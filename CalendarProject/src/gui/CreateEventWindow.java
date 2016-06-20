package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

import data.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.ParseException;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Point;
import javax.swing.JComboBox;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



public class CreateEventWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2111993159030174208L;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBoxReminder;
	
	private String date;
	
	private static final Map<Integer, Integer> mapping = new HashMap<Integer, Integer>(){{
		 		put(1, 5);
		 		put(2, 10);
		 		put(3, 15);
		 		put(4, 30);
		 		put(5, 60);
		 		put(6, 120);
		 		put(7, 360);
		 		put(8, 720);	
		 	}};
	/**
	 * Launch the application.
	 */
	public static void init(final String date, String formattedDate){    // USUNAC METODE INIT DODAC JEJ CIALO DO KONSTRUKOTRA
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventWindow frame = new CreateEventWindow(date, formattedDate);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateEventWindow(final String date, String formattedDate) {
		setTitle("Event Creator");
		setLocation(new Point(220, 700));
		
		this.date = date;
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setBounds(195, 23, 71, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(138, 48, 189, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Place");
		lblNewLabel_1.setBounds(205, 79, 43, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 104, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("From");
		lblNewLabel_2.setBounds(85, 132, 36, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("To");
		lblNewLabel_3.setBounds(340, 132, 24, 14);
		getContentPane().add(lblNewLabel_3);
			
		MaskFormatter mask = null;
		try{
			mask = new MaskFormatter("##:##");
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		mask.setPlaceholderCharacter('_');
		
		final JFormattedTextField formattedTextField = new JFormattedTextField(mask);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(75, 157, 46, 20);
		getContentPane().add(formattedTextField);
		
		final JFormattedTextField formattedTextField_1 = new JFormattedTextField(mask);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_1.setBounds(326, 157, 46, 20);
		getContentPane().add(formattedTextField_1);
		
		JLabel lblNewLabel_4 = new JLabel("Current Date");
		lblNewLabel_4.setBounds(26, 23, 104, 14);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setText(date);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			
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
					 if ( reminderIdx > 0 ){
						 LocalDateTime ldt = LocalDateTime.of(ld.getYear(), ld.getMonth(), ld.getDayOfMonth(), 0, 0);
						 ldt = ldt.withHour(fromHour).withMinute(fromMinutes).minusMinutes(mapping.get(reminderIdx));
						 System.out.println("From h: " + fromHour + " m: " + fromMinutes);
						 System.out.println("Event date: " + ldt);
						 reminder = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
					 }
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "You have to type hours.", "Wrong hours", JOptionPane.OK_OPTION);
					return;
				}
				
				if( fromHour > toHour || (fromHour == toHour && fromMinutes > toMinutes)){
					JOptionPane.showMessageDialog(null, "Event end before it starts.", "Wrong hours", JOptionPane.OK_OPTION);
				}
				else{
					EventList.addEvent(new Event(textField.getText(), textField_1.getText(), formattedTextField.getText(), formattedTextField_1.getText(), date, reminder)); 
					JOptionPane.showMessageDialog(null, "Event created propertly.", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					LocalDate ld = LocalDate.now();	
					DayList.init(date, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}
			}
		});
		btnCreate.setBounds(177, 227, 89, 23);
		getContentPane().add(btnCreate);
		
		final JCheckBox chckbxWholeDay = new JCheckBox("Whole Day");
		chckbxWholeDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if (chckbxWholeDay.isSelected()){
					
					formattedTextField.setText("00:00");
					formattedTextField_1.setText("23:59");
					formattedTextField.setEnabled(false);
					formattedTextField_1.setEnabled(false);
				}
				else{
					
					formattedTextField.setText(null);
					formattedTextField_1.setText(null);
					formattedTextField.setEnabled(true);
					formattedTextField_1.setEnabled(true);
				}
				
			}
		});
		chckbxWholeDay.setBounds(180, 156, 86, 23);
		getContentPane().add(chckbxWholeDay);
		
		comboBoxReminder = new JComboBox( new String[] {null, "5 min", "10 min", "15 min", "30 min", "1 h", "2 h", "6 h", "12 h"} );
		comboBoxReminder.setBounds(180, 196, 86, 20);
		getContentPane().add(comboBoxReminder);	
		
		JLabel lblReminder = new JLabel("Reminder");
		lblReminder.setBounds(121, 199, 68, 14);
		getContentPane().add(lblReminder);
		
	}
}