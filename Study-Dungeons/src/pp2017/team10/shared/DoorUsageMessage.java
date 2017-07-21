package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class DoorUsageMessage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = 1903689929686383967L;
	private String user;

	public DoorUsageMessage(String username) {
		user = username;
	}
	
	public String getUser() {
		return user;
	}

}
