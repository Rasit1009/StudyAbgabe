package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class Logout extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -4856081702058723109L;
	public String textMessage;
	public String user;

	public Logout(String clientID) {
		user = clientID;
	}

	public String getText() {
		return this.textMessage;
	}

	public Logout(String clientID, String abschiedNachricht) {
		user = clientID;
		this.textMessage = abschiedNachricht;
	}

}
