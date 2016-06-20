
package data;

import java.util.Date;
/**
 * Klasa reprezentuj�ca pojedyncze wydarzenie.<br> 
 * Zawiera takie informacje jak:<br>
 *  - opis wydarzenia<br> 
 *  - miejsce wydarzenia<br> 
 *  - godzin� rozpocz�cia <br> 
 *  - godzin� zako�czenia <br> 
 *  - dat� wydarzenia <br> 
 *  - informacje o przypomnieniu
 * 
 */
public class Event {
	/**
	 * Opis wydarzenia
	 */
	private String description;
	/**
	 * Miejsce w kt�rym ma si� odby� wydarzenie
	 */
	private String place;
	/**
	 * Godzina rozpocz�cia wydarzenia
	 */
	private String from;
	/**
	 * Godzina zako�czenia wydarzenia
	 */
	private String to;
	/**
	 * Data wydarzenia
	 */
	private String date;
	/**
	 * Data oraz godzina, podczas kt�rej ma wyst�pi� powiadomienie o wydarzeniu
	 */
	private Date reminder;
	/**
	 * Domy�lny konstruktor, kt�ry tworzy instacj� klasy Event, czyli zdarzenie.
	 */
	public Event(){
		description = null;
		place = null;
		from = null;
		to = null;
		date = null;				
	}
	/**
	 * Konstruktor, kt�ry tworzy instacj� klasy Event ustawiaj�c stosowne parametry : 
	 * @param desc - opis wydarzenia 
	 * @param place - miejsce wydarzenia
	 * @param from - godzina rozpocz�cia
	 * @param to - godzina zako�czenia 
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
	 * @return String from - zwraca godzin� rozpocz�cia wydarzenia
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * Getter pola to
	 * @return String to - zwraca godzin� zako�czenia wydarzenia
	 */
	public String getTo() {
		return to;
	}
	/**
	 * Getter pola date
	 * @return String date - zwraca dat� wydarzenia
	 */
	public String getDate() {
		return date;
	}
	/**
	 * Getter pola reminder
	 * @return Date reminder - zwraca dat� oraz godzin� wydarzenia 
	 */
	public Date getReminder(){
		return this.reminder;
	}
	/**
	 * Zwraca godzin� zako�czenia wydarzenia typu Date
	 * @return Date to - godzina zako�czenia wydarzenia typu Date
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