package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import data.DataRepository;
import data.DbDataRepository;
import data.EventService;
import gui.Frame;
import gui.TerminalDisplay;
/**
 * Klasa, ktora odpowiada za uruchomienie aplikacji. Otwarcie okna kalendarza oraz pobranie wydarzen z bazy danych. 
 * @author Dawid
 *
 */
public class Main {
	
	private static int mode;
	/**
	 * Metoda main rozpoczyna dzialanie aplikacji pobierajac wydarzenia z bazydancyh oraz nastepnie wyswietlajac okno kalendarza.
	 * @param args - argumenty uruchomienia programu
	 * @throws IOException - moze wyrzuc wyjatek typu IOException
	 */
	public static void main(String[] args) throws IOException {
		
		DbDataRepository dbDataRepository = new DbDataRepository();
		try {
			dbDataRepository.loadFromDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EventService eventService = new EventService(dbDataRepository);

		System.out.println("Press 1 to open Calendar in full gui version \nPress 2 to open Calendar in emergency terminal version");
		
		Scanner input = new Scanner(System.in);
		mode = input.nextInt();
		
		System.out.println(mode);
		if (mode  == 1){
			Frame window = new Frame(eventService);
		}
		else if (mode == 2){
			TerminalDisplay terminal = new TerminalDisplay(eventService);
		}
		else {
			System.out.println("Unknown operation");
		}
	}

}