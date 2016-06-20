
package data;

import java.util.List;
import java.util.Vector;
/**
 * Klasa reprezentujaca liste wydarzen
 * @author Dawid 
 *
 */
public class EventList {
	/**
	 * Lista, ktora przechowuje wszystkie wydarzenia
	 */
	private static List<Event> events = new Vector<Event>();
	/**
	 * Zwraca liste wszystkich wydarzen
	 * @return events - lista wydarzen
	 */
	public static List<Event> getEvents() {
		return events;
	}
	/**
	 * Metoda umozliwiajaca usuwanie wydarzen z listy.
	 * @param evt - wydarzenie, ktore ma zostac usuniete z listy.
	 */
	public static void deleteEvent (Event evt){ 
		events.remove(evt);
	}
	/**
	 * Metoda umozliwiajaca dodawanie przekazanych wydarzen do listy events.
	 * @param evt - wydarzenie, ktore ma zostac dodane do listy.
	 */
	public static void addEvent(Event evt){
		events.add(evt);
	}
	/**
	 * Metoda sluzaca do filtrowania wydarzen. Zwraca wydarzenia, ktore maja odbyc sie dla podanej daty.
	 *
	 * @param date - data wydarzen
	 * @return List - lista wydarzen dla zadanej daty.
	 */
	public static List<Event> getEventListForSpecifiedDate(String date){
		List<Event> results = new Vector<Event>();
		for( Event evt : events){
			if(evt.getDate().equals(date))
				results.add(evt);
		}
		return results;
	}
	/**
	 * Metoda sluzaca do filtrowania wydarzen. Zwraca wydarzenia, ktore maja odbyc sie w ciagu zadanego miesiaca.
	 * @param month - numer miesiaca, w ktorym maja szukamy wydarzen
	 * @return List - zwraca liste wydarzen, dla szukanego miesiaca
	 */
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
	/**
	 * Metoda wyswietlajaca w konsoli wszytkie wydarzenia z listy.
	 */
	public static void printEvents(){
		for( Event x : events ){
			System.out.println(x.getDescription() + x.getPlace() + " " + x.getFrom() + " - " + x.getTo() + x.getReminder());
		}
	}
	/**
	 * Metoda sluzaca do filtrowania wydarzen. Zwraca wydarzenia, ktore zawieraja zadany opis
	 * @param desc - szukany opis wydarzen
	 * @return List - zwraca liste wydarzen o zadanym opisie.
	 */
	public static List<Event> filterByDesc(String desc){
		
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getDescription().equals(desc))
				result.add(e);
		}
		return result;
	}
	/**
	 * Metoda sluzaca do filtrowania wydarzen. Zwraca wydarzenia, ktore maja sie odbyc w wybranym miejscu
	 * @param place - szukane miejsce wydarzen
	 * @return List - zwraca liste wydarzen, ktore maja sie odbyc w wybranym miejscu
	 */
	public static List<Event> filterByPlace(String place){
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getPlace().equals(place))
				result.add(e);
		}
		return result;
	}
	/**
	 * Metoda sluzaca do filtrowania wydarzen. Zwraca wydarzenia, ktore zaczynaja sie o zadanej godzinie.
	 * @param from - godzina rozpoczecia wydarzenia
	 * @return List - zwraca liste wydarzen, ktore rozpoczynaja sie o wybranej godzinie.
	 */
	public static List<Event> filterByFrom(String from){
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getFrom().equals(from))
				result.add(e);
		}
		return result;
	}
	/**
	 * Metoda sluzaca do filtrowania wydarzen. Zwraca wydarzenia, ktore koncza sie o zadanej godzinie.
	 * @param to godzina zakoczenia wydarzenia
	 * @return List - zwraca liste wydarzen, ktore koncza sie o zadanej godzinie.
	 */
	public static List<Event> filterByTo(String to){
		List<Event> result = new Vector<Event>();
		for( Event e : events){
			if(e.getTo().equals(to))
				result.add(e);
		}
		return result;
	}
}