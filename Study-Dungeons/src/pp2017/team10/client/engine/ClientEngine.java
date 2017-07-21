package pp2017.team10.client.engine;

import pp2017.team10.shared.Character;
import pp2017.team10.shared.ChatMessage;

import pp2017.team10.shared.DoorUsageMessage;
import pp2017.team10.shared.ItemMessage;
import pp2017.team10.shared.ItemUsageMessage;

import pp2017.team10.shared.DoorUsageMessage;
import pp2017.team10.shared.ItemMessage;
import pp2017.team10.shared.ItemUsageMessage;
import pp2017.team10.shared.LevelMessage;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.LogoutMessage;
import pp2017.team10.shared.Messages;
import pp2017.team10.shared.MonsterAttack;
import pp2017.team10.shared.MoveMessage;
import pp2017.team10.shared.NewPlayer;

import pp2017.team10.shared.PlayerAttackMessage;
import pp2017.team10.shared.GameOverMessage;
import pp2017.team10.shared.StartMessage;
import pp2017.team10.shared.UserLogedIn;
import pp2017.team10.shared.PlayerAttackMessage;
import pp2017.team10.shared.PlayersMessage;
import pp2017.team10.shared.StartMessage;
//github.com/Rasit1009/StudyAbgabe.git

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import pp2017.team10.client.comm.ClientComm;
import pp2017.team10.client.comm.SendQueue;
import pp2017.team10.client.gui.MainMenuB;
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

public final class ClientEngine {

	public Queue<Messages> bSendQueue = new LinkedList<Messages>();
	public int posx;
	public int posy;
	public boolean isPossible;
	// public ItemMessage item = new ItemMessage();
	public int charPos;
	public boolean isAvailable;
	public int[][] map;
	public SendQueue send;
	public ArrayList<int[][]> levels = new ArrayList();
	public ArrayList<UserLogedIn> user = new ArrayList();
	public UserLogedIn use;
	public int count = 1;

	private static ClientEngine ce = new ClientEngine();

	public ClientEngine() {

	}

	public static synchronized ClientEngine getEngine() {
		if (ce == null) {
			ce = new ClientEngine();
		}
		return ce;
	}

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

		MoveMessage moveMsg = new MoveMessage(posx, posy, direction);
		bSendQueue.offer(moveMsg);
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

