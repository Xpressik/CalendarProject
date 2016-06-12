package data;

public class Event {
	private String description;
	private String place;
	private String from;
	private String to;
	private String date; 
		
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