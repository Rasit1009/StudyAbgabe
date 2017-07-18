package pp2017.team10.client.engine;

import pp2017.team10.shared.Character;
import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.Cheat;
import pp2017.team10.shared.DoorUsage;
import pp2017.team10.shared.Item;
import pp2017.team10.shared.ItemUsage;
import pp2017.team10.shared.Level;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.Logout;
import pp2017.team10.shared.Messages;
import pp2017.team10.shared.MonsterAttack;
import pp2017.team10.shared.Move;
import pp2017.team10.shared.NewPlayer;
import pp2017.team10.shared.PlayerAttack;
import pp2017.team10.shared.PlayerDead;
import pp2017.team10.shared.StartMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import pp2017.team10.client.comm.ClientComm;
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

	public Queue<Messages> bSendQueue = new LinkedList<Messages>();
	public int posx;
	public int posy;
	public boolean isPossible;
	public Item item = new Item();
	public int charPos;
	public boolean isAvailable;
	public int[][] map;
	public SendQueue send;
	public ArrayList<int[][]> levels = new ArrayList();

	public void getCharInfo() {

		// posx = character.getPosX();
		// posy = character.getPosY();
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
		// posx = character.setNewXPos(posx);
		// posy = character.setNewYPos(posy);

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
				// System.out.println("you can move up");
				// CE_Main.spiel.movePlayer("up", posx, posy);
				// itemAvailable(posx, posy, Map);
				// spiel.movePlayerMinimap(posx, posy);
				isPossible = true;
			} else {
				// System.out.println("you cannot move up. Map border");
				isPossible = false;
			}
			break;
		case "down":
			if (posy < Maplength && Map[posx][++posy] != 1) {
				// System.out.println("you can move down");
				// CE_Main.spiel.movePlayer("down", posx, posy);
				// itemAvailable(posx, posy, Map);
				// spiel.movePlayerMinimap(posx, posy);
				isPossible = true;
			} else {
				// System.out.println((posx<Maplength) + ", " + posx + ", " +
				// Map[++posx][posy]);
				isPossible = false;
			}
			break;
		case "right":
			if (posx < Maplength && Map[++posx][posy] != 1) {
				// System.out.println("you can move right");
				// CE_Main.spiel.movePlayer("right", posx, posy);
				// itemAvailable(posx, posy, Map);
				// spiel.movePlayerMinimap(posx, posy);
				isPossible = true;
			} else {
				// System.out.println((posx<Maplength) + ", " + posx + ", " +
				// Map[++posx][posy]);
				isPossible = false;
			}
			break;
		case "left":
			if (posx > 0 && Map[--posx][posy] != 1) {
				// System.out.println("you can move left");
				// CE_Main.spiel.movePlayer("left", posx, posy);
				// itemAvailable(posx, posy, Map);
				// spiel.movePlayerMinimap(posx, posy);
				isPossible = true;
			} else {
				// System.out.println("you cannot move left. Map border");
				isPossible = false;
			}
			break;
		}

		Move moveMsg = new Move(posx, posy, direction);
		handleMove(moveMsg);
	}

	/*
	 * This method is responsible for handling the requests we are getting from
	 * the GUI. if we want to send a Chat Message for instance, we are passing
	 * an Object from the type ChatMessage (see Test class in GUI package).
	 */

	/*
	 * the functionality of this method is to check if our player has any items
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
		// this.Map = Map;
		// x = posx;
		// y = posy;

		boolean isPossible = false;

		int itemPosX, itemPosY;

		try {
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (Map[i][j] == 2) { // if the field is not empty
						isPossible = true; // set the sign that there is an Item
						System.out.println(isPossible);
						System.out.println("Item is on Position posx: [" + i + "] posy: [" + j + "]"
								+ " Player can pick it up, it is in his surrounding");
						ItemUsage useItem = new ItemUsage(i, j, isAvailable);
						addQueue(useItem);
					}
				}
			}

		} catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println("not allowed");

		}

		return isPossible;
	}

	public Queue<Messages> getQueue() {
		return bSendQueue;
	}

	public Messages deleteQueue() {
		return bSendQueue.poll();
	}

	public void addQueue(Messages m) {
		System.out.println("added");
		bSendQueue.offer(m);

	}

	public void handleMessage(Queue<Messages> messages) {
		try {
			while (!messages.isEmpty()) {
				Messages m = messages.poll();
				if (m instanceof ChatMessage) {
					System.out.println("This is a ChatMessage");
					handleChat((ChatMessage) m);
				} else if (m instanceof Cheat) {
					System.out.println("This is a CheatMessage");
					handleCheat((Cheat) m);
				} else if (m instanceof DoorUsage) {
					System.out.println("This is a DoorUsageMessage");
					handleDoor((DoorUsage) m);
				} else if (m instanceof ItemUsage) {
					System.out.println("This is a ItemUsageMessage");
					handleItem((ItemUsage) m);
				} else if (m instanceof Login) {
					System.out.println("This is a LoginMessage");
					handleLogin((Login) m);
				} else if (m instanceof Move) {
					System.out.println("This is a MoveMessage");
					handleMove((Move) m);
				} else if (m instanceof PlayerAttack) {
					System.out.println("This is a PlayerAttackMessage");
					handlePlayerAttack((PlayerAttack) m);
				} else if (m instanceof Logout) {
					System.out.println("This is a LogoutMessage");
					handleLogout((Logout) m);
				} else if (m instanceof MonsterAttack) {
					System.out.println("This is a MonsterAttackMessage");
					handleMonsterAttack((MonsterAttack) m);
				} else if (m instanceof NewPlayer) {
					System.out.println("This is a NewPlayerMessage");
					handleNewPlayer((NewPlayer) m);
				} else if (m instanceof PlayerDead) {
					System.out.println("This is a PlayerDeadMessage");
					handlePlayerDead((PlayerDead) m);
				} else if (m instanceof Level) {
					System.out.println("This is a LevelMessage");
					handleLevel((Level) m);
				} else if (m instanceof StartMessage) {
					System.out.println("This is a StartMessage");
					handleStart((StartMessage) m);
				}
			}
		} catch (Exception e) {

		}
	}

	private void handleLevel(Level msg) {
		int[][] world;
		int levelID;
		world = msg.getWorld();
		levelID = msg.getLevelID();
		levels.add(world);
		System.out.println("LevelMessage empfangen");

	}

	public void buildLevel(int levelID) {

		switch (levelID) {
		case 1:
			CE_Main.spiel.setWorld(levels.get(0));
			break;
		case 2:
			CE_Main.spiel.setWorld(levels.get(1));
			break;
		case 3:
			CE_Main.spiel.setWorld(levels.get(2));
			break;
		case 4:
			CE_Main.spiel.setWorld(levels.get(3));
			break;
		case 5:
			CE_Main.spiel.setWorld(levels.get(4));
			break;
		}

	}

	private void handlePlayerDead(PlayerDead msg) {
		System.out.println("Player Dead Message");
		addQueue(msg);

	}

	private void handleNewPlayer(NewPlayer msg) {
		System.out.println("New Player Message");
		addQueue(msg);

	}

	private void handleMonsterAttack(MonsterAttack msg) {
		System.out.println("Monster Attack Message");
		addQueue(msg);

	}

	private void handleLogout(Logout msg) {
		System.out.println("Logout Message");
		addQueue(msg);
	}

	public void handleStart(StartMessage msg) {
		System.out.println("This is a Start message");
		buildLevel(msg.getLevelID());

	}

	public void handleLogin(Login msg) {

		System.out.println("This is a Login message");
		addQueue((Messages) msg);
	}

	public void handleMove(Move msg) {

		
		System.out.println("This is a Move message");
		addQueue((Messages) msg);
	}

	public void handleItem(ItemUsage msg) {

		System.out.println("This is a Item message");
		addQueue(msg);
	}

	public void handleDoor(DoorUsage msg) {

		System.out.println("This is a Door message");
		addQueue((Messages) msg);

	}

	public void handlePlayerAttack(PlayerAttack msg) {

		System.out.println("This is a Attack message");
		addQueue(msg);
	}

	public void handleChat(Messages msg) {

		System.out.println("This is a Chat Message");
		addQueue(msg);
	}

	/*
	 * This String is to be exchanged by a CheatMessage Object after
	 * integrating. String is used for test purposes.
	 */
	public void handleCheat(Messages m) {
		if (m.equals("noFogAnymore")) {
			activateFogCheat();
		} else if (m.equals("maxHP")) {
			activateHPCheat();
		} else if (m.equals("allDeadEnemy")) {
			activateEnemyCheat();
		} else if (m.equals("ScottyBeamMeUp")) {
			activateTeleportCheat();
		}
	}

	private void activateTeleportCheat() {
		System.out.println("Player teleported");
	}

	private void activateEnemyCheat() {
		System.out.println("All Enemies are dead!!");
	}

	private void activateHPCheat() {
		System.out.println("You are immortal!!");
	}

	private void activateFogCheat() {
		System.out.println("The Fog disappeared!");
	}

}