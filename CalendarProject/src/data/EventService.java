package data;

import java.util.List;

public class EventService implements IEventService {

	private DataRepository repository;
	
	public EventService(DataRepository dataRepository){
		setDataRepository(dataRepository);
	}
	
	@Override
	public void setDataRepository(DataRepository dataRepository) {
		// TODO Auto-generated method stub
		this.repository = dataRepository;
	}

	@Override
	public void addEvent(Event event) {
		// TODO Auto-generated method stub
		repository.addEvent(event);
	}

	@Override
	public void removeEvent(Event event) {
		// TODO Auto-generated method stub
		repository.removeEvent(event);
	}

	@Override
	public List<Event> getEventsFromSpecifiedDay(String date) {
		// TODO Auto-generated method stub
		return repository.getEventsFromSpecifiedDay(date);
	}

	@Override
	public List<Event> getEventsFromSpecifiedMonth(String month) {
		// TODO Auto-generated method stub
		return repository.getEventsFromSpecifiedMonth(month);
	}

	@Override
	public List<Event> filterWithDescription(String desc) {
		// TODO Auto-generated method stub
		return repository.filterWithDescription(desc);
	}

	@Override
	public List<Event> filterWithPlace(String place) {
		// TODO Auto-generated method stub
		return repository.filterWithPlace(place);
	}

	@Override
	public List<Event> filterWithStartHour(String from) {
		// TODO Auto-generated method stub
		return repository.filterWithStartHour(from);
	}

	@Override
	public List<Event> filterWithEndHour(String to) {
		// TODO Auto-generated method stub
		return repository.filterWithEndHour(to);
	}

	@Override
	public List<Event> getAllEvents() {
		// TODO Auto-generated method stub
		return repository.getAllEvents();
	}

}
