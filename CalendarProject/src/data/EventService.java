package data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService implements IEventService {
	
	private static final Map<Integer, Integer> mapping = new HashMap<Integer, Integer>(){{
 		put(0, 0);
    put(5, 5);
 		put(10, 10);
 		put(15, 15);
 		put(30, 30);
 		put(1, 60);
 		put(2, 120);
 		put(6, 360);
 		put(12, 720);	
 	}};

	private DataRepository repository;
	
	public EventService(DataRepository dataRepository){
		setDataRepository(dataRepository);
	}
	
	@Override
	public void setDataRepository(DataRepository dataRepository) {
		this.repository = dataRepository;
	}

	@Override
	public void addEvent(Event event) {
		repository.addEvent(event);
	}

	@Override
	public void removeEvent(Event event) {
		repository.removeEvent(event);
	}

	@Override
	public List<Event> getEventsFromSpecifiedDay(String date) {
		return repository.getEventsFromSpecifiedDay(date);
	}

	@Override
	public List<Event> getEventsFromSpecifiedMonth(String month) {
		return repository.getEventsFromSpecifiedMonth(month);
	}

	@Override
	public List<Event> filterWithDescription(String desc) {
		return repository.filterWithDescription(desc);
	}

	@Override
	public List<Event> filterWithPlace(String place) {
		return repository.filterWithPlace(place);
	}

	@Override
	public List<Event> filterWithStartHour(String from) {
		return repository.filterWithStartHour(from);
	}

	@Override
	public List<Event> filterWithEndHour(String to) {
		return repository.filterWithEndHour(to);
	}

	@Override
	public List<Event> getAllEvents() {
		return repository.getAllEvents();
	}

	@Override
	public Date createReminder(String datestr, String fromHourStr, Integer remindIndex) {
		LocalDate eventDate = LocalDate.parse(datestr);
		
		String[] strs = fromHourStr.split(":");
		Integer fromHour = Integer.valueOf(strs[0]);
		Integer fromMin = Integer.valueOf(strs[1]);
		
		LocalDateTime ldt = LocalDateTime.of(eventDate.getYear(), eventDate.getMonth(), eventDate.getDayOfMonth(), 0, 0);
		ldt = ldt.withHour(fromHour).withMinute(fromMin).minusMinutes(mapping.get(remindIndex));
		
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public List<Event> removeEventByIdx(String eventDate, Integer idx) {
		List<Event> events = getEventsFromSpecifiedDay(eventDate);
		Event event = events.get(idx);
		if (event != null) {
			removeEvent(event);
		}
		return getEventsFromSpecifiedDay(eventDate);
	}

}
