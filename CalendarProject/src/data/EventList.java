package data;

import java.util.List;
import java.util.Vector;

public class EventList {
	private static List<Event> events = new Vector<Event>();
	
	public static List<Event> getEvents() {
		return events;
	}
	public static void deleteEvent (Event evt){ //
		events.remove(evt);
	}
	public static void setEvents(List<Event> events) {
		EventList.events = events;
	}
	public static void addEvent(Event evt){
		events.add(evt);
	}
	public static List<Event> getEventListForSpecifiedDate(String date){
		List<Event> results = new Vector<Event>();
		for( Event evt : events){
			if(evt.getDate().equals(date))
				results.add(evt);
		}
		return results;
	}
	public static List<Event> getEventListForSpecifiedMonth(String month){
		List<Event> results = new Vector<Event>();
		for( Event evt : events){
			
			String date = evt.getDate();
			System.out.println(date);
			if(date.charAt(1) == '-'){
				System.out.println(date.substring(2, 4).replaceAll("-", "") + " | " + month + "|");
				if (date.substring(2, 4).replaceAll("-", "").equals(month))
					results.add(evt);
			}
			else
				System.out.println(date.substring(3, 5).replaceAll("-", "") + " | " + month + "|");
				if (date.substring(3, 5).replaceAll("-", "").equals(month))
					System.out.println("################ TU #########");
					results.add(evt);
		}
		return results;
	}
	public static void printEvents(){
		for( Event x : events ){
			System.out.println(x.getDescription() + x.getPlace() + " " + x.getFrom() + " - " + x.getTo());
		}
	}
	public static List<Event> filterByDesc(String desc){
		
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getDescription().equals(desc))
				result.add(e);
		}
		return result;
	}
	public static List<Event> filterByPlace(String place){
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getPlace().equals(place))
				result.add(e);
		}
		return result;
	}
	public static List<Event> filterByFrom(String from){
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getFrom().equals(from))
				result.add(e);
		}
		return result;
	}
	public static List<Event> filterByTo(String to){
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getTo().equals(to))
				result.add(e);
		}
		return result;
	}
}