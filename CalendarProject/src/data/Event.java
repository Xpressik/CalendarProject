
package data;

import java.util.Date;
/**
 * Klasa reprezentujaca pojedyncze wydarzenie.<br> 
 * Zawiera takie informacje jak:<br>
 *  - opis wydarzenia<br> 
 *  - miejsce wydarzenia<br> 
 *  - godzine rozpoczecia <br> 
 *  - godzine zakonczenia <br> 
 *  - date wydarzenia <br> 
 *  - informacje o przypomnieniu
 * 
 */
public class Event {
	/**
	 * Opis wydarzenia
	 */
	private String description;
	/**
	 * Miejsce w ktorym ma sie odbyc wydarzenie
	 */
	private String place;
	/**
	 * Godzina rozpoczecia wydarzenia
	 */
	private String from;
	/**
	 * Godzina zakonczenia wydarzenia
	 */
	private String to;
	/**
	 * Data wydarzenia
	 */
	private String date;
	/**
	 * Data oraz godzina, podczas ktorej ma wystapic powiadomienie o wydarzeniu
	 */
	private Date reminder;
	/**
	 * Domyslny konstruktor, ktory tworzy instacje klasy Event, czyli zdarzenie.
	 */
	public Event(){
		description = null;
		place = null;
		from = null;
		to = null;
		date = null;				
	}
	/**
	 * Konstruktor, ktory tworzy instacje klasy Event ustawiajac stosowne parametry : 
	 * @param desc - opis wydarzenia 
	 * @param place - miejsce wydarzenia
	 * @param from - godzina rozpoczecia
	 * @param to - godzina zakonczenia 
	 * @param date - data wydarzenia 
	 * @param reminder - data oraz godzina powiadomienia
	 */
	public Event(String desc, String place, String from, String to, String date, Date reminder){
		this.description = desc;
		this.place = place;
		this.from = from;
		this.to = to;
		this.date = date;
		this.reminder = reminder;
	}
	/**
	 * Getter pola description
	 * @return String description - opis zadarzenia
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Getter pola place.
	 * @return String place - miejsce wydarzenia
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * Getter pola from
	 * @return String from - zwraca godzine rozpoczecia wydarzenia
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * Getter pola to
	 * @return String to - zwraca godzine zakonczenia wydarzenia
	 */
	public String getTo() {
		return to;
	}
	/**
	 * Getter pola date
	 * @return String date - zwraca date wydarzenia
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Getter pola reminder
	 * @return Date reminder - zwraca date oraz godzine wydarzenia 
	 */
	public Date getReminder(){
		return this.reminder;
	}
	/**
	 * Zwraca godzine zakonczenia wydarzenia typu Date
	 * @return Date to - godzina zakonczenia wydarzenia typu Date
	 */
	@SuppressWarnings("deprecation")
	public Date getToDate(){
		Date format;
		format = new Date();
		format.parse(getTo());
		return format;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @param reminder the reminder to set
	 */
	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}
}