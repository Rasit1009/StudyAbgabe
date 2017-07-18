package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class PlayerAttackMessage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = 5006047215823560813L;

	private String user;

	public PlayerAttackMessage(String pID) {
		this.user = pID;
	}
	
	public String getUser() {
		return user;
	}
}
