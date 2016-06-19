package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.text.Font;
import logic.Reminder;

public class ReminderWindow extends JFrame {
	
	public ReminderWindow(String message){
		this.setTitle("Reminder");
		this.setSize(260, 260);
		this.setVisible(true);
		
		JLabel remindLabel = new JLabel();
		remindLabel.setText(message);
		remindLabel.setSize(200, 200);
		
		this.add(remindLabel);
	}
}
