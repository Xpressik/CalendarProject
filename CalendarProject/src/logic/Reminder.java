package logic;

import java.awt.Event;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import data.EventService;
/**
 * Klasa odpowiedzialna za dzialanie powiadomien. 
 *
 */
	
public class Reminder {
	
	static private EventService eventService;
	
	public Reminder(EventService eventService){
		this.eventService = eventService;
	}
	
	/**
	 * Zwraca roznice czasu pomiedzy przekazanymi argumetami.<br> Wykorzystywana przy sprawdzaniu kiedy ma zostac wyswietlone powiadomienie dla nadchodzacego wydarzenia.
	 * 
	 * @param dateOne - godzina od ktorej odejmujemy
	 * @param dateTwo - godzina ktora odejmujemy
	 * @return Zwraca roznice pomiedzy parametrami (dateOne - dateTwo).
	 */
	public String getTimeDiff(Date dateOne, Date dateTwo) {        
		String diff = "";        
		long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());        
		diff = String.format("%d hour(s) %d min(s)", 
				TimeUnit.MILLISECONDS.toHours(timeDiff),                
				TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));        
		return diff;}
	
	/**
	 * Zwraca String message z wiadomoscia o nadchodzacym wydarzeniu.
	 * 
	 * @return String message z wiadomoscia o nadchodzacym wydarzeniu.
	 */
	public static String toRemind(){
		List<data.Event> events = eventService.getAllEvents();
		String message = "";
		LocalDateTime ldt = LocalDateTime.now().withNano(0);
		LocalDateTime ldt2 = LocalDateTime.now().withSecond(10).withNano(0);
		LocalDateTime ldt1 = LocalDateTime.now().withSecond(0).withNano(0).minusSeconds(10);
		Date dt = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		Date dt1= Date.from(ldt1.atZone(ZoneId.systemDefault()).toInstant());
		Date dt2= Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
		for (data.Event event : events) {
			if (event.getReminder() != null) {
				if (event.getReminder().equals(dt)/* || event.getReminder().after(dt1) && event.getReminder().before(dt2)*/) {
					message += "You have an event on: " + event.getDate() + " from: " + event.getFrom() + " to: " +
							event.getTo() + " " + "Description: " + event.getDescription() + "\n";
				}
			}
		}
		return message;
	}
}
