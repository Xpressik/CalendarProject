
package data;

import java.util.Date;
/**
 * Klasa reprezentuj¹ca pojedyncze wydarzenie.<br> 
 * Zawiera takie informacje jak:<br>
 *  - opis wydarzenia<br> 
 *  - miejsce wydarzenia<br> 
 *  - godzinê rozpoczêcia <br> 
 *  - godzinê zakoñczenia <br> 
 *  - datê wydarzenia <br> 
 *  - informacje o przypomnieniu
 * 
 */
public class Event {
	/**
	 * Opis wydarzenia
	 */
	private String description;
	/**
	 * Miejsce w którym ma siê odbyæ wydarzenie
	 */
	private String place;
	/**
	 * Godzina rozpoczêcia wydarzenia
	 */
	private String from;
	/**
	 * Godzina zakoñczenia wydarzenia
	 */
	private String to;
	/**
	 * Data wydarzenia
	 */
	private String date;
	/**
	 * Data oraz godzina, podczas której ma wyst¹piæ powiadomienie o wydarzeniu
	 */
	private Date reminder;
	/**
	 * Domyœlny konstruktor, który tworzy instacjê klasy Event, czyli zdarzenie.
	 */
	public Event(){
		description = null;
		place = null;
		from = null;
		to = null;
		date = null;				
	}
	/**
	 * Konstruktor, który tworzy instacjê klasy Event ustawiaj¹c stosowne parametry : 
	 * @param desc - opis wydarzenia 
	 * @param place - miejsce wydarzenia
	 * @param from - godzina rozpoczêcia
	 * @param to - godzina zakoñczenia 
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
	 * @return String from - zwraca godzinê rozpoczêcia wydarzenia
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * Getter pola to
	 * @return String to - zwraca godzinê zakoñczenia wydarzenia
	 */
	public String getTo() {
		return to;
	}
	/**
	 * Getter pola date
	 * @return String date - zwraca datê wydarzenia
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Getter pola reminder
	 * @return Date reminder - zwraca datê oraz godzinê wydarzenia 
	 */
	public Date getReminder(){
		return this.reminder;
	}
	/**
	 * Zwraca godzinê zakoñczenia wydarzenia typu Date
	 * @return Date to - godzina zakoñczenia wydarzenia typu Date
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