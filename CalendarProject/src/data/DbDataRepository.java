package data;

import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

import logic.DBConnection;
import logic.DBData;

public class DbDataRepository extends DataRepository {
	private DBConnection dbConnection = null;
	
	public void loadFromDatabase() throws Exception {
		if (dbConnection == null) {
			DBData dbData = null;
			Scanner in = new Scanner(new File("dbData.txt"));
			try{
				dbData = new DBData(in.nextLine(),in.nextLine(),in.nextLine());
				in.close();
				dbConnection = new DBConnection(dbData, this);
			}
			catch(Exception e){
			}
		}
		try{
			removeAllEvents();
			dbConnection.getData();	
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "There has been some problems with database.\nOr you have typed wrong data.", "Database failure", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void saveToDatabase() throws Exception {
		if (dbConnection == null) {
			DBData dbData = null;
			try{
				Scanner in = new Scanner(new File("dbData.txt"));
				dbData = new DBData(in.nextLine(),in.nextLine(),in.nextLine());
				in.close();

				dbConnection = new DBConnection(dbData, this);
			}
			catch(Exception e){
				
			}
		}
		try{
			dbConnection.saveData();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "There has been some problems with database.\nOr you have typed wrong data.", "Database failure", JOptionPane.WARNING_MESSAGE);
		}
	}
}
