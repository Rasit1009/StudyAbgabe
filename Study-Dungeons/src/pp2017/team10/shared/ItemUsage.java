package pp2017.team10.shared;

public class ItemUsage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -4490895966355240307L;

	public int itemID;
	public String user;
	public int posx;
	public int posy;
	public boolean pickup;

	public ItemUsage(int itemID, String user, boolean pickup) {
		this.itemID = itemID;
		this.user = user;
		this.pickup = pickup;
	}

	public ItemUsage(int posx, int posy, boolean pickup) {
		this.posx = posx;
		this.posy = posy;
		this.pickup = pickup;
	}

}
