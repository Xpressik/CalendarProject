package gui;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.text.Font;
import logic.Reminder;
/**
 * Klasa wyœwietlaj¹ca okno  z powiadomieniem / informacj¹ o nadchodz¹cym wydarzeniu.
 *
 */
public class ReminderWindow extends JFrame {
	
	/**
	 * Konstruktor tworz¹cy na stercie instancjê klasy ReminderWindow.<br>
	 * Tworzy i wyœwietla okno z powiadomieniem o nadchodz¹cym wydarzeniu.
	 * @param message - informacje o wydarzeniu ( miejsce, opis, data)
	 */
	public ReminderWindow(String message){
		setAlwaysOnTop(true);
		this.setTitle("Reminder");
		this.setSize(610, 204);
		this.setVisible(true);
		setLocation(new Point(600, 300));

		JLabel remindLabel = new JLabel();
		remindLabel.setText(message);
		remindLabel.setSize(200, 200);
		
		getContentPane().add(remindLabel);
	}
}
