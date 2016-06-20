package logic;
/**
 * Klasa przechowuj�ca dane dost�pu do bazy danych tj:<br>
 *  - nazwa bazy danych
 *  - nazwa u�ytkownika
 *  - has�o dost�pu
 *
 * @author Dawid
 */
public class DBData {
	/**
	 * Pole typu String.<br>Przechowuje nazw� bazy danych.
	 */
	private String dbName;
	/**
	 * Pole typu String.<br>Przechowuje nazw� has�o dost�pu do bazy.
	 */
	private String dbPassword;
	/**
	 * Pole typu String.<br>Przechowuje nazw� u�ytkownika.
	 */
	private String dbUser;
	
	/**
	 * Konstruktor, kt�ry tworzy na stercie instacj� klasy DBData przechowuj�cej dane dost�pu do bazy.
	 * @param name - String zawieraj�cy nazw� bazy danych
	 * @param user - String zawieraj�cy nazw� u�ytkownika
	 * @param pass - String zawieraj�cy has�o dost�pu
	 */
	public DBData(String name, String user, String pass){
		dbName = name;
		dbPassword = pass;
		dbUser = user;
	}
	/**
	 * Zwraca String zawieraj�cy nazw� bazy danych
	 * @return String dbName
	 */
	public String getDbName() {
		return dbName;
	}
	/**
	 * Zwraca String zawieraj�cy has�o dost�pu do bazy dancyh
	 * @return String dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}
	/**
	 * Zwraca String zawieraj�cy nazw� u�ytkownika 
	 * @return String dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}
}
