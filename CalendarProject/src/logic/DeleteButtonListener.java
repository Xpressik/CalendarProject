package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;

import data.Event;
import data.EventList;
import gui.DayList;

/**
 * Klasa nas�uchuj�ca. Implementuje mo�liwo�� usuwania wybranych wydarze�.
 * @author Dawid
 *
 */
public class DeleteButtonListener implements ActionListener {

	/**
	 * Lista wydarze� dla danego dnia
	 */
	private JList list;
	/**
	 * Lista wzsystkich wydarze�
	 */
	private List<Event> eventList;
	
	/**
	 * Konstruktor tworzy na stercie instancj� klasy DeleteButtonListener, kt�ra jest odpowiedzialna za usuniecie odpowienich wydarzen po klinki�ciu przez uzytkownika w przycisk Delete w oknie DayEvents.
	 * @param lst - lista wydarze� dla danego dnia
	 * @param eventList - lista wszystkich wydarze�
	 */
	public DeleteButtonListener (JList lst, List<Event> eventList){
		
		this.list = lst;
		this.eventList = eventList;
	}
	/**
	 * Metoda nas�uchuj�ca. W momencie wywo�ania usuwa wybrane wydarzenia.
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
