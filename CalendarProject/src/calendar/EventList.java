package calendar;

import java.util.List;
import java.util.Vector;

public class EventList {
	private static List<Event> events = new Vector<Event>();
	
	public static void addEvent(Event evt){
		events.add(evt);
	}
	public static List<Event> getEventListForSpecifiedDate(){
		// to do ....
		
		return null;
	}
	public static void printEvents(){
		for( Event x : events ){
			System.out.println(x.getDescription() + x.getPlace() + " " + x.getFrom() + " - " + x.getTo());
		}
	}
}
