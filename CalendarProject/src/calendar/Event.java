package calendar;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
	private String description;
	private String place;
	private String from;
	private String to;
	private String date;  // kazde wydazrzenie musi przechowywac date aby mozna bylo je odpowiednio wyswietlac 
	
//	Date from;
//	Date to;
//	String wodziesta = "20:40";           									/////////////////////////////////////////////////////////////////////////////////////////////////
//	SimpleDateFormat format = new SimpleDateFormat("HH:mm");				// TAK MO¯NA REPREZENTOWAÆ I PRZECHOWYWAÆ GODZINY ROZPOCZÊCIA I ZAKOÑCZENIA. ALE CZY JEST SENS //
//																			/////////////////////////////////////////////////////////////////////////////////////////////////
//	try {
//		Date date = format.parse(wodziesta);
//
//		// this is the uglier solution
//		System.out.println("The hour is: "+date.getHours() + ":" + date.getMinutes());
//	} catch (ParseException e) {
//		System.err.println("Couldn't parse string! "+e.getMessage());
//	}
	

	public Event(){
		setDescription(null);
		setPlace(null);
		setFrom(null);
		setTo(null);
		setDate(null);
	}
	public Event(String desc, String place, String from, String to, String date){
		this.setDescription(desc);
		this.setPlace(place);
		this.setFrom(from);
		this.setTo(to);
		this.setDate(date);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}