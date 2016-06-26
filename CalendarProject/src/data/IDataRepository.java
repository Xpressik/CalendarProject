package data;

import java.util.List;
/**
 * Intefejs ktory zawiera metody potrzebne do obslugi repozytorium.<br> 
 * 
 */
public interface IDataRepository {
	
	//	add & remove
	/**
	 * Dodaje do repozytorium liste wydarzen
	 *
	 * @param events - lista wydarzen ktora chcemu dodac do repozytorium
	 */
	public void addEvents(List<Event> events);
	/**
	 * Dodanie eventu do repozytorium
	 * @param event - element ktory dodajemy do repozytorium
	 */
	public void addEvent(Event event);
	/**
	 * Zwraca z repozytorium liste eventow ktore odbywaja sie w podanej dacie 
	 *
	 * @param event - zdarzenie ktore usuwamy z repozytorium
	 */
	public void removeEvent(Event event);
	/**
	 * Usuwa wszystkie eventy z repozytorium
	 */
	public void removeAllEvents();
	
	//	get
	/**
	 * Zwraca z repozytorium liste eventow ktore odbywaja sie w podanej dacie 
	 *
	 * @param date - data dla ktorej zwracamy liste eventow z repozytorium
	 * @return lista zdarzen
	 */
	public List<Event> getEventsFromSpecifiedDay(String date);
	/**
	 * Zwraca liste eventow w danym miesiacu
	 *
	 * @param month - miesiac dla ktorego zwracamy liste eventow
	 * @return lista zdarzen
	 */
	public List<Event> getEventsFromSpecifiedMonth(String month);
	/**
	 * Zwraca liste wszystkich wydarzen w repozytorium
	 * @return lista zdarzen
	 */
	public List<Event> getAllEvents();
	
	//	filter
	/** Zwraca liste evnetow z podana deskrypcja
	 *
	 * @param desc - string ktory podaje fragment opisu wydarzenia. Opis wszystkich wydarzen bedzie porownywany z tym parametrem w poszukiwaniu zgodnosci
	 * @return lista zdarzen
	 */
	public List<Event> filterWithDescription(String desc);
	/**
	 * Zwraca liste eventow ktore odbywaja sie w danym miejscu
	 *
	 * @param place - string ktory opisuje miejsce wydarzenia. Miejsce odbywania kazdego wydarzenia bedzie porownywany z tym parametrem w poszukiwaniu zgodnosci
	 * @return lista zdarzen
	 */
	public List<Event> filterWithPlace(String place);
	/**
	 * Zwraca liste eventow ktore rozpoczynaja sie o danej godzinie
	 *
	 * @param from - string ktory okresla godzine rozpoczecia wydarzenia. Godzina rozpoczecia kazdego wydarzenia bedzie porownywana z tym parametrem w poszukiwaniu zgodnosci
	 * @return lista zdarzen
	 */
	public List<Event> filterWithStartHour(String from);
	/**
	 * Zwraca liste ktora przechowuje wszystkie eventy w repozytorium
	 * @param to - Sring ktory okresla godzine zakonczenia wydarzenia
	 * @return lista zdarzen
	 */
	public List<Event> filterWithEndHour(String to);
	

}