package gui;

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

import data.Event;
import data.EventService;

public class TerminalDisplay {
	
	private final EventService eventService;
	
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
	
	public TerminalDisplay(EventService eventService){
		this.eventService = eventService;
		try {
			mainLoop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mainLoop() throws IOException{
		System.out.println("Tryb awaryjny - tekstowy");
		
		Scanner input = new Scanner(System.in);
		input.useDelimiter(Pattern.compile("[\\r\\n;]+"));
		int action = 0;
		String selectedEventDate = null;
		do {
			System.out.println("MENU:");
			System.out.println("  1 - SHOW EVENTS FOR DATE, 2 -  SHOW EVENTS FOR MONTH, 3 - SHOW EVENTS BY PLACE");
			System.out.println("  4 - CREATE EVENT, 5 - DELETE EVENT, 0 - Exit");
			
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
				
			case 50:
				break;
			case 51:
				break;
			}
			
		} while(action != 0);
		System.out.println("The end");
	}
	
	private void showEventsForDate(String datestr) {
		System.out.println("Events for date: " + datestr);
		List<Event> events = eventService.getEventsFromSpecifiedDay(datestr);
		for (int i = 0; i < events.size(); i++) {
			showEvent(i, events.get(i), false);
		}
	}
	
	private void showEventsForMonth(Integer month) {
		System.out.println("Events for month: " + monthMapping.get(month));
		List<Event> events = eventService.getEventsFromSpecifiedMonth(month.toString());
		for (int i = 0; i < events.size(); i++) {
			showEvent(null, events.get(i), true);
		}
	}
	
	private void showEventsForPlace(String place) {
		System.out.println("Events for place: " + place);
		List<Event> events = eventService.filterWithPlace(place);
		for (int i = 0; i < events.size(); i++) {
			showEvent(null, events.get(i), true);
		}
	}
	
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
}
