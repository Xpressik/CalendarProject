package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import data.DataRepository;
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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		DataRepository dataRepository = new DataRepository();
		EventService eventService = new EventService(dataRepository);
		DBData dbData = null;
		try{
		    Scanner in = new Scanner(new File("dbData.txt"));
		    dbData = new DBData(in.nextLine(),in.nextLine(),in.nextLine());
		    in.close();
		}
		catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "There has been some problems with file.\n Try again", "File not found", JOptionPane.WARNING_MESSAGE);
		}
		
		DBConnection dbCon;
		try {
			dbCon = new DBConnection(dbData, eventService);
			dbCon.getData();
			
		} catch (IncorrectPasswordException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "There has been some problems with database.\nOr you have typed wrong data.", "Database failure", JOptionPane.WARNING_MESSAGE);

		}

		System.out.println("Press 1 to open Calendar in full gui version \nPress 2 to open Calendar in emergency terminal version");
		mode = new Scanner(System.in).nextInt();
		System.out.println(mode);
		if (mode  == 1){
			Frame window = new Frame(dbData, eventService);
		}
		else if (mode == 2){
			TerminalDisplay terminal = new TerminalDisplay(eventService);
		}
		else {
			System.out.println("Unknown operation");
		}
	}

}