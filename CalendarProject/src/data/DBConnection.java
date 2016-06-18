package data;

import java.sql.*;

import logic.IncorrectPasswordException;

public class DBConnection {

	private Connection connect;
	private Statement statement;
	private ResultSet resultSet;
	private String password;

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
		this.password = password;
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
			System.out.println("Records from database:");
			while(resultSet.next()){
				String desc = resultSet.getString("description");
				String place = resultSet.getString("place");
				System.out.println("DESC: " + desc + " | PLACE: " + place);
			}	
		}
		catch(Exception e){
			System.out.println("Error: " + e);
		}
	}
}