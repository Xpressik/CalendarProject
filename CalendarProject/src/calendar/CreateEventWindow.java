package calendar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class CreateEventWindow extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public void init(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventWindow frame = new CreateEventWindow();
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
	public CreateEventWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		lblNewLabel_1.setBounds(205, 79, 37, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 104, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("From");
		lblNewLabel_2.setBounds(51, 168, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("To");
		lblNewLabel_3.setBounds(326, 168, 22, 14);
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
		formattedTextField.setBounds(41, 193, 46, 20);
		getContentPane().add(formattedTextField);
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField(mask);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_1.setBounds(312, 193, 46, 20);
		getContentPane().add(formattedTextField_1);
	}
}
