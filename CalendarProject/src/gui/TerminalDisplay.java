package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.Timer;

import data.Event;
import data.EventService;
import logic.Reminder;
/**
 * Klasa odpowiedzialna za obsluge logiki programu w trybie awaryjnym - w konsoli.<br> 
 * Zawiera takie informacje jak:<br>
 *  - mapa ktora zamienia numer miesiaca na jego nazwe po ang.<br> 
 *  - timer ktory jest potrzebny do odmierzania czasu do ustawiania powiadomien<br> 
 *  - informacje o przypomnieniu
 * 
 */
public class TerminalDisplay {
	
	private final EventService eventService;
	/**
	 * mapa ktora zamienia numer miesiaca na jego nazwe po ang.
	 */
	private static final Map<Integer, String> monthMapping = new HashMap<Integer, String>(){{
 		put(1, "January");
    put(2, "February");
 		put(3, "March");
 		put(4, "April");
 		put(5, "May");
 		put(6, "June");
 		put(7, "July");
 		put(8, "August");
 		put(9, "September");	
 		put(10, "October");
 		put(11, "November");
 		put(12, "December");
 	}};

	private Timer timer;
	/**
	 * Konstruktor domyslny ktory inicjuje zmienna lokalna typu EventService.<br>
	 * Dzieki temu mozemy dzialac na repozytorium z pozycji konsoli
	 * konstruktor tworzy obiekt typu Timer.<br>
	 * Potrzebny jest do tworzenia obiektow powiadomien o zblizajacych sie wydarzeniach<br>
	 * Uruchamia dzialanie glownej petli w ktorej odbywaja sie wszystkie akcje na repozytorium
	 * @param eventService - obiekt ktory dostarcza logike obslugi zdarzen
	 */
	public TerminalDisplay(final EventService eventService){
		this.eventService = eventService;
		try {
			timer = new javax.swing.Timer(1000, new ActionListener() {
	 			private String message = null;
				@Override
	 			public void actionPerformed(ActionEvent e) {
//					System.out.println("WORKING...");	
					Reminder reminderObject = new Reminder(eventService);
	 				message = reminderObject.toRemind();
	 				if (message != null && !"".equals(message)){
	 					reminderMessage(message);
	 				}
	 			}
			});
			System.out.println("test");
			timer.start();
			mainLoop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Glowna petla w konsoli w ktorej odbywaja sie wszystkie operacje az do podania odpowiedniej wartosci ktora przerywa dzialanie petli
	 * @throws IOException - metoda ta moze rzucac obiektami typu IOException
	 */
	public void mainLoop() throws IOException{
		System.out.println("Tryb awaryjny - tekstowy");
		
		Scanner input = new Scanner(System.in);
		input.useDelimiter(Pattern.compile("[\\r\\n;]+"));
		int action = 0;
		String selectedEventDate = null;
		do {
			System.out.println("MENU:");
			System.out.println("  1 - SHOW EVENTS FOR DATE, 2 -  SHOW EVENTS FOR MONTH, 3 - SHOW EVENTS BY PLACE");
			System.out.println("  4 - CREATE EVENT, 5 - DELETE EVENT");
			System.out.println("  6 - LOAD FROM XML, 7 - SAVE TO XML, 0 - EXIT");
			
			action = input.nextInt();// System.in.read();
			
			System.out.println("ACTION: " + action);
			
			switch(action){
			
			case 1: {
				// SHOW EVENTS FOR DATE
				System.out.println("Enter date of event (yyyy-MM-dd):");
				String datestr = input.next();
				showEventsForDate(datestr);
				selectedEventDate = datestr;
				break;
			}
			
			case 2: {
				// SHOW EVENTS FOR MONTH
				System.out.println("Enter a month [1-12]: ");
				int month = input.nextInt();
				showEventsForMonth(month);
				selectedEventDate = null;
				break;
			}
			
			case 3: {
				// SHOW EVENTS FOR PLACE
				System.out.println("Enter a place: ");
				String place = input.next();
				showEventsForPlace(place);
				selectedEventDate = null;
				break;
			}
				
			case 4:	{
				// CREATE EVENT
				System.out.println("Enter date of event (yyyy-MM-dd):");
				
				String datestr = input.next();
				
				System.out.println("Enter start hour (hh:mm): ");
				String fromHour = input.next();
				
				System.out.println("Enter end hour(hh:mm): ");
				String toHour = input.next();
				
				System.out.println("Enter place: ");
				String place = input.next();
				
				System.out.println("Enter description: ");
				String description = input.next();
				
				System.out.println("Enter reminder(0min, 5min, 10min, 15min, 30min, 1h, 2h, 6h, 12h)");
				int reminder = input.nextInt();
				
				Date reminderDate = eventService.createReminder(datestr, fromHour, reminder);
				eventService.addEvent(new Event(description, place, fromHour, toHour, datestr, reminderDate));
				
				showEventsForDate(datestr);
				selectedEventDate = datestr;
				break;
			}
			
			case 5: {
				// DELETE EVENT FOR DATE
				if (selectedEventDate == null || "".equals(selectedEventDate)) {
					System.out.println("No events date selected");
					break;
				}
				System.out.println("Enter index of event to delete:");
				int idx = input.nextInt();
				eventService.removeEventByIdx(selectedEventDate, idx);
				showEventsForDate(selectedEventDate);
				break;
			}
				
			case 6: {
				// LOAD FRO XML
				System.out.println("Enter XML file name:");
				String filename = input.next();
				eventService.loadEventsFromXmlFile(filename);
				selectedEventDate = null;
				break;
			}
			case 7: {
				// SAVE TO XML
				String filename = null;
				if (!eventService.hasXmlDataRepository()) {
					System.out.println("Enter XML file name:");
					filename = input.next();
				}
				eventService.saveEventsToXmlFile(filename);
				selectedEventDate = null;
				break;
			}
			}
			
		} while(action != 0);
		System.out.println("The end");
	}
	/**
	 * Wyswietla w konsoli wszystkie eventy ktore odbywaja sie danego przez parametr dnia
	 * @param datestr - string ktory reprezentuje date dla ktorej maja zostac pokazane eventy
	 */
	private void showEventsForDate(String datestr) {
		System.out.println("Events for date: " + datestr);
		List<Event> events = eventService.getEventsFromSpecifiedDay(datestr);
		for (int i = 0; i < events.size(); i++) {
			showEvent(i, events.get(i), false);
		}
	}
	/**
	 * Wyswietla w konsoli wszystkie eventy ktore odbywaja danego przez parametr miesiaca 
	 * @param month - integer ktory okresla numer miesiaca ktora nastepnie zostaje zmapowana do nazwy w jezyku ang.
	 */
	private void showEventsForMonth(Integer month) {
		System.out.println("Events for month: " + monthMapping.get(month));
		List<Event> events = eventService.getEventsFromSpecifiedMonth(month.toString());
		for (int i = 0; i < events.size(); i++) {
			showEvent(null, events.get(i), true);
		}
	}
	/**
	 * Wyswietla w konsoli wszystkie eventy ktore odbywaja sie w danym przez parametr miejscu
	 * @param place - miejsce dla ktorego szukamy wydarzen
	 */
	private void showEventsForPlace(String place) {
		System.out.println("Events for place: " + place);
		List<Event> events = eventService.filterWithPlace(place);
		for (int i = 0; i < events.size(); i++) {
			showEvent(null, events.get(i), true);
		}
	}
	/**
	 * Wyswietla stringa ktory definiuje dane wydarzenie 
	 * @param idc - integer ktory okresla index wydarzenia w danym dniu
	 * @param event - event dla ktorego budowany jest string definiujacy ten event
	 * @param printEventDate - zmienna logiczna ktora decyduje o tym czy do stringa definiujacego wydarzenie dodawac informacje o dacie
	 */
	private void showEvent(Integer idx, final Event event, boolean printEventDate) {
		StringBuilder strb = new StringBuilder();
		strb
			.append("=====\n");
		if (idx != null) {
			strb.append("  Index: ").append(idx).append("\n");
		}
		if (printEventDate) {
			strb.append("  Date: ").append(event.getDate()).append("\n");
		}
		LocalDateTime ldt = LocalDateTime.ofInstant(event.getReminder().toInstant(), ZoneId.systemDefault());
		strb
			.append("  Description: ").append(event.getDescription()).append("\n")
			.append("  Place: ").append(event.getPlace()).append("\n")
			.append("  Event hours: ").append(event.getFrom()).append("-").append(event.getTo()).append("\n")
			.append("  Reminder:").append(ldt.toString()).append("\n")
			.append("=====\n");
		System.out.println(strb.toString());
	}
	/**
	 * Wyswietla podany przez parametr message Remindera 
	 * @param message - message zwrocony przez remindera ktory zawiera informacje o zblizajacym sie evencie
	 */
	public void reminderMessage(String message){
		System.out.println(message);
	}
}
