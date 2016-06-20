package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Listener, który obs³uguje klikniêcie przycisku Save w oknie ustawieñ PreferencesWindow.
 * @author Dawid
 *
 */
public class SaveButtonListener implements ActionListener {

	/**
	 * Pole, w które u¿ytkownik wpisuje haslo dostêpu do bzy dancyh
	 */
	private JPasswordField passwordField;
	/**
	 * Pole, w które u¿ytkownik wpisuje nazwê u¿ytkownika bazy danych 
	 */
	private JTextField textField;
	/**
	 * Pole, w które u¿ytkownik wpisuje nazwê bazy danych 
	 */
	private JTextField textField_1;
	
	/**
	 * Konstruktor tworzzy na stercie obiekt klasy SaveButtonListener.<br>
	 * Ustawiaj¹c odpowiednie parametry przekazane z klasy, któr¹ nas³uchuje. Tak aby móg³ odpowiednio zareagowaæ na zainsta³¹ sytuacjê.
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
	 * Metoda nas³uchuj¹ca. Podczas wywo³ania pobiera z okien nazwê u¿ytkownika, nazwê bazy oraz has³o i zapisuje je do pliku txt aby przy ka¿dym kolejny uruchomieniu program móg³ pobraæ dane z bazy.
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
