package logic;

import java.util.List;
import java.util.Locale;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import data.Event;
import data.EventList;
import logic.IncorrectPasswordException;
/**
 * Klasa odpowiedzialna za po��czenie z baz� danych <br>
 * oraz pobieranie i zapisywanie wydarze� w bazie danych.
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

	/**
	 * Domy�lny konstruktor, kt�ry tworzy instancj� klasy DBConnection. <br>
	 * Umo�liwia po��czenie si� z baz� danych przy pomocy domy�lnych danych tj:<br>
	 * adres bazy danych: localhost:3306 <br>
	 * nazwa bazy danych: prokom <br>
	 * nazwa u�ytkownika: root <br>
	 * haslo: "" <br>
	 * @throws IncorrectPasswordException
	 * @throws ClassNotFoundException
	 */
	public DBConnection() throws IncorrectPasswordException, ClassNotFoundException{
		
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
	}
	/**
	 * Konstruktor, kt�ry tworzy na stercie instancj� klasy DBConnection.<br>
	 * Umo�liwia po��czenie z baz� danych wykorzystuj�c przekazane informacje tj: nazw� bazy, nazw� u�ytkownika, haslo	
	 * @param dbData - przechowuje dane dost�pu do bazy danych.
	 * @throws IncorrectPasswordException
	 * @throws ClassNotFoundException
	 */
	public DBConnection(DBData dbData) throws IncorrectPasswordException, ClassNotFoundException{
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
	}
	/**
	 * Konstruktor, kt�ry tworzy na stercie instancj� klasy DBConnection.<br>
	 * Umo�liwia po��czenie z baz� danych wykorzystuj�c przekazane haslo
	 * @param password - haslo dostepu do bazy. 
	 * @throws IncorrectPasswordException
	 * @throws ClassNotFoundException
	 */
	public DBConnection(String password) throws IncorrectPasswordException, ClassNotFoundException{
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
	}
	/**
	 * Metoda pobieraj�ca wszystkie wydarzenia z bazy danych z tabeli events2.
	 * <br>W programie wywo�ywana podczas uruchamiania aby pobra� wszystkie wcze�niej utworzone wydarzenia. 
	 */
	public void getData(){
		try{
			String query = "select * from events2";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);

				EventList.addEvent(new Event(resultSet.getString("description"),resultSet.getString("place"), resultSet.getString("fromHour"), resultSet.getString("toHour"), resultSet.getString("evtDate").replaceAll("-0", "-"),  formatter.parse(resultSet.getString("reminder"))/* (java.util.Date)formatter.parse(resultSet.getString("reminder")) *//*.getDate("reminder")*/));				
			}	
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
	}
	/**
	 * Metoda zapisuj�ca wszystkie wydarzenia z Listy do bazy danych.<br>
	 * W programie jest wywo�ywana bezpo�rednio przed zamkni�ciem aplikacji aby zachowa� dane na sta�e. 
	 */
	public void saveData(){
		try{
			String query;
			query = "DELETE FROM `events2` WHERE 1";
			statement.executeUpdate(query);
			List <Event> events  = EventList.getEvents();
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