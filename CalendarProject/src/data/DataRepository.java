package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DataRepository implements IDataRepository {

	private List<Event> events = new Vector<Event>();

	@Override
	public void addEvent(Event event) {
		events.add(event);
	}

	@Override
	public void removeEvent(Event event) {
		events.remove(event);
	}

	@Override
	public List<Event> getEventsFromSpecifiedDay(String date) {
		// TODO Auto-generated method stub
		List<Event> results = new Vector<Event>();
		for( Event evt : events){
			if(evt.getDate().equals(date))
				results.add(evt);
		}
		return results;
	}

	@Override
	public List<Event> getEventsFromSpecifiedMonth(String month) {
		// TODO Auto-generated method stub
		List<Event> results = new Vector<Event>();
		for( Event evt : events){
			String date = evt.getDate();
			if(date.charAt(1) == '-'){
				if (date.substring(2, 4).replaceAll("-", "").equals(month))
					results.add(evt);
			}
			else
				if (date.substring(3, 5).replaceAll("-", "").equals(month))
					results.add(evt);
		}
		return results;
	}

	@Override
	public List<Event> getAllEvents() {
		// TODO Auto-generated method stub
		return events;
	}

	@Override
	public List<Event> filterWithDescription(String desc) {
		// TODO Auto-generated method stub
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getDescription().equals(desc))
				result.add(e);
		}
		return result;
	}

	@Override
	public List<Event> filterWithPlace(String place) {
		// TODO Auto-generated method stub
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getPlace().equals(place))
				result.add(e);
		}
		return result;
	}

	@Override
	public List<Event> filterWithStartHour(String from) {
		// TODO Auto-generated method stub
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getFrom().equals(from))
				result.add(e);
		}
		return result;
	}

	@Override
	public List<Event> filterWithEndHour(String to) {
		// TODO Auto-generated method stub
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getTo().equals(to))
				result.add(e);
		}
		return result;
	}

	
}
