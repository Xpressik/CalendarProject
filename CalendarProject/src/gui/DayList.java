package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import data.*;
import logic.DeleteButtonListener;
import logic.SaveXMLListener;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
/**
 * Klasa odpowiedzialna za wyœwietlanie okna z list¹ wydarzeñ dla danego dnia oraz umo¿liwianie u¿ytkownikowi tworzenie nowych wydarzeñ,
 * zapis wybranych wydarzeñ do plików XML oraz usuwanie wybranych wydarzeñ.
 * @author Dawid
 */
public class DayList extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	/**
	 * Tworzy okno wyœwietlaj¹ce wydarzenia dla danego dniapoprzez wywo³anie konstruktora klasy DayList oraz odpowienie skonfigurowanie okna.
	 * @param date - data dla której maja zostaæ wyswietlone wydarzenia
	 * @param formattedDate - sfromatowana data na potrzeby powiadomien
	 */
	public static void init(final String date, String formattedDate){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayList frame = new DayList(date, formattedDate);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Konstruktor tworzy na stercie obiekt klasy DayList <br> 
	 * Tworzy okno, które wyœwietla listê wydarzeñ dla danego dnia oraz implemenuje odpowiednie zachowania i mo¿liwoœæ interakcji z u¿ytkowniem poprzez: <br>
	 *  - usuwanie wybranych wydarzeñ,<br>
	 *  - zapis wybranych wydarzeñ do formatu XML (serializacja),<br>
	 *  - tworzenie nowych wydarzeñ dla tego dnia.
	 * 
	 * @param date - data dla której maj¹ zostaæ wyœwietlone wydarzenia
	 * @param formattedDate - odpowiednio sformatowana data na potrzeby przypomnieñ
	 */
	public DayList(final String date, String formattedDate) {
		
		this.date = date;

		final List<Event> eventList = EventList.getEventListForSpecifiedDate(date);

		
		setTitle("Events");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 517, 300);
		getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 43, 46, 14);
		getContentPane().add(lblFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(66, 43, 46, 14);
		getContentPane().add(lblTo);
		
		JLabel lblDesc = new JLabel("Description");
		lblDesc.setBounds(168, 43, 69, 14);
		getContentPane().add(lblDesc);
		
		JLabel lblNewLabel_3 = new JLabel("Place");
		lblNewLabel_3.setBounds(337, 43, 46, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnCreateEvent = new JButton("Create Event");
		btnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate ld = LocalDate.now();
				CreateEventWindow.init(date, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				dispose();
			}
		});
		btnCreateEvent.setBounds(171, 11, 106, 23);
		getContentPane().add(btnCreateEvent);
		
		JLabel lblEventsFor = new JLabel("Events for :  " + date);
		lblEventsFor.setBounds(10, 18, 139, 14);
		getContentPane().add(lblEventsFor);
		
		JButton btnNewButton = new JButton("Save to XML");
		btnNewButton.addActionListener(new SaveXMLListener(eventList));
		btnNewButton.setBounds(306, 11, 106, 23);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 68, 402, 182);
		getContentPane().add(scrollPane);
		
		final JList list = new JList();
		DayList.repaintList(list, eventList);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JButton btnNewButton_1 = new JButton("Delete"); 
		btnNewButton_1.addActionListener(new DeleteButtonListener(list, eventList));	
		btnNewButton_1.setBounds(412, 39, 89, 23);
		getContentPane().add(btnNewButton_1);		

	}
	private static String alignment(int length){
		
		String result = new String();		
		length = 40 - length;
		
		while(length > 0){
			result += "  ";
			length--;
		}
		return result;
	}
	
	public static void repaintList(JList list, List<Event> eventList){
		int size = eventList.size();
		String [] str = new String[size];
		for (int i= 0; i< size; i++){
			Event event = eventList.get(i);
			str[i] = (event.getFrom() + "    " + event.getTo() + "    " + event.getDescription() + alignment(event.getDescription().length()) + event.getPlace());
		}
		list.setListData(str);
	}
}