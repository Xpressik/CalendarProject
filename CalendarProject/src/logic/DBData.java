package logic;

public class DBData {
	
	private String dbName;
	private String dbPassword;
	private String dbUser;
	
	public DBData(String name, String user, String pass){
		dbName = name;
		dbPassword = pass;
		dbUser = user;
	}
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
}
