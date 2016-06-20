package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Listener, ktary obsluguje klikniecie przycisku Save w oknie ustawien PreferencesWindow.
 * @author Dawid
 *
 */
public class SaveButtonListener implements ActionListener {

	/**
	 * Pole, w ktore uzytkownik wpisuje haslo dostepu do bazy dancych
	 */
	private JPasswordField passwordField;
	/**
	 * Pole, w ktore uzytkownik wpisuje nazwe uzytkownika bazy danych 
	 */
	private JTextField textField;
	/**
	 * Pole, w ktore uzytkownik wpisuje nazwe bazy danych 
	 */
	private JTextField textField_1;
	
	/**
	 * Konstruktor tworzy na stercie obiekt klasy SaveButtonListener.<br>
	 * Ustawiajac odpowiednie parametry przekazane z klasy, ktara nasluchuje. Tak aby mogl odpowiednio zareagowac na zainstala sytuacje.
	 * @param textField_1 - nazwa bazy danych
	 * @param textField - nazwa uzytkownika bazy danych
	 * @param passwordField - haslo dostepu do bazy danych
	 */
	public SaveButtonListener( JTextField textField_1, JTextField textField, JPasswordField passwordField)
	{
		this.passwordField = passwordField;
		this.textField = textField;
		this.textField_1 = textField_1;
	}
	/**
	 * Metoda nasluchujaca. Podczas wywolania pobiera z okien nazwe uzytkownika, nazwe bazy oraz haslo i zapisuje je do pliku txt aby przy kazdym kolejny uruchomieniu program mogl pobrac dane z bazy.
	 */
	public void actionPerformed(ActionEvent arg0) {
		char [] input = passwordField.getPassword();
		String inputString;
		if(input.length == 0)
			inputString = "";
		else
			inputString = input.toString();
		
		DBData data = new DBData(textField_1.getText(), textField.getText(), inputString);
		try{
			PrintWriter toFile = new PrintWriter("dbData.txt");
			toFile.println(data.getDbName());
			toFile.println(data.getDbUser());
			toFile.println(data.getDbPassword());
			toFile.close();
		}
		catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "There has been some problems with file.\n Try again", "File not found", JOptionPane.WARNING_MESSAGE);
		}
		
	}

}
