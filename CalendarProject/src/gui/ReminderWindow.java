package gui;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Klasa wyświetlająca okno  z powiadomieniem / informacją o nadchodzącym wydarzeniu.
 *
 */
public class ReminderWindow extends JFrame {
	
	/**
	 * Konstruktor tworzący na stercie instancję klasy ReminderWindow.<br>
	 * Tworzy i wyświetla okno z powiadomieniem o nadchodzącym wydarzeniu.
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
