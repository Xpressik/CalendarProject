package logic;

import java.util.List;
import java.sql.*;

import data.Event;
import data.EventList;
import logic.IncorrectPasswordException;

public class DBConnection {

	private Connection connect;
	private Statement statement;
	private ResultSet resultSet;

	public DBConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prokom", "root", "");
		      
		    statement = connect.createStatement();
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
	
	}
	
	public DBConnection(String password) throws IncorrectPasswordException, ClassNotFoundException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
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
	
	public void getData(){
		try{
			String query = "select * from events2";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				
				EventList.addEvent(new Event(resultSet.getString("description"),resultSet.getString("place"), resultSet.getString("fromHour"), resultSet.getString("toHour"), resultSet.getString("evtDate").replaceAll("-0", "-")));				
			}	
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
	}
	public void saveData(){
		try{
			String query = "INSERT INTO events2 VALUES (";
			List <Event> events  = EventList.getEvents();
			for(int i=0; i< events.size(); i++){
				query += 
				events.get(i).getDescription() + "," + 
				events.get(i).getPlace() + "," +
				events.get(i).getFrom() + "," + 
				events.get(i).getTo() + "," +
				events.get(i).getDate() + ");";
				statement.executeUpdate(query);
			}
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
	}
}