package pp2017.team10.shared;

import java.util.LinkedList;

/*
 * @author Burcu Akcay, @author Rasit Güven
 * 
 * Die Datenstruktur für unsere Items werden hier erstellt
 * 
 * */

public class Item {

	public int itemID;
	public String itemName;
	public int itemEffect;
	public int health = 70;
	public int key;
	public int itemXPos;
	public int itemYPos;
	public int[][] itemPos;

	public Item(int id, String name) {
		this.itemID = id;
		this.itemName = name;

	}

	public Item() {

	}

	/*
	 * Linkedlist für Items wird erstellt, falls wir später weitere Items
	 * hinzufügen wollen, wird uns dies erleichtert, da wir die Items dann mit
	 * deren itemIDs aufrufen können
	 */
	LinkedList<Item> items = new LinkedList<Item>();

	/*
	 * erstmals werden trunk und key in die Linkedlist aufgenommen
	 */

	public void add(int itemID, int itemName) {

		items.add(new Trunk(1, "trunk"));
		items.add(new Key(2, "key"));

	}

	public int getItemID() { // gibt die itemID zurück
		return itemID;
	}

	public void setItemID(int itemID) { // setzt die itemID
		this.itemID = itemID;
	}

	public int setItemPos(int itemID) {
		this.itemID = itemPos[itemXPos][itemYPos];
		return itemID;
	}

	public String getItemName() { // gibt den itemNamen zurück
		return itemName;
	}

	public void setItemName(String itemName) { // setzt den itemNamen
		this.itemName = itemName;
	}

	public int getItemEffect() { // gibt den itemEffect zurück
		return itemEffect;
	}

	public void setItemEffect(int itemEffect) { // setzt den itemEffect
		this.itemEffect = itemEffect;
	}

	/*
	 * durch den eingenommenen Item wird health des Players um 20 Einheiten
	 * erhöht. Für den späteren Verlauf kann unser itemEffect erweitert werden
	 * und fortgeschrittene Items können dazu können, die dann neu berechnet
	 * werden müssen
	 * 
	 */

	public void itemEffect(int itemID) {

		if (itemID == 1)
			;
		{
			itemEffect = health + 20;
			System.out.println("itemEffect ist" + itemEffect);

		}
	}

	/*
	 * Item wird aufgehoben und aus der Liste entfernt, wenn die itemID
	 * identisch mit der id ist
	 */

	public void pickupItem(int id) {

		for (int i = 1; i == id; i++) {
			if (itemID == id) {
				items.remove(itemID);
			}
		}

	}

	public static void main(String[] args) {

		Item test = new Item();
		test.itemEffect(1);

	}
}
