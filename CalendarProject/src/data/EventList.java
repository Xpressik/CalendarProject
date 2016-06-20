package data;

import java.util.List;
import java.util.Vector;
/**
 * Klasa reprezentuj¹ca listê wydarzeñ
 * @author Dawid 
 *
 */
public class EventList {
	/**
	 * Lista, która przechowuje wszystkie wydarzenia
	 */
	private static List<Event> events = new Vector<Event>();
	/**
	 * Zwraca listê wszystkich wydarzeñ
	 * @return events - lista wydarzeñ
	 */
	public static List<Event> getEvents() {
		return events;
	}
	/**
	 * Metoda umo¿liwiaj¹ca usuwanie wydarzeñ z listy.
	 * @param evt - wydarzenie, które ma zostaæ usuniête z listy.
	 */
	public static void deleteEvent (Event evt){ 
		events.remove(evt);
	}
	/**
	 * Metoda umo¿liwiaj¹ca dodawanie przekazanych wydarzeñ do listy events.
	 * @param evt - wydarzenie, które ma zostaæ dodane do listy.
	 */
	public static void addEvent(Event evt){
		events.add(evt);
	}
	/**
	 * Metoda s³u¿¹ca do filtrowania wydarzeñ. Zwraca wydarzenia, które maj¹ odbyæ siê dla podanej daty.
	 *
	 * @param date - data wydarzeñ
	 * @return List<Event> - lista wydarzeñ dla zadanej daty.
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
	 * Metoda s³u¿¹ca do filtrowania wydarzeñ. Zwraca wydarzenia, które maj¹ odbyæ siê w ci¹gu zadanego miesi¹ca.
	 * @param month - numer miesi¹ca, w którym maj¹ szukamy wydarzeñ
	 * @return List<Event> - zwraca listê wydarzeñ, dla szukanego miesi¹ca
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
	 * Metoda wyœwietlaj¹ca w konsoli wszytkie wydarzenia z listy.
	 */
	public static void printEvents(){
		for( Event x : events ){
			System.out.println(x.getDescription() + x.getPlace() + " " + x.getFrom() + " - " + x.getTo() + x.getReminder());
		}
	}
	/**
	 * Metoda s³u¿¹ca do filtrowania wydarzeñ. Zwraca wydarzenia, które zawieraj¹ zadany opis
	 * @param desc - szukany opis wydarzeñ
	 * @return List - zwraca listê wydarzeñ o zadanym opisie.
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
	 * Metoda s³u¿¹ca do filtrowania wydarzeñ. Zwraca wydarzenia, które maj¹ siê odbyæ w wybranym miejscu
	 * @param place - szukane miejsce wydarzeñ
	 * @return List - zwraca listê wydarzeñ, które maj¹ siê odbyæ w wybranym miejscu
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
	 * Metoda s³u¿¹ca do filtrowania wydarzeñ. Zwraca wydarzenia, które zaczynaj¹ siê o zadanej godzinie.
	 * @param from - godzina rozpoczêcia wydarzenia
	 * @return List - zwraca listê wydarzeñ, ktore rozpoczynaj¹ siê o wybranej godzinie.
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
	 * Metoda s³u¿¹ca do filtrowania wydarzeñ. Zwraca wydarzenia, które koñcz¹ siê o zadanej godzinie.
	 * @param to godzina zakoczenia wydarzenia
	 * @return List - zwraca listê wydarzeñ, które koñcz¹ siê o zadanej godzinie.
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