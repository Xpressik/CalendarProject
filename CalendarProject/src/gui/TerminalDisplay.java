package gui;

import java.io.IOException;
import java.util.Date;

import data.EventService;

public class TerminalDisplay {
	
	private int action;
	private EventService eventService;
	
	public TerminalDisplay(){
		
	}
	
	public void mainLoop(){
		
		System.out.println("Tryb awaryjny - tekstowy");
		do{
			try {
				action = System.in.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("1 -  CREATE EVENT");
			switch(action){
			case 1:
				int fromHour = 0, fromMinutes = 0, toHour = 0, toMinutes = 0;
				Date reminder = null;
				System.out.println("Enter date of event (yyyy-MM-dd):");
				
				break;
			case 2:
				break;
			case 3:
				break;
			}
			
		}while(action != 0);
	}
}
