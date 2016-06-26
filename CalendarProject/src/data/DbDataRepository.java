package data;

import java.io.File;
import java.util.Scanner;

import logic.DBConnection;
import logic.DBData;

public class DbDataRepository extends DataRepository {
	private DBConnection dbConnection = null;
	
	public void loadFromDatabase() throws Exception {
		if (dbConnection == null) {
			DBData dbData = null;
	    Scanner in = new Scanner(new File("dbData.txt"));
	    dbData = new DBData(in.nextLine(),in.nextLine(),in.nextLine());
	    in.close();
			
			dbConnection = new DBConnection(dbData, this);
		}
		removeAllEvents();
		dbConnection.getData();		
	}
	
	public void saveToDatabase() throws Exception {
		if (dbConnection == null) {
			DBData dbData = null;
	    Scanner in = new Scanner(new File("dbData.txt"));
	    dbData = new DBData(in.nextLine(),in.nextLine(),in.nextLine());
	    in.close();
			
			dbConnection = new DBConnection(dbData, this);
		}
		dbConnection.saveData();
	}
}
