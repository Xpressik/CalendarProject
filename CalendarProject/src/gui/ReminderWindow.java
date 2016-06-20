package gui;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * Klasa wyswietlajaca okno  z powiadomieniem / informacja o nadchodzacym wydarzeniu.
 *
 */
public class ReminderWindow extends JFrame {
	
	/**
	 * Konstruktor tworzacy na stercie instancje klasy ReminderWindow.<br>
	 * Tworzy i wyswietla okno z powiadomieniem o nadchodzacym wydarzeniu.
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
