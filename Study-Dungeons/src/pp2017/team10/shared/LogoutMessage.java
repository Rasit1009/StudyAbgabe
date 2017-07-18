package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class LogoutMessage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -4856081702058723109L;
	private String user;

	public LogoutMessage(String clientID) {
		user = clientID;
	}
	
	public String getUser() {
		return user;
	}

}