	// public void setItemOnMap(int[][] Map) {
	// getCharInfo();
	// Map[posx + 1][posy + 1] = item.setItemPos(1);
	// itemAvailable(posx, posy, Map);
	// }

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
						// ItemUsageMessage useItem = new ItemUsageMessage(i, j,
						// isAvailable);
						// addQueue(useItem);
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
				} else if (m instanceof DoorUsageMessage) {
					System.out.println("This is a DoorUsageMessage");
					handleDoor((DoorUsageMessage) m);
				} else if (m instanceof ItemUsageMessage) {
					System.out.println("This is a ItemUsageMessage");
					handleItem((ItemUsageMessage) m);
				} else if (m instanceof Login) {
					System.out.println("This is a LoginMessage");
					// handleLogin((Login) m);
				} else if (m instanceof MoveMessage) {
					System.out.println("This is a MoveMessage");
					handleMove((MoveMessage) m);
				} else if (m instanceof PlayerAttackMessage) {
					System.out.println("This is a PlayerAttackMessage");
					handlePlayerAttack((PlayerAttackMessage) m);
				} else if (m instanceof LogoutMessage) {
					System.out.println("This is a LogoutMessage");
					handleLogout((LogoutMessage) m);
				} else if (m instanceof MonsterAttack) {
					System.out.println("This is a MonsterAttackMessage");
					handleMonsterAttack((MonsterAttack) m);
				} else if (m instanceof NewPlayer) {
					System.out.println("This is a NewPlayerMessage");
					handleNewPlayer((NewPlayer) m);
				} else if (m instanceof GameOverMessage) {
					System.out.println("This is a PlayerDeadMessage");
					handlePlayerDead((GameOverMessage) m);
					// handlePlayerDead((PlayerDead) m);
				} else if (m instanceof LevelMessage) {
					System.out.println("This is a LevelMessage");
					handleLevel((LevelMessage) m);
				} else if (m instanceof StartMessage) {
					System.out.println("This is a StartMessage");
					handleStart((StartMessage) m);
				} else if (m instanceof PlayersMessage) {
					handlePlayersMessage((PlayersMessage) m);
				}
			}
		} catch (Exception e) {

		}
	}

	private void handlePlayersMessage(PlayersMessage msg) throws IOException {
		System.out.println("this is a PlayersMessage");

		for (int i = 0; i <= user.size(); i++) {
			if (user.get(i).getHealth() != msg.getUser().get(i).getHealth()) {
				spielwelt.getSpielwelt().setHealth(msg.getUser().get(i).getHealth());
			} else if ((user.get(i).getUserPosX() != msg.getUser().get(i).getUserPosX()
					|| user.get(i).getUserPosY() != msg.getUser().get(i).getUserPosY())) {
				spielwelt.getSpielwelt().movePlayer(i, msg.getUser().get(i).getUserPosX(),
						msg.getUser().get(i).getUserPosY());
			} else if (user.get(i).getItems() != msg.getUser().get(i).getItems()) {
				// spielwelt.getSpielwelt().setItemCount(msg.getUser().get(i).get,
				// count);
			}
		}
		// level = user.get(i).getLevelNow();
		// System.out.println("start level" + level);
		// i++;
		// buildLevel(level);
	}

	public void setUser(PlayersMessage msg) {

		// userlist = ((PlayersMessage)msg).getUser();
		// user = new UserLogedIn();

	}

	private void handlePlayerDead(GameOverMessage msg) {

	}

	private void handleLevel(LevelMessage msg) {
		int[][] world;
		int levelID;

		world = msg.getWorld();
		levelID = msg.getLevelID();
		System.out.println("das momentane Level ist Level" + levelID);
		levels.add(world);

		// levels.add(world, levelID);
		System.out.println("LevelMessage empfangen");
		++count;
		if (count == 5) {
			try {
				buildLevel(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void buildLevel(int levelID) throws IOException {

		int[][] world;
		int length;
		switch (levelID) {
		case 1:
			System.out.println("hier bin ich");
			length = levels.size();
			System.out.println("Anzahl der Elemente: " + length);
			// spielwelt.getSpielwelt().setWorld(levels.get(levelID).getWorld());
			spielwelt.getSpielwelt().setWorld(levels.get(0));
			break;
		case 2:
			spielwelt.getSpielwelt().setWorld(levels.get(1));
			break;
		case 3:
			spielwelt.getSpielwelt().setWorld(levels.get(2));
			break;
		case 4:
			spielwelt.getSpielwelt().setWorld(levels.get(3));
			break;
		case 5:
			spielwelt.getSpielwelt().setWorld(levels.get(4));
			break;
		}

	}

	private void handlePlayerDead(PlayersMessage msg) {
		System.out.println("Player Dead Message");
		addQueue(msg);

	}

	public void startup(String user, char[] cs) {
		System.out.println("Send LoginMessage");
		Login msg = new Login(user, cs);
		addQueue(msg);
	}

	public void startNewPlayer(String user, char[] cs) {
		NewPlayer msg = new NewPlayer(user, cs);
		addQueue(msg);
	}

	private void handleNewPlayer(NewPlayer msg) {
		System.out.println("New Player Added");
		// addQueue(msg);

	}

	private void handleMonsterAttack(MonsterAttack msg) {
		System.out.println("Monster Attack Message");

	}

	private void handleLogout(LogoutMessage msg) {
		System.out.println("Logout Message");
		// addQueue(msg);
	}

	public void handleStart(StartMessage msg) throws IOException {
		System.out.println("This is a Start message");

	}

	public void handleLogin(PlayersMessage msg) {

		System.out.println("This is a Login message");
		user.addAll(msg.getUser());
	}

	public void handleMove(MoveMessage msg) {

		System.out.println("This is a Move message");
		// spielwelt.getSpielwelt().movePlayer(msg.playerID, posXNeu, posYNeu);
	}

	public void handleItem(ItemUsageMessage msg) {

		System.out.println("This is a Item message");
		// addQueue(msg);
	}

	public void handleDoor(DoorUsageMessage msg) {

		System.out.println("This is a Door message");
		// addQueue((Messages) msg);

	}

	public void handlePlayerAttack(PlayerAttackMessage msg) {

		System.out.println("This is a Attack message");
		// addQueue(msg);
	}

	public void handleChat(Messages msg) {

		System.out.println("This is a Chat Message");
		// addQueue(msg);
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