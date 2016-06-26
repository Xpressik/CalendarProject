package logic;

import java.util.List;
import java.util.Locale;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import data.DataRepository;
import data.Event;
import data.EventService;
import logic.IncorrectPasswordException;
/**
 * Klasa odpowiedzialna za polaczenie z baza danych <br>
 * oraz pobieranie i zapisywanie wydarzen w bazie danych.
 * @author Dawid
 *
 */
public class DBConnection {
	/*
	 * 
	 */
	private Connection connect;
	/*
	 * 
	 */
	private Statement statement;
	/*
	 * 
	 */
	private ResultSet resultSet;
	
	private DataRepository dataRepository;

	/**
	 * Domyslny konstruktor, ktory tworzy instancje klasy DBConnection. <br>
	 * Umozliwia polaczenie sie z baza danych przy pomocy domyslnych danych tj:<br>
	 * adres bazy danych: localhost:3306 <br>
	 * nazwa bazy danych: prokom <br>
	 * nazwa uzytkownika: root <br>
	 * haslo: "" <br>
	 * @param dataRepository - obiekt typu DataRepository ktory inicjujemy.<br>
	 * @throws IncorrectPasswordException - wlasny wyjatek rzucany gdy podane haslo jest niepoprawne.<br>
	 * @throws ClassNotFoundException - wyjatek rzucany gdy wystapi blad podczas laczenia z baza danych.<br>
	 */
	public DBConnection(DataRepository dataRepository) throws IncorrectPasswordException, ClassNotFoundException{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prokom", "root", "");
		      
		    statement = connect.createStatement();
		}
		catch(SQLException e){
			throw new IncorrectPasswordException();
		}
		catch(ClassNotFoundException e){
			throw new ClassNotFoundException();
		}
		
		this.dataRepository = dataRepository;
	}
	/**
	 * Konstruktor, ktory tworzy na stercie instancje klasy DBConnection.<br>
	 * Umozliwia polaczenie z baza danych wykorzystujac przekazane informacje tj: nazwe bazy, nazwe uzytkownika, haslo	
	 * @param dbData - przechowuje dane dostepu do bazy danych.
	 * @param dataRepository - obiekt typu DataRepository
	 * @throws IncorrectPasswordException - wlasny wyjatek rzucany jesli podane haslo jest niepoprawne
	 * @throws ClassNotFoundException wyjatek rzucany gdy wystapi blad podczas laczenia z baza danych
	 */
	public DBConnection(DBData dbData, DataRepository dataRepository) throws IncorrectPasswordException, ClassNotFoundException{
		try{
			Class.forName("com.mysql.jdbc.Driver");			
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbData.getDbName(), dbData.getDbUser(), dbData.getDbPassword());
		    statement = connect.createStatement();
		}
		catch(SQLException e){
			throw new IncorrectPasswordException();
		}
		catch(ClassNotFoundException e){
			throw new ClassNotFoundException();
		}
		this.dataRepository = dataRepository;
	}
	/**
	 * Konstruktor, ktory tworzy na stercie instancje klasy DBConnection.<br>
	 * Umozliwia polaczenie z baza danych wykorzystujac przekazane haslo
	 * @param password - haslo dostepu do bazy. 
	 * @param dataRepository - obiekt typu DataRepository
	 * @throws IncorrectPasswordException  wlasny wyjatek rzucany jesli podane haslo jest niepoprawne
	 * @throws ClassNotFoundException wyjatek rzucany gdy wystapi blad podczas laczenia z baza danych
	 */
	public DBConnection(String password, DataRepository dataRepository) throws IncorrectPasswordException, ClassNotFoundException{
		try{
			Class.forName("com.mysql.jdbc.Driver");			
			//connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName, dbUser, password);
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prokom", "root", password);
		    statement = connect.createStatement();
		}
		catch(SQLException e){
			throw new IncorrectPasswordException();
		}
		catch(ClassNotFoundException e){
			throw new ClassNotFoundException();
		}
		
		this.dataRepository = dataRepository;
	}
	/**
	 * Metoda pobierajaca wszystkie wydarzenia z bazy danych z tabeli events2.
	 * <br>W programie wywolywana podczas uruchamiania aby pobrac wszystkie wczesniej utworzone wydarzenia. 
	 */
	public void getData(){
		try{
			String query = "select * from events2";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);

				dataRepository.addEvent(new Event(resultSet.getString("description"),resultSet.getString("place"), resultSet.getString("fromHour"), resultSet.getString("toHour"), resultSet.getString("evtDate").replaceAll("-0", "-"),  formatter.parse(resultSet.getString("reminder"))/* (java.util.Date)formatter.parse(resultSet.getString("reminder")) *//*.getDate("reminder")*/));				
			}	
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
	}
	/**
	 * Metoda zapisujaca wszystkie wydarzenia z Listy do bazy danych.<br>
	 * W programie jest wywolywana bezposrednio przed zamknieciem aplikacji aby zachowac dane na stale. 
	 */
	public void saveData(){
		try{
			String query;
			query = "DELETE FROM `events2` WHERE 1";
			statement.executeUpdate(query);
			List<Event> events  = dataRepository.getAllEvents();
			for(int i=0; i< events.size(); i++){
				query = "INSERT INTO events2 (description, place, fromHour, toHour, evtDate, reminder) VALUES (";
				query += 
				"'" + events.get(i).getDescription() + "', " + 
				"'" + events.get(i).getPlace() + "', " +
				"'" + events.get(i).getFrom() + "', " + 
				"'" + events.get(i).getTo() + "', " +
				"'" + events.get(i).getDate() + "', " +
				"'" + events.get(i).getReminder().toString() + "' "
					+ ");";
				statement.executeUpdate(query);
			}
			connect.close();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		catch(Exception e){
			System.out.println(e.toString());
		}

	}
}