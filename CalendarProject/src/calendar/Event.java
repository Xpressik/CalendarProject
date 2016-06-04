package calendar;

import java.text.SimpleDateFormat;

public class Event {
	private String description;
	private String place;
//	private SimpleDateFormat from;
	//private SimpleDateFormat to; 

	
	public Event(){
		setDescription(null);
		setPlace(null);
		//setFrom(null);
		//setTo(null);
	}
	public Event(String desc, String place, SimpleDateFormat from, SimpleDateFormat to){
		this.setDescription(desc);
		this.setPlace(place);
	//	this.from = new SimpleDateFormat("HH-mm");
//		this.from.
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
}
