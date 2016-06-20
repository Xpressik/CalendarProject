package logic;


/** 
 * Klasa reprezenuj¹ca wyj¹tek IncorrectPasswordException.<br>
 * Wyj¹tek wykorzysywany przy sprawdzaniu poprawnoœci hase³.<br>
 * Sygnalizuje, ¿e wprowadzone has³o jest niepoprawne.
 */
public class IncorrectPasswordException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Domyœlny konstruktor, który tworzy instacjê klasy IncorrectPasswordException
	 */
	public IncorrectPasswordException(){
		
	}
	/**
	 * Konstruktor tworz¹cy instancjê klasy IncorrectPasswordException przy u¿yciu String msg.
	 * @param msg - wiadomoœæ zawieraj¹ca informacje o wyj¹tku.
	 */
	public IncorrectPasswordException(String msg){
		super(msg);
	}
}
