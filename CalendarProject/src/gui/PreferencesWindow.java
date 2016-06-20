package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.SaveButtonListener;

import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

/**
 * Klasa reprezentujaca okno ustawien programu, gdzie mozna ustawic dane polaczenia z baza danych.
 * @author Dawid
 *
 */
public class PreferencesWindow extends JFrame {

	
	private static final long serialVersionUID = 1L;
	/**
	 * Panel, ktory umozliwa rozmieszczenie kompomentow w oknie.
	 */
	private JPanel contentPane;
	/**
	 * Pole, w ktore uzytkownik wpisuje haslo dostepu do bazy danych.
	 */
	private JPasswordField passwordField;
	/**
	 * Pole, w ktore uzytkownik wpisuje nazwe uzytkownika bazy danych 
	 */
	private JTextField textField;
	/**
	 * Pole, w ktore uzytkownik wpisuje nazwe bazy danych 
	 */
	private JTextField textField_1;

	/**
	 * Metoda sluzaca do stworzenia i wyswietlenia okna ustawien aplikacji. 
	 */
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
	/**
	 * Konstruktor tworzacy na stercie obiekt klasy PreferencesWindow.<br> 
	 * Tworzy i wyswietla okno ustawien aplikacji, gdzie mozemy zmienic dane polaczenia z baza danych.
	 */
	public PreferencesWindow() {
		setTitle("Preferences");
		setBounds(100, 100, 462, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(5, 99, 419, 2);
		contentPane.add(separator);
		
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
		btnNewButton.addActionListener(new SaveButtonListener(textField_1, textField, passwordField));
		btnNewButton.setBounds(326, 144, 98, 23);
		contentPane.add(btnNewButton);
	}
}
