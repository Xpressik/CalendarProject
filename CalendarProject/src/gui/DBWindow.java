package gui;

import logic.DBConnection;
import logic.IncorrectPasswordException;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

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
	
	private JMenuBar menuBar;
	private JMenuItem connect;
	private JPasswordField passwordField;
	private JPanel jPanel;
	private JLabel passwordLabel;
	private char[] input;
	private String inputString;
	private JButton enterButton;
	private JLabel result;
	
	public DBWindow(){
		init();
	}
	
	private void init(){
		
		jPanel = new JPanel();
		passwordLabel = new JLabel("Enter password to Database");
		jPanel.add(passwordLabel);
		getContentPane().add(jPanel);
		result= new JLabel();
		result.setMaximumSize(new Dimension(230, 50));
				
		menuBar = new JMenuBar();
		menuBar.setVisible(true);
		
		connect = new JMenuItem("connectToDB", KeyEvent.VK_C);
		connect.disable();
		menuBar.add(connect);
		
		passwordField = new JPasswordField(20);
		jPanel.add(passwordField);
		
		enterButton = new JButton("ENTER");
		enterButton.setSize(new Dimension(20, 20));
		jPanel.add(enterButton);
		jPanel.add(result);
		
		enterButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				input = passwordField.getPassword();

				if(input.length == 0)
					inputString = "";
				else
					inputString = input.toString();
				DBConnection conn = null;
				try{
					conn = new DBConnection(inputString);
				}
				catch(IncorrectPasswordException ex){ 
					result.setText("Incorrect password");
					return;
				} catch (ClassNotFoundException e1) {
					result.setText(e1.getMessage());
				}
				/*
				catch(SQLException ex){
					result.setText(ex.toString());
					System.out.println("Error: " + ex);
					return;
				} catch (ClassNotFoundException e1) {
					//result.setText(e1.toString());
					return;
				}*/
				result.setText("connected");
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}