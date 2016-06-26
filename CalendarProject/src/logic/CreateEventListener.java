package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.Event;
import data.EventService;
import gui.CreateEventWindow;
import gui.DayList;

public class CreateEventListener implements ActionListener {
	
	private JFormattedTextField formattedTextField;
	private JFormattedTextField formattedTextField_1;
	private JComboBox comboBoxReminder;
	private String formattedDate; 
	private Map<Integer, Integer> mapping;
	private JTextField textField;
	private JTextField textField_1;
	private String date;
	private CreateEventWindow frame;
	private EventService eventService;
	

	public CreateEventListener(JFormattedTextField formattedTextField, JFormattedTextField formattedTextField_1,
			JComboBox comboBoxReminder, String formattedDate, Map<Integer, Integer> mapping, JTextField textField,
			JTextField textField_1, String date, CreateEventWindow frame, EventService eventService) {
	
		this.formattedTextField = formattedTextField;
		this.formattedTextField_1 = formattedTextField_1;
		this.comboBoxReminder = comboBoxReminder;
		this.formattedDate = formattedDate;
		this.mapping = mapping;
		this.textField = textField;
		this.textField_1 = textField_1;
		this.date = date;
		this.frame = frame;
		this.eventService = eventService;
	}

	public void actionPerformed(ActionEvent arg0) { 
		
		Date reminder = null;
		int fromHour = 0, fromMinutes = 0, toHour = 0, toMinutes = 0;
		try{
			String[] from = formattedTextField.getText().split(":");
			 fromHour = Integer.parseInt(from[0]);
			 fromMinutes = Integer.parseInt(from[1]);
			String[] to = formattedTextField_1.getText().split(":");
			 toHour = Integer.parseInt(to[0]);
			 toMinutes = Integer.parseInt(to[1]);
			 int reminderIdx = comboBoxReminder.getSelectedIndex();
			 LocalDate ld = LocalDate.parse(formattedDate);
				 LocalDateTime ldt = LocalDateTime.of(ld.getYear(), ld.getMonth(), ld.getDayOfMonth(), 0, 0);
				 ldt = ldt.withHour(fromHour).withMinute(fromMinutes).minusMinutes(mapping.get(reminderIdx));
				 reminder = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		}
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "You have to type hours.", "Wrong hours", JOptionPane.OK_OPTION);
			return;
		}
		
		if( fromHour > toHour || (fromHour == toHour && fromMinutes > toMinutes)){
			JOptionPane.showMessageDialog(null, "Event end before it starts.", "Wrong hours", JOptionPane.OK_OPTION);
		}
		else{
			eventService.addEvent(new Event(textField.getText(), textField_1.getText(), formattedTextField.getText(), formattedTextField_1.getText(), date, reminder)); 
			JOptionPane.showMessageDialog(null, "Event created properly.", "Success", JOptionPane.INFORMATION_MESSAGE);
			
			this.frame.dispose();
			LocalDate ld = LocalDate.now();	
			DayList.init(date, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), eventService);
		}
	}
}