package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

import data.*;
import logic.CreateEventListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.text.ParseException;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Point;
import javax.swing.JComboBox;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Klasa odpowiedzialna za umozliwienie uzytkownikowi tworzenia nowych wydarzen. Wyswietla okno, w ktorym uzytkownik wprowadzajac odpowiedenie dane tworzy nowe wydarzenia.
 * @author Dawid
 *
 */
public class CreateEventWindow extends JFrame {
	/**
	 * Klasa wyswietlajaca okno tworzenia nowego wydarzenia.
	 */
	private static final long serialVersionUID = -2111993159030174208L;
	/**
	 * Pole tekstowe do ktorego uzytkownik wpisuje slowny opis wydarzenia
	 */
	private JTextField textField;
	/**
	 * Pole tekstowe do ktorego uzytkownik wpisuje miejsce wydarzenia
	 */
	private JTextField textField_1;
	/**
	 * ComboBox z ktorego uzytkownik moze ustawic powiadomienie o wydarzeniu
	 */
	private JComboBox comboBoxReminder;
	/**
	 * String przechowujacy date, dla ktorej chcemy stworzyc wydarzenie
	 */
	private String date;
	
	private EventService eventService;
	
	/**
	 * Mapa, ktora umozliwa wybor odpowiedniego interwalu czasu dla powiadomien.
	 */
	private static final Map<Integer, Integer> mapping = new HashMap<Integer, Integer>(){{
		 		put(0, 0);
		    put(1, 5);
		 		put(2, 10);
		 		put(3, 15);
		 		put(4, 30);
		 		put(5, 60);
		 		put(6, 120);
		 		put(7, 360);
		 		put(8, 720);	
		 	}};
		 	
	/**
	 * Uruchamia okno tworzenia wydarzen poprzez wywolanie konstruktora klasy CreateEventWindow oraz ustawienie widocznosci okna.
	 * @param date - data dla ktorej ma zostac stworzone wydarzenie
	 * @param formattedDate - sfromatowana data na potrzeby ustawienia powiadomienia
	 */
	public static void init(final String date, final String formattedDate, final EventService eventService){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateEventWindow frame = new CreateEventWindow(date, formattedDate, eventService);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Konstruktor tworzacy na stercie instancje klasy CreateEventWindow.<br>
	 * Tworzy okno oraz implemenuje odpowiednie zachowania i mozliwosc interakcji z uzytkownikiem podczas tworzenia wydarzen.
	 * @param date - data dla ktorej ma zostac stworzone wydarzenie
	 * @param formattedDate - sfromatowana data na potrzeby ustawienia powiadomienia
	 */
	public CreateEventWindow(final String date, final String formattedDate, final EventService eventService) {
		setTitle("Event Creator");
		setLocation(new Point(220, 700));
		
		this.date = date;
		this.eventService = eventService;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Description");
		lblNewLabel.setBounds(195, 23, 71, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(138, 48, 189, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Place");
		lblNewLabel_1.setBounds(205, 79, 43, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 104, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("From");
		lblNewLabel_2.setBounds(85, 132, 36, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("To");
		lblNewLabel_3.setBounds(340, 132, 24, 14);
		getContentPane().add(lblNewLabel_3);
			
		MaskFormatter mask = null;
		try{
			mask = new MaskFormatter("##:##");
		}
		catch(ParseException e){
			e.printStackTrace();
		}
		mask.setPlaceholderCharacter('_');
		
		final JFormattedTextField formattedTextField = new JFormattedTextField(mask);
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(75, 157, 46, 20);
		getContentPane().add(formattedTextField);
		
		final JFormattedTextField formattedTextField_1 = new JFormattedTextField(mask);
		formattedTextField_1.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField_1.setBounds(326, 157, 46, 20);
		getContentPane().add(formattedTextField_1);
		
		JLabel lblNewLabel_4 = new JLabel("Current Date");
		lblNewLabel_4.setBounds(26, 23, 104, 14);
		getContentPane().add(lblNewLabel_4);
		lblNewLabel_4.setText(date);
		
		comboBoxReminder = new JComboBox( new String[] {"", "5 min", "10 min", "15 min", "30 min", "1 h", "2 h", "6 h", "12 h"} );
		comboBoxReminder.setBounds(180, 196, 86, 20);
		getContentPane().add(comboBoxReminder);	
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new CreateEventListener(formattedTextField, formattedTextField_1, 
				comboBoxReminder, formattedDate, mapping, textField,textField_1, date, this, eventService));
		
		btnCreate.setBounds(177, 227, 89, 23);
		getContentPane().add(btnCreate);
		
		final JCheckBox chckbxWholeDay = new JCheckBox("Whole Day");
		chckbxWholeDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if (chckbxWholeDay.isSelected()){
					
					formattedTextField.setText("00:00");
					formattedTextField_1.setText("23:59");
					formattedTextField.setEnabled(false);
					formattedTextField_1.setEnabled(false);
				}
				else{
					
					formattedTextField.setText(null);
					formattedTextField_1.setText(null);
					formattedTextField.setEnabled(true);
					formattedTextField_1.setEnabled(true);
				}
				
			}
		});
		chckbxWholeDay.setBounds(180, 156, 86, 23);
		getContentPane().add(chckbxWholeDay);
		
		JLabel lblReminder = new JLabel("Reminder");
		lblReminder.setBounds(121, 199, 68, 14);
		getContentPane().add(lblReminder);
		
	}
}