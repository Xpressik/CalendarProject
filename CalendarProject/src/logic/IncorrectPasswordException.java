package logic;

public class IncorrectPasswordException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IncorrectPasswordException(){
		
	}
	public IncorrectPasswordException(String msg){
		super(msg);
	}
}
