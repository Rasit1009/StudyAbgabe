package pp2017.team10.client.engine;

import pp2017.team10.shared.Character;
import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.Cheat;
import pp2017.team10.shared.DoorUsage;
import pp2017.team10.shared.Item;
import pp2017.team10.shared.ItemUsage;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.Logout;
import pp2017.team10.shared.Messages;
import pp2017.team10.shared.MonsterAttack;
import pp2017.team10.shared.Move;
import pp2017.team10.shared.NewPlayer;
import pp2017.team10.shared.PlayerAttack;
import pp2017.team10.shared.PlayerDead;

import java.io.IOException;
import java.util.Queue;

import pp2017.team10.client.comm.SendQueue;
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

public class ClientEngine {
	public int posx;
	public int posy;
	public boolean isPossible;
	public Item item = new Item();
	public int charPos;
	public boolean isAvailable;
	public int[][] Map;
	public static spielwelt spiel;
	public SendQueue send;




	public static void main(String[]args) throws IOException{
		spiel = new spielwelt();
		spiel.show();
	}
	
	public ClientEngine() {

	}

	public void getCharInfo() {

//		posx = character.getPosX();
//		posy = character.getPosY();
	}

	/*
	 * This method is responsible for the movement of the character. We get the
	 * direction from the GUI and handle the pressed button. Then we set the new
	 * Position and print them on the console.
	 */

	public void movement(String direction) {

		getCharInfo();

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
//		posx = character.setNewXPos(posx);
//		posy = character.setNewYPos(posy);

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
	public void consistency(int[][] Map, String direction, int posx, int posy) throws InterruptedException {

		int Maplength = Map.length;
		/*
		 * "if tile is instance of ground then check if the new Position is
		 * within the map. else return false and do not allow move. Surround
		 * if-statement below with another if.
		 */
		// if(charPos[posx][posy] is instanceof ground){

		switch (direction) {
		case "up":
			if (posy >= 0 && Map[posx][--posy] != 1) {
				//System.out.println("you can move up");
				spiel.movePlayer("up", posx, posy);
//				itemAvailable(posx, posy, Map);
				//spiel.movePlayerMinimap(posx, posy);
				isPossible = true;
			} else {
				//System.out.println("you cannot move up. Map border");
				isPossible = false;
			}
			break;
		case "down":
			if (posy < Maplength && Map[posx][++posy] != 1) {
				//System.out.println("you can move down");
				spiel.movePlayer("down", posx, posy);
//				itemAvailable(posx, posy, Map);
				//spiel.movePlayerMinimap(posx, posy);
			isPossible = true;
			} else {
				//System.out.println((posx<Maplength) + ", " + posx + ", " + Map[++posx][posy]);
				isPossible = false;
			}
			break;
		case "right":
			if (posx < Maplength && Map[++posx][posy] != 1) {
				//System.out.println("you can move right");
				spiel.movePlayer("right", posx, posy);
//				itemAvailable(posx, posy, Map);
				//spiel.movePlayerMinimap(posx, posy);
				isPossible = true;
			} else {
				//System.out.println((posx<Maplength) + ", " + posx + ", " + Map[++posx][posy]);
				isPossible = false;
			}
			break;
		case "left":
			if (posx > 0 && Map[--posx][posy] != 1) {
				//System.out.println("you can move left");
				spiel.movePlayer("left", posx, posy);
//				itemAvailable(posx, posy, Map);
				//spiel.movePlayerMinimap(posx, posy);
				isPossible = true;
			} else {
				//System.out.println("you cannot move left. Map border");
				isPossible = false;
			}
			break;
		}
		
		Move moveMsg = new Move(posx, posy, direction);
		handleRequests(moveMsg);
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
//		this.Map = Map;
//		x = posx;
//		y = posy;
		
		boolean isPossible = false;

		int itemPosX, itemPosY;
		
	try{	
		 for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (Map[i][j] == 2) { // if the field is not empty
					isPossible = true; // set the sign that there is an Item
					System.out.println(isPossible);
					System.out.println("Item is on Position posx: [" + i + "] posy: [" + j + "]"
							+ " Player can pick it up, it is in his surrounding");
					ItemUsage useItem = new ItemUsage(i, j, isAvailable);
					handleRequests(useItem);					
				} 
			} 
		} 
		
		
	} catch  (ArrayIndexOutOfBoundsException exception){
		System.out.println("not allowed");
		
	}
		
		return isPossible;
	}
	
	public Queue<Messages> getQueue(){
		return send.sendQueue;
	}

	public Messages deleteQueue() {
	
		return send.sendQueue.poll();
	}
	
	public void handleMessage(Queue<Messages> messages){
		try{
			while(!messages.isEmpty()){
				Messages m = messages.poll();
				
				if(m instanceof ChatMessage){
					System.out.println("This is a ChatMessage");
					handleRequests((ChatMessage) m);
				}
				else if(m instanceof Cheat){
					System.out.println("This is a CheatMessage");
					handleRequests((Cheat) m);
				}
				else if(m instanceof DoorUsage){
					System.out.println("This is a DoorUsageMessage");
					handleRequests((DoorUsage) m);
				}
				else if(m instanceof ItemUsage){
					System.out.println("This is a ItemUsageMessage");
					handleRequests((ItemUsage) m);
				}
				else if(m instanceof Login){
					System.out.println("This is a LoginMessage");
					handleRequests((Login) m);
				}
				else if(m instanceof Move){
					System.out.println("This is a MoveMessage");
					handleRequests((Move) m);
				}
				else if(m instanceof PlayerAttack){
					System.out.println("This is a PlayerAttackMessage");
					handleRequests((PlayerAttack) m);
				}
				else if(m instanceof Logout){
					System.out.println("This is a LogoutMessage");
					handleRequests((Logout) m);
				}
				else if(m instanceof MonsterAttack){
					System.out.println("This is a MonsterAttackMessage");
					handleRequests((MonsterAttack) m);
				}
				else if(m instanceof NewPlayer){
					System.out.println("This is a NewPlayerMessage");
					handleRequests((NewPlayer) m);
				}
				else if(m instanceof PlayerDead){
					System.out.println("This is a PlayerDeadMessage");
					handleRequests((PlayerDead) m);
				}
			}
		}catch(Exception e){
			
		}
	}
}
