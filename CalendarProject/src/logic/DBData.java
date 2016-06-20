package logic;
/**
 * Klasa przechowujaca dane dostepu do bazy danych tj:<br>
 *  - nazwa bazy danych
 *  - nazwa uzytkownika
 *  - haslo dostepu
 *
 * @author Dawid
 */
public class DBData {
	/**
	 * Pole typu String.<br>Przechowuje nazwe bazy danych.
	 */
	private String dbName;
	/**
	 * Pole typu String.<br>Przechowuje nazwe haslo dostepu do bazy.
	 */
	private String dbPassword;
	/**
	 * Pole typu String.<br>Przechowuje nazwe uzytkownika.
	 */
	private String dbUser;
	
	/**
	 * Konstruktor, ktory tworzy na stercie instacje klasy DBData przechowujacej dane dostepu do bazy.
	 * @param name - String zawierajacy nazwe bazy danych
	 * @param user - String zawierajacy nazwe uzytkownika
	 * @param pass - String zawierajacy haslo dostepu
	 */
	public DBData(String name, String user, String pass){
		dbName = name;
		dbPassword = pass;
		dbUser = user;
	}
	/**
	 * Zwraca String zawierajacy nazwe bazy danych
	 * @return String dbName
	 */
	public String getDbName() {
		return dbName;
	}
	/**
	 * Zwraca String zawierajacy haslo dostepu do bazy dancyh
	 * @return String dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}
	/**
	 * Zwraca String zawierajacy nazwe uzytkownika 
	 * @return String dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}
}
