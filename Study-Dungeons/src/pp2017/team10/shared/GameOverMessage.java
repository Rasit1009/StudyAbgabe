package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class GameOverMessage extends Messages {
	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = 3630130623292812974L;
	private String user;

	public String getUser() {
		return user;
	}

	public GameOverMessage(String username) {
		user = username;
	}

}
