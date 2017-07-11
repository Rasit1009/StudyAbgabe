package pp2017.team10.shared;

import pp2017.team10.client.engine.ClientEngineGUI;

public class ItemUsage extends Messages {

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	private static final long serialVersionUID = -4490895966355240307L;

	public int itemID;
	public int pID;
	public int posx;
	public int posy;
	public boolean isAvailabe;

	public ItemUsage(int itemID, int pID, boolean isAvailable){
	this.itemID = itemID;
	this.pID = pID;
	}
	

}
