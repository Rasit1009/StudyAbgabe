package pp2017.team10.server.engine;

/**
 * Author: Felix Schifferdecker, 5585147
 */

/*
 * Klasse um die Highscore zu definieren
 */

public class Highscore {
	public int pos;
	public String user;
	public int score;
	
	public Highscore(String username, int scored){
		this.score = scored;
		this.user = username;
	}
	
	public Highscore(String user, int score, int pos){
		this.user = user;
		this.score = score;
		this.pos = pos;
	}
}
