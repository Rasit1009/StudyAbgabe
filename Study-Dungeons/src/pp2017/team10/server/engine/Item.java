package pp2017.team10.server.engine;

public class Item {

	public int itemID;
	public int itemXPos;
	public int itemYPos;
	public int[][] itemPos = new int[5][5];

	public Item() {
	}

	public int setItem(int itemID) {
		this.itemID = itemPos[itemXPos][itemYPos];
		return itemID;
	}

}
