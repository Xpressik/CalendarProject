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
 * Klasa odpowiedzialna za wyswietlanie okna z lista wydarzen dla danego dnia oraz umozliwianie uzytkownikowi tworzenie nowych wydarzen,
 * zapis wybranych wydarzen do plikow XML oraz usuwanie wybranych wydarzen.
 * @author Dawid
 */
public class DayList extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	
	private static EventService eventService;
	/**
	 * Tworzy okno wyswietlajace wydarzenia dla danego dniapoprzez wywolanie konstruktora klasy DayList oraz odpowienie skonfigurowanie okna.
	 * @param date - data dla ktorej maja zostac wyswietlone wydarzenia
	 * @param formattedDate - sfromatowana data na potrzeby powiadomien
	 */
	public static void init(final String date, final String formattedDate, final EventService eventService){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DayList frame = new DayList(date, formattedDate, eventService);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Konstruktor tworzy na stercie obiekt klasy DayList <br> 
	 * Tworzy okno, ktore wyswietla liste wydarzen dla danego dnia oraz implemenuje odpowiednie zachowania i mozliwosc interakcji z uzytkowniem poprzez: <br>
	 *  - usuwanie wybranych wydarzen,<br>
	 *  - zapis wybranych wydarzen do formatu XML (serializacja),<br>
	 *  - tworzenie nowych wydarzen dla tego dnia.
	 * 
	 * @param date - data dla ktorej maja zostac wyswietlone wydarzenia
	 * @param formattedDate - odpowiednio sformatowana data na potrzeby przypomnien
	 */
	public DayList(final String date, String formattedDate, final EventService eventService) {
		
		this.eventService = eventService;
		this.date = date;
		final List<Event> eventList = eventService.getEventsFromSpecifiedDay(date);

		
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
				CreateEventWindow.init(date, ld.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), eventService);
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
		btnNewButton_1.addActionListener(new DeleteButtonListener(list, eventList, eventService));	
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