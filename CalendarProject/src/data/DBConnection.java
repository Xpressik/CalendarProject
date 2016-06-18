package data;

import java.sql.*;

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
	
	public DBConnection(String password) throws IncorrectPasswordException{
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/prokom", "root", password);
		    statement = connect.createStatement();
		}
		catch(SQLException e){
			throw new IncorrectPasswordException();
		}
		catch(ClassNotFoundException e){
			
		}
	}
	
	public void getData(){
		try{
			String query = "select * from events2";
			resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				
				EventList.addEvent(new Event(resultSet.getString("description"),resultSet.getString("place"), resultSet.getString("fromHour"), resultSet.getString("toHour"), resultSet.getString("date").replaceAll("-0", "-")));				
			}	
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
	}
}