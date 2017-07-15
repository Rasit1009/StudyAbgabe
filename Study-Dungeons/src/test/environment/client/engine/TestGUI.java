package test.environment.client.engine;

import pp2017.team10.client.engine.ClientEngine;
import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.Cheat;
import pp2017.team10.shared.DoorUsage;
import pp2017.team10.shared.ItemUsage;
import pp2017.team10.shared.Messages;
import pp2017.team10.shared.Move;
import pp2017.team10.shared.PlayerAttack;

public class TestGUI {
	/**
	 * In this class we are simulating the different types of Messages we have
	 * (e.g. "use the door" "use an Item" etc.) we are handling the Requests by
	 * distinguishing their type of Message, then we create a Request Message
	 * that we send to our Server by an ObjetcOutputStream
	 * 
	 * @author Güven, Rasit Matnr: 6019617
	 */

	public ClientEngine g;
	public static Messages msg;
	public static boolean doors = true;
	public DoorUsage use;
	public static String content = "maxHP";
	public static int pID = 12;
	public static int frpID = 21;
	public static int itemID = 1;
	public static int attack = 100;
	public static int defense = 100;
	public static int posx = 2;
	public static int posy = 1;

	
	public static void main(String[] args) {
		ClientEngine neu = new ClientEngine();
	
	/*	DoorUsage DoorMsg = new DoorUsage(doors);
		neu.handleRequests(DoorMsg);

		ItemUsage ItemMsg = new ItemUsage(pID, itemID,true);
		neu.handleRequests(ItemMsg);

		PlayerAttack AttackMsg = new PlayerAttack(pID, attack, defense);
		neu.handleRequests(AttackMsg);
		neu.handleResponse(AttackMsg);
		
		ChatMessage ChatMsg = new ChatMessage(content, pID,frpID);
		neu.handleRequests(ChatMsg);
		
		Move MoveMsg = new  Move(posx, posy, pID);
		neu.handleRequests(MoveMsg);
		
		Cheat cheat = new Cheat(content,pID);
		cheat.handleCheat(content);
		*/

		/*
		 * https://www.mkyong.com/java/how-to-read-an-object-from-file-in-java/
		 * (used to test if the object read is the right message (in this case
		 * Attack Message)
		 */
		
		Messages obj = new Messages();
		Messages read = obj.readObject("actionInfo.ser");
		System.out.println(read);

	}
}
