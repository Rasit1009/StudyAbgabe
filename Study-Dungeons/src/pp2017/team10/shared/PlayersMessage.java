package pp2017.team10.shared;

import java.util.ArrayList;

public class PlayersMessage extends Messages {

	private ArrayList<UserLogedIn> user;

	public PlayersMessage(ArrayList<UserLogedIn> user) {
		this.user = user;
	}

	public ArrayList<UserLogedIn> getUser() {
		return user;
	}

}
