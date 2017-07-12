package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class Move extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -1481934905749147097L;
	public int posx;
	public int posy;
	public String user;

	public Move(int xPos, int yPos, String pID) {
		this.posx = xPos;
		this.posy = yPos;
		this.user = pID;
	}

}
