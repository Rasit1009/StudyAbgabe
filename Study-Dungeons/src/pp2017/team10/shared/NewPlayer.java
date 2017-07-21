package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class NewPlayer extends Messages {
	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private char[] pw;
	private boolean added;
	private int levelID;

	public NewPlayer(String name, char[] pw, int levelID) {
		user = name;
		this.pw = pw;
		this.levelID = levelID;
	}

	public String getUser() {
		return user;
	}

	public char[] getPw() {
		return pw;
	}

	public NewPlayer(String name, boolean added) {
		this.user = name;
		this.added = added;
	}

	public boolean getAdded() {
		return added;
	}
}