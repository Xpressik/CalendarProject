package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Listener, kt�ry obs�uguje klikni�cie przycisku Save w oknie ustawie� PreferencesWindow.
 * @author Dawid
 *
 */
public class SaveButtonListener implements ActionListener {

	/**
	 * Pole, w kt�re u�ytkownik wpisuje haslo dost�pu do bzy dancyh
	 */
	private JPasswordField passwordField;
	/**
	 * Pole, w kt�re u�ytkownik wpisuje nazw� u�ytkownika bazy danych 
	 */
	private JTextField textField;
	/**
	 * Pole, w kt�re u�ytkownik wpisuje nazw� bazy danych 
	 */
	private JTextField textField_1;
	
	/**
	 * Konstruktor tworzzy na stercie obiekt klasy SaveButtonListener.<br>
	 * Ustawiaj�c odpowiednie parametry przekazane z klasy, kt�r� nas�uchuje. Tak aby m�g� odpowiednio zareagowa� na zainsta�� sytuacj�.
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
	 * Metoda nas�uchuj�ca. Podczas wywo�ania pobiera z okien nazw� u�ytkownika, nazw� bazy oraz has�o i zapisuje je do pliku txt aby przy ka�dym kolejny uruchomieniu program m�g� pobra� dane z bazy.
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
