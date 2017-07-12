package pp2017.team10.client.engine;

import pp2017.team10.shared.Character;
import pp2017.team10.shared.Item;
import pp2017.team10.shared.Messages;

import java.io.IOException;

import pp2017.team10.client.gui.spielwelt;

/**
 * 
 * This class is mainly used to move the player and check the consistency. it
 * also has the method "handleRequests" which is used to distinguish the
 * different types of requests we are having (Chat Message, Player Attack etc.).
 * 
 * @author Güven, Rasit Matnr: 6019617
 *
 */

public class ClientEngineGUI {
	public int posx;
	public int posy;
	public boolean isPossible;
	public Item item = new Item();
	public int charPos;
	public boolean isAvailable;
	public int[][] Map;
	public Character character;


	public static void main(String[]args) throws IOException{
		spielwelt spiel = new spielwelt();
		spiel.show();
	}
	
	public ClientEngineGUI() {

	}

	public void getCharInfo() {

		posx = character.getPosX();
		posy = character.getPosY();
	}

	/*
	 * This method is responsible for the movement of the character. We get the
	 * direction from the GUI and handle the pressed button. Then we set the new
	 * Position and print them on the console.
	 */

	public void movement(String direction, int posx, int posy) {

//		getCharInfo();

		if (isPossible == true) {
			switch (direction) {
			case "UP":
				posy++;
				break;
			case "DOWN":
				posy--;
				break;
			case "LEFT":
				posx--;
				break;
			case "RIGHT":
				posx++;
				break;
			}

			System.out.println("moved " + direction);
		} else {
			isPossible = false;
		}

		if (isPossible == true) {
			System.out.println("New Position is posx: [" + posx + "] + posy: [" + posy + "]");
		} else {
			System.out.println("New Position is similiar to old Position. posx: [" + posx + "] + posy: [" + posy + "]");
		}

	}

	/*
	 * This method checks if the move of the player is within the given map. It
	 * also checks if the new Array position is the ground we are allowed to
	 * move on but the interface is not implemented yet.
	 */
	public boolean consistency(int[][] Map, String direction, int posx, int posy) {

		int Maplength = Map.length;
		

		/*
		 * "if tile is instance of ground then check if the new Position is
		 * within the map. else return false and do not allow move. Surround
		 * if-statement below with another if.
		 */
		// if(charPos[posx][posy] is instanceof ground){

		switch (direction) {
		case "up":
			if (posy >= 0 && Map[posx][posy--] != 1) {
				System.out.println("you can move up");
				isPossible = true;
			} else {
				System.out.println("you cannot move up. Map border");
				isPossible = false;
			}
			break;
		case "down":
			if (posy < Maplength && Map[posx][posy++] != 1) {
				System.out.println("you can move down");
			isPossible = true;
			} else {
				System.out.println("you cannot move down. Map border");
				isPossible = false;
			}
			break;
		case "right":
			if (posx < Maplength && Map[posx++][posy] != 1) {
				System.out.println("you can move right");
				isPossible = true;
			} else {
				System.out.println("you cannot move right. Map border");
				isPossible = false;
			}
			break;
		case "left":
			if (posx > 0 && Map[posx--][posy] != 1) {
				System.out.println("you can move lwft");
				isPossible = true;
			} else {
				System.out.println("you cannot move left. Map border");
				isPossible = false;
			}
			break;
		}
		movement(direction, posx, posy);
		return isPossible;
	}

	/*
	 * This method is responsible for handling the requests we are getting from
	 * the GUI. if we want to send a Chat Message for instance, we are passing
	 * an Object from the type ChatMessage (see Test class in GUI package).
	 */
	public void handleRequests(Messages msg) {
		MessageRequest hdl = new MessageRequest(msg);

	}

	public void handleResponse(Messages msg) {

		MessageResponse rsp = new MessageResponse(msg);
	}

	/*
	 * the functionality of this metho is to check if our player has any items
	 * in his surrounding to pick up.
	 */

	/*
	 * this method is used to set an item on the x+1 and y+1 position of the
	 * character on the map to show that our itemAvailable method is working
	 * correctly.
	 */

	public void setItemOnMap(int[][] Map) {
		getCharInfo();
		Map[posx + 1][posy + 1] = item.setItemPos(1);
		itemAvailable(posx, posy, Map);
	}

	public boolean itemAvailable(int x, int y, int[][] Map) {
		this.Map = Map;
		x = posx;
		y = posy;

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (Map[i][j] != 0) { // if the field is not empty
					isAvailable = true; // set the sign that there is an Item
					System.out.println("Item is on Position posx: [" + i + "] posy: [" + j + "]"
							+ " Player can pick it up, it is in his surrounding");
				} else {
					isAvailable = false;
				}
			}
		}

		return isAvailable;
	}
}
