package pp2017.team10.shared;

public class Move extends Messages {

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	private static final long serialVersionUID = -1481934905749147097L;
	public int posx;
	public int posy;
	public int pID;

	public Move(int xPos, int yPos, int pID) {
		this.posx = xPos;
		this.posy = yPos;
		this.pID = pID;
	}

}
