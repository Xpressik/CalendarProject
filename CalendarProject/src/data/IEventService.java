package data;

import java.util.List;

public interface IEventService {
	
	public void setDataRepository(DataRepository dataRepository);

	public void addEvent(Event event);
	public void removeEvent(Event event);
	
	public List<Event> getEventsFromSpecifiedDay(String date);
	public List<Event> getEventsFromSpecifiedMonth(String month);
	public List<Event> filterWithDescription(String desc);
	public List<Event> filterWithPlace(String place);
	public List<Event> filterWithStartHour(String from);
	public List<Event> filterWithEndHour(String to);
	
	public List<Event> getAllEvents();
	
	
	
	
}
