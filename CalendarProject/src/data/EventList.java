package data;

import java.util.List;
import java.util.Vector;
/**
 * Klasa reprezentuj�ca list� wydarze�
 * @author Dawid 
 *
 */
public class EventList {
	/**
	 * Lista, kt�ra przechowuje wszystkie wydarzenia
	 */
	private static List<Event> events = new Vector<Event>();
	/**
	 * Zwraca list� wszystkich wydarze�
	 * @return events - lista wydarze�
	 */
	public static List<Event> getEvents() {
		return events;
	}
	/**
	 * Metoda umo�liwiaj�ca usuwanie wydarze� z listy.
	 * @param evt - wydarzenie, kt�re ma zosta� usuni�te z listy.
	 */
	public static void deleteEvent (Event evt){ 
		events.remove(evt);
	}
	/**
	 * Metoda umo�liwiaj�ca dodawanie przekazanych wydarze� do listy events.
	 * @param evt - wydarzenie, kt�re ma zosta� dodane do listy.
	 */
	public static void addEvent(Event evt){
		events.add(evt);
	}
	/**
	 * Metoda s�u��ca do filtrowania wydarze�. Zwraca wydarzenia, kt�re maj� odby� si� dla podanej daty.
	 *
	 * @param date - data wydarze�
	 * @return List<Event> - lista wydarze� dla zadanej daty.
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
	 * Metoda s�u��ca do filtrowania wydarze�. Zwraca wydarzenia, kt�re maj� odby� si� w ci�gu zadanego miesi�ca.
	 * @param month - numer miesi�ca, w kt�rym maj� szukamy wydarze�
	 * @return List<Event> - zwraca list� wydarze�, dla szukanego miesi�ca
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
	 * Metoda wy�wietlaj�ca w konsoli wszytkie wydarzenia z listy.
	 */
	public static void printEvents(){
		for( Event x : events ){
			System.out.println(x.getDescription() + x.getPlace() + " " + x.getFrom() + " - " + x.getTo() + x.getReminder());
		}
	}
	/**
	 * Metoda s�u��ca do filtrowania wydarze�. Zwraca wydarzenia, kt�re zawieraj� zadany opis
	 * @param desc - szukany opis wydarze�
	 * @return List - zwraca list� wydarze� o zadanym opisie.
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
	 * Metoda s�u��ca do filtrowania wydarze�. Zwraca wydarzenia, kt�re maj� si� odby� w wybranym miejscu
	 * @param place - szukane miejsce wydarze�
	 * @return List - zwraca list� wydarze�, kt�re maj� si� odby� w wybranym miejscu
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
	 * Metoda s�u��ca do filtrowania wydarze�. Zwraca wydarzenia, kt�re zaczynaj� si� o zadanej godzinie.
	 * @param from - godzina rozpocz�cia wydarzenia
	 * @return List - zwraca list� wydarze�, ktore rozpoczynaj� si� o wybranej godzinie.
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
	 * Metoda s�u��ca do filtrowania wydarze�. Zwraca wydarzenia, kt�re ko�cz� si� o zadanej godzinie.
	 * @param to godzina zakoczenia wydarzenia
	 * @return List - zwraca list� wydarze�, kt�re ko�cz� si� o zadanej godzinie.
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