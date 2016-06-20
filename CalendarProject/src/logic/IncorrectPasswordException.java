package logic;


/** 
 * Klasa reprezenuj�ca wyj�tek IncorrectPasswordException.<br>
 * Wyj�tek wykorzysywany przy sprawdzaniu poprawno�ci hase�.<br>
 * Sygnalizuje, �e wprowadzone has�o jest niepoprawne.
 */
public class IncorrectPasswordException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Domy�lny konstruktor, kt�ry tworzy instacj� klasy IncorrectPasswordException
	 */
	public IncorrectPasswordException(){
		
	}
	/**
	 * Konstruktor tworz�cy instancj� klasy IncorrectPasswordException przy u�yciu String msg.
	 * @param msg - wiadomo�� zawieraj�ca informacje o wyj�tku.
	 */
	public IncorrectPasswordException(String msg){
		super(msg);
	}
}
