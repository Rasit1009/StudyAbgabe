package pp2017.team10.shared;

import java.io.Serializable;

/**
 * Author: Felix Schifferdecker, 5585147
 */
/*
 * Klasse um die eingeloggten User zu definieren, inklusiv aller Spielerwerte
 * und Items und den jeweiligen Get- und Set-Methoden
 */

public class UserLogedIn extends Messages implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1294154414794374409L;
	private int health;
	private int[] items;
	private String user;
	private int score;
	private boolean gotKey;
	private int userID;
	private int userPosX;
	private int userPosY;
	private int damage;
	private int levelDone;
	private int levelNow;
	private UserLogedIn userlist;

	public UserLogedIn(int health, int[] items, String user, int score, boolean gotKey, int userID, int userPosX,
			int userPosY, int damage, int levelDone, int levelNow) {

		this.health = health;
		this.items = items;
		this.user = user;
		this.score = score;
		this.gotKey = gotKey;
		this.userID = userID;
		this.userPosX = userPosX;
		this.userPosY = userPosY;
		this.damage = damage;
		this.levelDone = levelDone;
		this.levelNow = levelNow;
	}

	public void setLevelNow(int levelNow) {
		this.levelNow = levelNow;
	}

	public int getLevelNow() {
		return levelNow;
	}

	public void setLevelDone(int levelDone) {
		this.levelDone = levelDone;
	}

	public int getLevelDone() {
		return levelDone;
	}

	public int getUserID() {
		return userID;
	}

	public String getUser() {
		return user;
	}

	public int[] getItems() {
		return items;
	}

	public int getHealth() {
		return health;
	}

	public int getScore() {
		return score;
	}

	public boolean getGotKey() {
		return gotKey;
	}

	public int getUserPosX() {
		return userPosX;
	}

	public int getUserPosY() {
		return userPosY;
	}

	public int getDamage() {
		return damage;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setItems(int[] items) {
		this.items = items;
	}

	public void setGotKey(boolean gotKey) {
		this.gotKey = gotKey;
	}

	public void setUserPosX(int userPosX) {
		this.userPosX = userPosX;
	}

	public void setUserPosY(int userPosY) {
		this.userPosY = userPosY;
	}

	public UserLogedIn getUserLogedIn() {
		return userlist;
	}

}
