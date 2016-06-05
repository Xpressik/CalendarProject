package calendar;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.text.ParseException;
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
	
	private Calendar c;
	/**
	 * Launch the application.
	 */
	public void init(Calendar cal){    // USUNAC METODE INIT DODAC JEJ CIALO DO KONSTRUKOTRA
		c = cal;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventWindow frame = new CreateEventWindow(c);
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
	public CreateEventWindow(Calendar c) {
		
		this.c = c;
		
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
		lblNewLabel_4.setText(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR));
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				String[] from = formattedTextField.getText().split(":");
				int fromHour = Integer.parseInt(from[0]);
				int fromMinutes = Integer.parseInt(from[1]);
				String[] to = formattedTextField_1.getText().split(":");
				int toHour = Integer.parseInt(to[0]);
				int toMinutes = Integer.parseInt(to[1]);
				
				if( fromHour > toHour || (fromHour == toHour && fromMinutes > toMinutes)){
					//throw new Exception("Godzina rozpoczêcia musi byæ przed godzin¹ zakoñczenia");
				}
				else
					EventList.addEvent(new Event(textField.getText(), textField_1.getText(), formattedTextField.getText(), formattedTextField_1.getText(), c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR))); 
			}
		});
		btnCreate.setBounds(180, 192, 89, 23);
		getContentPane().add(btnCreate);
		
		JButton btnPrnt = new JButton("Prnt");
		btnPrnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventList.printEvents();
				List<Event> test = EventList.getEventListForSpecifiedDate(c.get(Calendar.DAY_OF_MONTH) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.YEAR)); // ZAPISAC TO c..... jako pole String ..
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
