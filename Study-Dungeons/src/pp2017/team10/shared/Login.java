package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class Login extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -8177275538000046658L;

	private String user;
	private String pass;

	public Login(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return pass;
	}

}
