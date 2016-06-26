package data;

import java.util.List;

public interface IDataRepository {
	
	//	add & remove
	public void addEvents(List<Event> events);
	public void addEvent(Event event);
	public void removeEvent(Event event);
	public void removeAllEvents();
	
	//	get
	public List<Event> getEventsFromSpecifiedDay(String date);
	public List<Event> getEventsFromSpecifiedMonth(String month);
	public List<Event> getAllEvents();
	
	//	filter
	public List<Event> filterWithDescription(String desc);
	public List<Event> filterWithPlace(String place);
	public List<Event> filterWithStartHour(String from);
	public List<Event> filterWithEndHour(String to);
	

}