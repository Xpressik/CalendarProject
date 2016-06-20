package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;

import data.Event;
import data.EventList;
import gui.DayList;

/**
 * Klasa nasluchujaca. Implementuje mozliwosc usuwania wybranych wydarzen.
 * @author Dawid
 *
 */
public class DeleteButtonListener implements ActionListener {

	/**
	 * Lista wydarzen dla danego dnia
	 */
	private JList list;
	/**
	 * Lista wzsystkich wydarzen
	 */
	private List<Event> eventList;
	
	/**
	 * Konstruktor tworzy na stercie instancje klasy DeleteButtonListener, ktora jest odpowiedzialna za usuniecie odpowienich wydarzen po klinkieciu przez uzytkownika w przycisk Delete w oknie DayEvents.
	 * @param lst - lista wydarzen dla danego dnia
	 * @param eventList - lista wszystkich wydarzen
	 */
	public DeleteButtonListener (JList lst, List<Event> eventList){
		
		this.list = lst;
		this.eventList = eventList;
	}
	/**
	 * Metoda nasluchujaca. W momencie wywolania usuwa wybrane wydarzenia.
	 */
	public void actionPerformed(ActionEvent e) {
		
		int selectedEventIndex = list.getSelectedIndex();
		try{
			EventList.deleteEvent(eventList.get(selectedEventIndex));

			eventList.remove(selectedEventIndex);
			DayList.repaintList(list, eventList);
		}
		catch(Exception e1){

		}
	}

}
