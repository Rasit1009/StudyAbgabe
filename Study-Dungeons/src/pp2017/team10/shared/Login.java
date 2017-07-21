package pp2017.team10.shared;

import java.io.Serializable;
import java.util.ArrayList;

import pp2017.team10.shared.Messages;

public class Login extends Messages implements Serializable {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -8177275538000046658L;

	private String user;
	private char[] pass;
	private boolean added;
	private ArrayList<UserLogedIn> userlist = new ArrayList<UserLogedIn>();

	public Login(boolean added, ArrayList<UserLogedIn> userlist) {
		this.added = added;
		this.userlist = userlist;
	}

	public Login(String user, char[] pass) {
		this.user = user;
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}

	public char[] getPassword() {
		return pass;
	}

	public ArrayList<UserLogedIn> getList() {
		return userlist;
	}

}
