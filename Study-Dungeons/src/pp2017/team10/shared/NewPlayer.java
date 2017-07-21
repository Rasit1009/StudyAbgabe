package pp2017.team10.shared;

import java.io.Serializable;

import pp2017.team10.shared.Messages;

public class NewPlayer extends Messages implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4876875526789291303L;
	/**
	 * Author: Felix Schifferdecker, 5585147
	 */

	private String user;
	private char[] pw;
	private boolean added;


	public NewPlayer(String name, char[] password) {
		user = name;
		pw = password;
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
