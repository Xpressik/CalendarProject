package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JList;

import data.Event;
import data.EventList;
import gui.DayList;

/**
 * Klasa nas³uchuj¹ca. Implementuje mo¿liwoœæ usuwania wybranych wydarzeñ.
 * @author Dawid
 *
 */
public class DeleteButtonListener implements ActionListener {

	/**
	 * Lista wydarzeñ dla danego dnia
	 */
	private JList list;
	/**
	 * Lista wzsystkich wydarzeñ
	 */
	private List<Event> eventList;
	
	/**
	 * Konstruktor tworzy na stercie instancjê klasy DeleteButtonListener, która jest odpowiedzialna za usuniecie odpowienich wydarzen po klinkiêciu przez uzytkownika w przycisk Delete w oknie DayEvents.
	 * @param lst - lista wydarzeñ dla danego dnia
	 * @param eventList - lista wszystkich wydarzeñ
	 */
	public DeleteButtonListener (JList lst, List<Event> eventList){
		
		this.list = lst;
		this.eventList = eventList;
	}
	/**
	 * Metoda nas³uchuj¹ca. W momencie wywo³ania usuwa wybrane wydarzenia.
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
