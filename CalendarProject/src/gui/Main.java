package gui;

import data.DBConnection;

public class Main {

	public static void main(String[] args) {
		
		Frame window = new Frame();
		//Test.init();
		DBConnection test = new DBConnection();
		test.getData();
	}

}