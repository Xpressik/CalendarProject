package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gui.Frame;
/**
 * Klasa, kt�ra odpowiada za uruchomienie aplikacji. Otwarcie okna kalendarza oraz pobranie wydarze� z bazy danych. 
 * @author Dawid
 *
 */
public class Main {
	/**
	 * Metoda main rozpoczyna dzia�anie aplikacji pobieraj�c wydarzenia z bazydancyh oraz nast�pnie wy�wietlaj�c okno kalendarza.
	 * @param args
	 */
	public static void main(String[] args) {
		
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
			dbCon = new DBConnection(dbData);
			dbCon.getData();
			
		} catch (IncorrectPasswordException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "There has been some problems with database.\nOr you have typed wrong data.", "Database failure", JOptionPane.WARNING_MESSAGE);

		}
		
		Frame window = new Frame(dbData);
	}

}