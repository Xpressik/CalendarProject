package calendar;

import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.corba.se.pept.transport.Connection;
//import com.sun.xml.internal.fatinfoset.sax.Properties;

public class DBConnection {

	Connection connect = null;
	Statement statement = null;
	ResultSet resultSer = null;

	public DBConnection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = (Connection) DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/testDb/", "root", "");
		      //root:@127.0.0.1:3306:
		}
		catch(Exception e){
			System.out.println("Error: "+e);
		}
	}
}
