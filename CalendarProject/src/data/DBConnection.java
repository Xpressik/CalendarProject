package data;

import java.sql.*;

public class DBConnection {

	private Connection connect;
	private Statement statement;
	private ResultSet resultSet;
	private String password;

	public DBConnection(String password){
		this.password = password;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		    connect = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/prokom", "root", "");
		   
		}
		catch(Exception e){
			System.out.println("Error: "+e);
		}
		System.out.println("connected");
	}
	
	public void getData(){
		try{
			String query = "select * from events";
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
/*
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
	public void getData(){
		try{
			String query = "select * from events";
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
}*/
