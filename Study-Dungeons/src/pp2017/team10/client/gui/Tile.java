package pp2017.team10.client.gui;

public class Tile {

	int groundType;
	int itemType;
	boolean visibleField;

	public Tile(int a, int b, boolean c) {
		groundType = a;
		itemType = b;
		visibleField = c;

	}

	public void setGround(int a) {
		groundType = a;
	}

	public int getGround() {
		return groundType;
	}

	public void setItemType(int b) {
		itemType = b;
	}

	public int getItemType() {
		return itemType;
	}

	public void setVisibleField(boolean c) {
		visibleField = c;
	}

	public boolean getVisibleField() {
		return visibleField;
	}

}
