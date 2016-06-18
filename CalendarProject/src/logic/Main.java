package logic;

import gui.Frame;

public class Main {

	public static void main(String[] args) {
		
		DBConnection dbCon = new DBConnection();
		dbCon.getData();
		Frame window = new Frame();
		
	}

}