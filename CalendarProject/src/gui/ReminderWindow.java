package gui;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.scene.text.Font;
import logic.Reminder;
/**
 * Klasa wy�wietlaj�ca okno  z powiadomieniem / informacj� o nadchodz�cym wydarzeniu.
 *
 */
public class ReminderWindow extends JFrame {
	
	/**
	 * Konstruktor tworz�cy na stercie instancj� klasy ReminderWindow.<br>
	 * Tworzy i wy�wietla okno z powiadomieniem o nadchodz�cym wydarzeniu.
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
