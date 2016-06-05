package calendar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class CreateEventWindow extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	
	private String date;
	/**
	 * Launch the application.
	 */
	public static void init(String date){    // USUNAC METODE INIT DODAC JEJ CIALO DO KONSTRUKOTRA
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventWindow frame = new CreateEventWindow(date);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateEventWindow frame = new CreateEventWindow();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	public CreateEventWindow(final Calendar c) {
=======
	public CreateEventWindow(Calendar c) {
>>>>>>> parent of d36de0c... adding final attribute to methods parametres
		
		this.c = c;
=======
	public CreateEventWindow(String date) {
>>>>>>> cba5233163fde782a69f1edd308747b35563151f
		
		this.date = date;
		//this.date = c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR);
				
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setBounds(195, 23, 71, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(180, 48, 86, 20);
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
		lblNewLabel_2.setBounds(71, 168, 36, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("To");
		lblNewLabel_3.setBounds(326, 168, 24, 14);
		getContentPane().add(lblNewLabel_3);
			
		MaskFormatter mask = null;
		try{
			mask = new MaskFormatter("##:##");
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		mask.setPlaceholderCharacter('_');
		
		JFormattedTextField formattedTextField = new JFormattedTextField(mask);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(61, 193, 46, 20);
		getContentPane().add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField(mask);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_1.setBounds(312, 193, 46, 20);
		getContentPane().add(formattedTextField_1);
		
		JLabel lblNewLabel_4 = new JLabel("Current Date");
		lblNewLabel_4.setBounds(26, 23, 104, 14);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setText(date);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				
				int fromHour = 0, fromMinutes = 0, toHour = 0, toMinutes = 0;
				try{
					String[] from = formattedTextField.getText().split(":");
					 fromHour = Integer.parseInt(from[0]);
					 fromMinutes = Integer.parseInt(from[1]);
					String[] to = formattedTextField_1.getText().split(":");
					 toHour = Integer.parseInt(to[0]);
					 toMinutes = Integer.parseInt(to[1]);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "You have to type hours.", "Wrong hours", JOptionPane.OK_OPTION);
					return;
				}
				
				if( fromHour > toHour || (fromHour == toHour && fromMinutes > toMinutes)){
					JOptionPane.showMessageDialog(null, "Event end before it starts.", "Wrong hours", JOptionPane.OK_OPTION);
				}
				else{
					EventList.addEvent(new Event(textField.getText(), textField_1.getText(), formattedTextField.getText(), formattedTextField_1.getText(), date)); 
					JOptionPane.showMessageDialog(null, "Event created propertly.", "Success", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnCreate.setBounds(180, 192, 89, 23);
		getContentPane().add(btnCreate);
		
		JButton btnPrnt = new JButton("Prnt");
		btnPrnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventList.printEvents();
				List<Event> test = EventList.getEventListForSpecifiedDate(date); 
				for(Event x : test){
					System.out.println(x.getDate() + " " + x.getDescription());
				}
			}
		});
		btnPrnt.setBounds(26, 103, 89, 23);
		getContentPane().add(btnPrnt);
		
		JCheckBox chckbxWholeDay = new JCheckBox("Whole Day");
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
		chckbxWholeDay.setBounds(180, 144, 86, 23);
		getContentPane().add(chckbxWholeDay);
	}
}
