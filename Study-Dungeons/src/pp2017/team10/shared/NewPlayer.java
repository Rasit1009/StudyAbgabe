package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class NewPlayer extends Messages {
	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private String pw;
	private boolean added;

	public NewPlayer(String name, String password) {
		user = name;
		pw = password;
	}

	public String getUser() {
		return user;
	}

	public String getPw() {
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
