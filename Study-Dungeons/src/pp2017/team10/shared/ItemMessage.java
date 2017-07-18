package pp2017.team10.shared;

import java.util.ArrayList;

/*
 * @author Felix
 * 
 * Die Datenstruktur fuer unsere Items werden hier erstellt
 * 
 * */

public class ItemMessage extends Messages{
	private ArrayList<ItemList> item;
	
	public ItemMessage(ArrayList<ItemList> item) {
		this.item = item;
	}
	
	public ArrayList<ItemList> getItemList(){
		return item;
	}
}