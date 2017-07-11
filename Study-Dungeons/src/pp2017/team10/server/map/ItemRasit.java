 package pp2017.team10.server.map;

public class ItemRasit {

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	
	public int itemID;
	public int itemXPos;
	public int itemYPos;
	public int[][] itemPos = new int [5][5]; 
	
	public ItemRasit() {
	}

	public int setItem(int itemID) {
		this.itemID = itemPos[itemXPos][itemYPos];
		return itemID;
	}

}
