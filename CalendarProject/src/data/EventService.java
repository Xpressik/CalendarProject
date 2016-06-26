package data;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

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

	@Override
	public void loadEventsFromXmlFile(String filename) {
		if (filename == null || "".equals(filename)) {
			System.out.println("No file name selected");
			return;
		}
		Path path = Paths.get(filename);
		if (!Files.exists(path) || Files.isDirectory(path)) {
			JOptionPane.showMessageDialog(null, "File does not exist or is a directory.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		XmlDataRepository xmlDataRepository = new XmlDataRepository(filename);
		try {
			xmlDataRepository.loadFromXmlFile();	
		} catch(Exception ex) {
			ex.printStackTrace();
			return;
		}
		JOptionPane.showMessageDialog(null, "Events imported properly.", "Success", JOptionPane.INFORMATION_MESSAGE);
		this.repository = xmlDataRepository;
	}

	@Override
	public void saveEventsToXmlFile(String filename) {
		XmlDataRepository xmlDataRepository = null;
		if (hasXmlDataRepository()) {
			xmlDataRepository = (XmlDataRepository)this.repository;
		} else {
			if (filename == null || "".equals(filename)) {
				System.out.println("No file name selected");
				return;
			}
			Path path = Paths.get(filename);
			if (Files.isDirectory(path)) {
				System.out.println("File is a directory");
				return;
			}
			xmlDataRepository = new XmlDataRepository(filename);
			xmlDataRepository.addEvents(this.repository.getAllEvents());
		}
		try {
			xmlDataRepository.saveToXmlFile();
		} catch(Exception ex) {
			ex.printStackTrace();
			return;
		}
		this.repository = xmlDataRepository;
	}

	@Override
	public boolean hasXmlDataRepository() {
		return this.repository instanceof XmlDataRepository;
	}

	@Override
	public boolean hasDbDataRepository() {
		return this.repository instanceof DbDataRepository;
	}
	
	@Override
	public void loadEventsFromDatabase() {
		DbDataRepository dbDataRepository = null;
		if (hasDbDataRepository()) {
			dbDataRepository = (DbDataRepository)this.repository;
		} else {
			dbDataRepository = new DbDataRepository();
		}
		try {
			dbDataRepository.loadFromDatabase();	
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
		this.repository = dbDataRepository;
	}

	@Override
	public void saveEventsToDatabase() {
		DbDataRepository dbDataRepository = null;
		if (hasDbDataRepository()) {
			dbDataRepository = (DbDataRepository)this.repository;
		} else {
			dbDataRepository = new DbDataRepository();
			dbDataRepository.addEvents(this.repository.getAllEvents());
		}
		try {
			dbDataRepository.saveToDatabase();	
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
		
		this.repository = dbDataRepository;
	}

	
}
