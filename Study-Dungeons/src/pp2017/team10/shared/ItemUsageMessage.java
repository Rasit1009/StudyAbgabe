package pp2017.team10.shared;

public class ItemUsageMessage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -4490895966355240307L;

	public int itemID;
	public String user;

	public ItemUsageMessage(int itemID, String user) {
		this.itemID = itemID;
		this.user = user;
	}

}
