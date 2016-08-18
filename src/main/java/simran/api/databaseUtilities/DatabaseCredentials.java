package simran.api.databaseUtilities;

public class DatabaseCredentials {

	private static DatabaseCredentials credentialInstance;
	private String username ;
	private String password;
	private String dbUrl;
	
	public DatabaseCredentials(){
		
	}
	
	public DatabaseCredentials(String username, String password, String dbUrl){
		 this.username = username;
		 this.password = password;
		 this.dbUrl = dbUrl;	
	}
	public static DatabaseCredentials getInstance(String username, String password, String dbUrl){
		
	if(credentialInstance == null){
	credentialInstance = new DatabaseCredentials(username, password, dbUrl);
	}
	
	return credentialInstance;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
}
