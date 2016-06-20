package logic;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import data.Event;
import data.EventList;
/**
 * Klasa odpowiedzialna za dzia³anie powiadomieñ. 
 *
 */
public class Reminder {
	
	
	public static String getTimeDiff(Date dateOne, Date dateTwo) {        
		String diff = "";        
		long timeDiff = Math.abs(dateOne.getTime() - dateTwo.getTime());        
		diff = String.format("%d hour(s) %d min(s)", 
				TimeUnit.MILLISECONDS.toHours(timeDiff),                
				TimeUnit.MILLISECONDS.toMinutes(timeDiff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeDiff)));        
		return diff;}
	
	public static String toRemind(){
		List<Event> events = EventList.getEvents();
		String message = "";
		LocalDateTime ldt = LocalDateTime.now().withNano(0);
		LocalDateTime ldt2 = LocalDateTime.now().withSecond(10).withNano(0);
		LocalDateTime ldt1 = LocalDateTime.now().withSecond(0).withNano(0).minusSeconds(10);
		Date dt = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		Date dt1= Date.from(ldt1.atZone(ZoneId.systemDefault()).toInstant());
		Date dt2= Date.from(ldt2.atZone(ZoneId.systemDefault()).toInstant());
		for (Event event : events) {
			if (event.getReminder() != null) {
			//	System.out.println("ldt " + ldt + " dt " + dt + "event " + event.getReminder());
				if (event.getReminder().equals(dt)/* || event.getReminder().after(dt1) && event.getReminder().before(dt2)*/) {
					message += "You have an event on: " + event.getDate() + " from: " + event.getFrom() + " to: " +
							event.getTo() + " " + "Description: " + event.getDescription() + "\n";
				}
			}
		}
		return message;
	}
}
