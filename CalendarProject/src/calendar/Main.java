package calendar;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		
		Frame window = new Frame();
		
		String wodziesta = "20:40";
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");

		try {
			Date date = format.parse(wodziesta);

			// this is the uglier solution
			System.out.println("The hour is: "+date.getHours() + ":" + date.getMinutes());
		} catch (ParseException e) {
			System.err.println("Couldn't parse string! "+e.getMessage());
		}
	}

}
