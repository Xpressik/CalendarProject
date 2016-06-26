package data;

import java.util.Date;
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
	
	public Date createReminder(String eventDate, String fromHour, Integer remindIndex);
	public List<Event> removeEventByIdx(String eventDate, Integer idx);
	
	public void loadEventsFromXmlFile(String filename);
	public void saveEventsToXmlFile(String filename);
	
	public void loadEventsFromDatabase();
	public void saveEventsToDatabase();
	
	public boolean hasXmlDataRepository();
	public boolean hasDbDataRepository();
}