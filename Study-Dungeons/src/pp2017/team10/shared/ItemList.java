package pp2017.team10.shared;

public class ItemList {
	private int indexItem;
	private int posX;
	private int posY;
	private int itemID;
	
	public ItemList(int indexItem, int posX, int posY, int itemID){
		this.indexItem = indexItem;
		this.posX = posX;
		this.posY = posY;
		this.itemID = itemID;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getIndexItem() {
		return indexItem;
	}
}
