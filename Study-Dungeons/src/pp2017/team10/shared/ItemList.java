package pp2017.team10.shared;

public class ItemList {
	private int indexItem;
	private int posX;
	private int posY;
	private int itemID;
	private int level;
	
	public ItemList(int indexItem, int posX, int posY, int itemID, int level){
		this.indexItem = indexItem;
		this.posX = posX;
		this.posY = posY;
		this.itemID = itemID;
		this.level = level;
	}
	public int getLevel() {
		return level;
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
