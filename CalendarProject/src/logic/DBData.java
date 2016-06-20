package logic;
/**
 * Klasa przechowuj¹ca dane dostêpu do bazy danych tj:<br>
 *  - nazwa bazy danych
 *  - nazwa u¿ytkownika
 *  - has³o dostêpu
 *
 * @author Dawid
 */
public class DBData {
	/**
	 * Pole typu String.<br>Przechowuje nazwê bazy danych.
	 */
	private String dbName;
	/**
	 * Pole typu String.<br>Przechowuje nazwê has³o dostêpu do bazy.
	 */
	private String dbPassword;
	/**
	 * Pole typu String.<br>Przechowuje nazwê u¿ytkownika.
	 */
	private String dbUser;
	
	/**
	 * Konstruktor, który tworzy na stercie instacjê klasy DBData przechowuj¹cej dane dostêpu do bazy.
	 * @param name - String zawieraj¹cy nazwê bazy danych
	 * @param user - String zawieraj¹cy nazwê u¿ytkownika
	 * @param pass - String zawieraj¹cy has³o dostêpu
	 */
	public DBData(String name, String user, String pass){
		dbName = name;
		dbPassword = pass;
		dbUser = user;
	}
	/**
	 * Zwraca String zawieraj¹cy nazwê bazy danych
	 * @return String dbName
	 */
	public String getDbName() {
		return dbName;
	}
	/**
	 * Zwraca String zawieraj¹cy has³o dostêpu do bazy dancyh
	 * @return String dbPassword
	 */
	public String getDbPassword() {
		return dbPassword;
	}
	/**
	 * Zwraca String zawieraj¹cy nazwê u¿ytkownika 
	 * @return String dbUser
	 */
	public String getDbUser() {
		return dbUser;
	}
}
