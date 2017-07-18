package pp2017.team10.shared;

public class ItemAddMessage extends Messages {

	private String user;
	private int itemID;
	private int count;
	
	public ItemAddMessage(int itemID, int count, String user) {
		this.user = user;
		this.itemID = itemID;
		this.count = count;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public int getCount() {
		return count;
	}
	
	public String getUser() {
		return user;
	}
}
