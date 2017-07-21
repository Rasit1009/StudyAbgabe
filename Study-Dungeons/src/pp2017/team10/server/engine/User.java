package pp2017.team10.server.engine;

/**
 * Author: Felix Schifferdecker, 5585147
 */
//Klasse um den User zu definieren
public class User {
	private String user;
	private String password;
	private int levelDone;
	
	public User(String username, String pw, int levelDone){
		this.user = username;
		this.password = pw;
		this.levelDone = levelDone;
	}
	
	public int getLevelDone() {
		return levelDone;
	}
	
	public String getUser(){
		return this.user;
	}
	
	public String getPassword(){
		return this.password;
	}
}
