package logic;


/** 
 * Klasa reprezenujaca wyjatek IncorrectPasswordException.<br>
 * Wyjatek wykorzysywany przy sprawdzaniu poprawnosci hasel.<br>
 * Sygnalizuje, ze wprowadzone haslo jest niepoprawne.
 */
public class IncorrectPasswordException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Domyslny konstruktor, ktory tworzy instacje klasy IncorrectPasswordException
	 */
	public IncorrectPasswordException(){
		
	}
	/**
	 * Konstruktor tworzacy instancje klasy IncorrectPasswordException przy uzyciu String msg.
	 * @param msg - wiadomosc zawierajaca informacje o wyjatku.
	 */
	public IncorrectPasswordException(String msg){
		super(msg);
	}
}
