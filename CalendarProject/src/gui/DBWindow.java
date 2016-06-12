package gui;

import data.DBConnection;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class DBWindow extends JFrame implements ActionListener{
	
	JMenuBar menuBar;
	JMenuItem connect;
	JPasswordField passwordField;
	JPanel jPanel;
	JLabel passwordLabel;
	char[] input;
	String inputString;
	JButton enterButton;
	JLabel enteredPassword;
	
	public DBWindow(){
		init();
	}
	
	private void init(){
		
		jPanel = new JPanel();
		passwordLabel = new JLabel("Enter password to Database");
		jPanel.add(passwordLabel);
		this.add(jPanel);
		enteredPassword= new JLabel();
				
		menuBar = new JMenuBar();
		menuBar.setVisible(true);
		
		connect = new JMenuItem("connectToDB", KeyEvent.VK_C);
		connect.disable();
		menuBar.add(connect);
		
		passwordField = new JPasswordField(20);
		jPanel.add(passwordField);
		//jPanel.add(enteredPassword);
		
		enterButton = new JButton("ENTER");
		enterButton.setSize(new Dimension(20, 20));
		jPanel.add(enterButton);
		jPanel.add(enteredPassword);
		
		enterButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				input = passwordField.getPassword();
				DBConnection conn = new DBConnection(inputString);
				enteredPassword.setText("connecting...");
				jPanel.add(enteredPassword);
				conn.getData();
			}
			
		});
		
		connect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				inputString.valueOf(input);
			}
			
		});
		
		
		this.setMinimumSize(new Dimension(250, 100));
		this.setSize(new Dimension(200, 170));
		this.setLocation(new Point(600, 300));
		//this.setContentPane().setLayout(null);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}