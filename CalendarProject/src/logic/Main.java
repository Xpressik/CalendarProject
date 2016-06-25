package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import data.DataRepository;
import data.EventService;
import gui.Frame;
/**
 * Klasa, ktora odpowiada za uruchomienie aplikacji. Otwarcie okna kalendarza oraz pobranie wydarzen z bazy danych. 
 * @author Dawid
 *
 */
public class Main {
	/**
	 * Metoda main rozpoczyna dzialanie aplikacji pobierajac wydarzenia z bazydancyh oraz nastepnie wyswietlajac okno kalendarza.
	 * @param args - argumenty uruchomienia programu
	 */
	public static void main(String[] args) {
		
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

		Frame window = new Frame(dbData, eventService);
	}

}