package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class MoveMessage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -1481934905749147097L;
	private int posx;
	private int posy;
	private String user;

	public MoveMessage(int xPos, int yPos, String pID) {
		this.posx = xPos;
		this.posy = yPos;
		this.user = pID;
	}
	
	public String getUser() {
		return user;
	}
	
	public int getPosX() {
		return posx;
	}
	
	public int getPosY() {
		return posy;
	}
	
	

}
