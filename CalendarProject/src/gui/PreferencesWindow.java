package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.DBData;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class PreferencesWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox comboBox;
	private static Color backgroundColor = Color.GRAY;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;

	
	public static void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreferencesWindow frame = new PreferencesWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}

	public PreferencesWindow() {
		setTitle("Preferences");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 99, 419, 2);
		contentPane.add(separator);
		
		comboBox = new JComboBox(new String []{ "black", "green", "cyan", "grey", "yellow", "red"});
		comboBox.setBounds(5, 136, 92, 20);
		comboBox.addActionListener(this);
		contentPane.add(comboBox);
		
		passwordField = new JPasswordField(20);
		passwordField.setBounds(301, 56, 123, 20);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(334, 35, 61, 14);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("User");
		lblNewLabel.setBounds(202, 35, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(170, 56, 92, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(51, 35, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(26, 56, 98, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Database settings");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 10, 198, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				char [] input = passwordField.getPassword();
				String inputString;
				if(input.length == 0)
					inputString = "";
				else
					inputString = input.toString();
				
				DBData data = new DBData(textField_1.getText(), textField.getText(), inputString);
				try{
					PrintWriter toFile = new PrintWriter("dbData.txt");
					toFile.println(data.getDbName());
					toFile.println(data.getDbUser());
					toFile.println(data.getDbPassword());
					toFile.close();
				}
				catch(FileNotFoundException e){
					JOptionPane.showMessageDialog(null, "There has been some problems with file.\n Try again", "File not found", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(326, 227, 98, 23);
		contentPane.add(btnNewButton);
	}

	/**
	 * @return the backgroundColor
	 */
	public static Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if( arg0.getSource() == comboBox){
			Color [] colorArray = {Color.BLACK,Color.GREEN, Color.CYAN, Color.GRAY, Color.YELLOW, Color.RED };
			backgroundColor = colorArray[comboBox.getSelectedIndex()];
			System.out.println(backgroundColor.toString());
		}
		
	}
}
