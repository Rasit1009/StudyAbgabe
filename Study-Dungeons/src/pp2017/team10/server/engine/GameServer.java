package pp2017.team10.server.engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.Timer;

import pp2017.team10.shared.Messages;
import pp2017.team10.server.map.LevelGenerator;
import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.DoorUsageMessage;
import pp2017.team10.shared.ItemUsageMessage;
import pp2017.team10.shared.LevelMessage;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.LogoutMessage;
import pp2017.team10.shared.MonsterAttack;
import pp2017.team10.shared.MonsterList;
import pp2017.team10.shared.MonsterOnFieldMessage;
import pp2017.team10.shared.MoveMessage;
import pp2017.team10.shared.NewPlayer;
import pp2017.team10.shared.PlayerAttackMessage;
import pp2017.team10.shared.PlayersMessage;
import pp2017.team10.shared.StartMessage;
import pp2017.team10.shared.Tiles;
import pp2017.team10.shared.GameOverMessage;
import pp2017.team10.shared.ItemAddMessage;
import pp2017.team10.shared.ItemList;
import pp2017.team10.shared.UserLogedIn;
import pp2017.team10.shared.Map;

/**
 * Author: Felix Schifferdecker, 5585147
 */

/*
 * Die Hauptklasse um das Spiel zu verwalten. Hier werden Nachrichten
 * verarbeitet, mit Usern gearbeitet, Spielstï¿½nde geladen/gespeichert und die
 * Item- bzw. Kampfnachrichten verarbeitet. Auï¿½erdem loggt diese Klasse alle
 * User ein und aus und legt neue User ï¿½ber die Database-Klasse an
 */

public class GameServer {

	/*
	 * Beim Aufruf des GameServers wird eine neue Instanz der Datenbank erstellt
	 * wodurch alle User dort eingelesen werden.
	 */
	Database db = new Database();

	public GameServer() throws IOException {
		for (int k = 1; k <= 5; k++) {
			Tiles[][] map = lg.getLevel(k).getGround();
			monsterOnServer = new ArrayList<Monster>();
			totalMonServer.add(monsterOnServer);
			int[][] lvl = new int[50][50];
			boolean darkSide[][] = new boolean[50][50];
			for(int b = 0; b < 50; b++)
				for(int c = 0; c < 50; c++)
						darkSide[b][c] = true;
			
			int walkable[][] = new int [50][50];
			for (int i = 0; i < 50; i++) {
				for (int j = 0; j < 50; j++) {
					if (map[i][j].isWall())
						lvl[i][j] = 2;
					else if (map[i][j].isPlayer()) {
						lvl[i][j] = 100;
						for(int b = -2; b <= 4; b++)
							for(int c = -2; c <= 4; c++)
								if(i+b < 50 && c + j < 50)
								darkSide[i+b][j+c] = false;
					}
					else if (map[i][j].isDoor())
						lvl[i][j] = 6;
					else if (map[i][j].isExit())
						lvl[i][j] = 7;
					else if (map[i][j].isFloor())
						lvl[i][j] = 5;
					else if (map[i][j].isStone())
						lvl[i][j] = 4;
					else if (map[i][j].isMarble())
						lvl[i][j] = 3;
					else if (map[i][j].isEntrance())
						lvl[i][j] = 1;
					else if (map[i][j].isColumn())
						lvl[i][j] = 8;
					else if (map[i][j].isKey())
						lvl[i][j] = 9;
					
					if(map[i][j].isWalkable()) {
						walkable[i][j] = 1;
					}
					else
						walkable[i][j] = 0;
					
					if (map[i][j].isItem()) {
//						world[i][j] = 20;
						int type = new Random().nextInt(2);
						ItemList item = new ItemList(itemList.size(), i, j, type, k);
						itemList.add(item);
					}
						
					if (map[i][j].isMonster()) {
						int type = new Random().nextInt(2);
						Monster monster = new Monster(type, i, j, lg.getLevel(k).getLevelID());
						monsterOnServer.add(monster);
						int monsterID = 1;//totalMonster.get(map.getLevelID()).size();
						boolean gotKey = false;
						if(monsterID== 1) 
							gotKey = true;
						MonsterList mof = new MonsterList(monsterID, i, j, monster.getmaxHp(), monster.gethp(), gotKey, monster.getdmg());
						
						totalMonServer.add(monsterOnServer);	
						//totalMonster.get(map.getLevelID()).add(mof);				
						}
				}
			}
		darkside.add(darkSide);
		walkableList.add(walkable);
		levelList.add(new LevelMessage(lvl, k));
//		lg.printUniversity(map);
		}
		//moveMonster();
		getAllLevels();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//Damit das Programm weiter läuft und nicht terminiert
		
	}
	
	
	
	private boolean start = true;
	private int[][] world = new int[50][50];
	private ArrayList<boolean[][]> darkside = new ArrayList<boolean[][]>();
	private int currentLvl;
	private LevelGenerator lg = new LevelGenerator();
	public Map map;
	private ArrayList<Monster> monsterOnServer;
	private Queue<Messages> messageQueue = new LinkedList<Messages>();
	private ArrayList<UserLogedIn> userList = new ArrayList<UserLogedIn>();
	
	private ArrayList<LevelMessage> levelList = new ArrayList<LevelMessage>();
	private ArrayList<int[][]> walkableList = new ArrayList<int[][]>();
	private ArrayList<ItemList> itemList = new ArrayList<ItemList>();

	// Monster werden in dieser Liste abgespeichert und verï¿½ndert
	private ArrayList<MonsterList> monsterList = new ArrayList<MonsterList>();
	private ArrayList<ArrayList<MonsterList>> totalMonster = new ArrayList<ArrayList<MonsterList>>();
	private ArrayList<ArrayList<Monster>> totalMonServer = new ArrayList<ArrayList<Monster>>();

	// Methode um ausgehende Nachrichten vom Server abzurufen
	public Queue<Messages> getMessageQueue() {
		return this.messageQueue;
	}

	// Methode um die ausgehenden Nachrichten zu loeschen bzw. neu zu definieren
	public void setMessageQueue(Messages m) {
		messageQueue.offer(m);
	}

	public void moveMonster() {
		System.out.println("Hallo - test");
		ActionListener taskPerformer = new ActionListener() {

 			boolean run = true;
         		public void actionPerformed(ActionEvent evt) {
         			
         			if (run == false) {
                        ((Timer) evt.getSource()).stop();
                    }
         			
         			for(int i = 0; i < totalMonServer.size(); i++) {
         				System.out.println("test");
         				monsterOnServer = totalMonServer.get(i);
         				for(int j = 0; j < monsterOnServer.size(); j++) {
         					for(UserLogedIn user : userList) {
         						System.out.println(monsterOnServer.size());
         						int posX = user.getUserPosX();
         						int posY = user.getUserPosY();
         						int id = user.getUserID();
         						Monster mon = monsterOnServer.get(j);
         						mon.fsm(posX, posY, id, walkableList.get(i), userList);
         						monsterOnServer.set(j, mon);
         						System.out.println(mon.getposx());
         					}
         				}
                        	 
                    }
         			System.out.println("Monster wurde bewegt!");
         		}
		};
		new Timer(500, taskPerformer).start();

	}		
	
	
	public int[][] mapToArray(Map map, int lvl) {
		int walkable[][] = new int [50][50];
	/*	Map build = new Map(); 
		map.getLevelID();
		switch(map.levelID){
		case 1: 
			Map wiso = lg.buildWiso();
			build = wiso;
			break;
		case 2: 
			Map  philo = lg.buildPhilo();
			build = philo;
			break;
		case 3: 
			Map library = lg.buildLibrary();
			build = library;
			break;
		case 4: 
			Map physik = lg.buildPhysik();
			build = physik;
			break;
		case 5: 
			Map copt = lg.buildCopt();
			build = copt;
			break;
		}
		*/
//		Map wiso = lg.buildWiso();
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				if (map.getTile(i, j).isWall())
					world[i][j] = 2;
				else if (map.getTile(i, j).isPlayer())
					world[i][j] = 100;
				else if (map.getTile(i, j).isDoor())
					world[i][j] = 6;
				else if (map.getTile(i, j).isExit())
					world[i][j] = 7;
				else if (map.getTile(i, j).isFloor())
					world[i][j] = 5;
				else if (map.getTile(i, j).isStone())
					world[i][j] = 4;
				else if (map.getTile(i, j).isMarble())
					world[i][j] = 3;
				else if (map.getTile(i, j).isEntrance())
					world[i][j] = 1;
				else if (map.getTile(i, j).isColumn())
					world[i][j] = 8;
				else if (map.getTile(i, j).isKey())
					world[i][j] = 9;
				
				if(map.getTile(i,  j).isWalkable()) {
					walkable[i][j] = 1;
				}
				else
					walkable[i][j] = 0;
				
				if (map.getTile(i, j).isItem()) {
					world[i][j] = 20;
					int type = new Random().nextInt(2);
					ItemList item = new ItemList(itemList.size(), i, j, type, lvl);
					itemList.add(item);
				}
					
				if (map.getTile(i, j).isMonster()) {
					int type = new Random().nextInt(2);
					Monster monster = new Monster(type, i, j, map.getLevelID());
					int monsterID = totalMonster.get(map.getLevelID()).size();
					boolean gotKey = false;
					if(monsterID== 1) 
						gotKey = true;
					MonsterList mof = new MonsterList(monsterID, i, j, monster.getmaxHp(), monster.gethp(), gotKey, monster.getdmg());
					totalMonster.get(map.getLevelID()).add(mof);
				}
//				System.out.print(world[i][j] + ", ");
			}
//			System.out.println();
//			levelList.add(new Level(world, k));
		}
		
			walkableList.add(walkable);
		return world;
		
	}
	

	// bearbeitet eingehende Nachrichten von der ClientEngine
	// Jede Nachricht ist eine Instanz einer anderen Klasse und ruft eine andere
	// Methode auf
	public void handleMessage(Queue<Messages> messages) {
		System.out.println("Handle");
		try {
			while (!messages.isEmpty()) {
				Messages m = messages.poll();

				if (m instanceof ChatMessage) {
					System.out.println("This is a ChatMessage");
					handleChatMessage((ChatMessage) m);
				} else if (m instanceof DoorUsageMessage) {
					System.out.println("This is a DoorUsageMessage");
					handleDoorUsage((DoorUsageMessage) m);
				} else if (m instanceof ItemUsageMessage) {
					System.out.println("This is a ItemUsageMessage");
					handleItemUsage((ItemUsageMessage) m);
				} else if (m instanceof Login) {
					System.out.println("This is a LoginMessage");
					handleLogin((Login) m);
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
				} else if(m instanceof ItemAddMessage) {
					handleItemAddMessage((ItemAddMessage) m);
				} else if(m instanceof StartMessage) {
					System.out.println("This is a Start Message");
					handleStartMessage((StartMessage) m);
				}
				
			}
		} catch (Exception e) {

		}
	}

	// Sendet eine Chat-Nachricht an alle Nutzer auï¿½er den Empfï¿½nger
	public void handleItemAddMessage(ItemAddMessage m) {
		
		for(UserLogedIn u : userList) {
			if(u.getUser().equals(m.getUser())) {
				int items[] = u.getItems();
				items[m.getItemID()] = items[m.getItemID()] + m.getCount();
				u.setItems(items);
			}				
		}
		messageQueue.offer(new PlayersMessage(userList));
	}
	
	public void handleStartMessage(StartMessage m) {
		LevelMessage msg = levelList.get(m.getLevelID() - 1);
		messageQueue.offer(msg);
		messageQueue.offer(new PlayersMessage(userList));
		messageQueue.offer(new MonsterOnFieldMessage(totalMonster.get(m.getLevelID()-1)));
	}
	
	public void handleChatMessage(ChatMessage m) throws IOException {
		switch(m.getContent()) {
		case "noFogAnymore" : {
			fogCheat();
			break;
		}
		case "maxHPPlease" : {
			for(UserLogedIn u : userList) {
				u.setHealth(100);
				messageQueue.offer(new PlayersMessage(userList));
			}
			break;
		}
		case "allMonstersFlyHigh" : {
			for(int i = 0; i < totalMonster.get(m.getCurrentLevel() - 1).size(); i++)
				totalMonster.get(m.getCurrentLevel() - 1).remove(i);
				messageQueue.offer(new MonsterOnFieldMessage(totalMonster.get(m.getCurrentLevel() - 1)));
		}
		default : {
		messageQueue.offer(m);
		}
		}
	}
//
//	// Ruft verschiedene Cheatmethoden auf. Bisher nur noFogAnymore
//	// implementiert
//	public void handleCheat(Cheat m) throws IOException {
//		System.out.println("Cheat wurde aktiviert: " + m.content);
//		if (m.content == "noFogAnymore")
//			fogCheat();
//		else if (m.content == "maxHP") {
//		}
//		// Funtion to set the the HP to the max
//		else if (m.content == "allDeadEnemy") {
//		}
//		// Function to Monster System
//		else if (m.content == "ScottyBeamMeUp") {
//		}
//		// Function for ScottyBeamMeUp
//
//	}

	// Ueberprueft, ob der Nutzer den Schluessel fuer die Tuer hat, falls ja
	// wird getLevel(currentlvl++) aufgerufen
	public void handleDoorUsage(DoorUsageMessage m) throws IOException {
		boolean userKey = false;
		for (UserLogedIn u : userList) {
			if (m.getUser().equals(u.getUser()) && u.getGotKey() == true) {
				u.setGotKey(false);
				userKey = true;
				
			}
		}
			System.out.println("Der User hat keinen Key");
	}

	// ï¿½berprï¿½ft ob der Nutzer ein Item aufheben oder Nutzen will und
	// verarbeitet dieses
	// (Bisher nur Trank implementiert) und Levelabhï¿½ngig
	public void handleItemUsage(ItemUsageMessage m) throws IOException {
//		if (m.pickup == true) {
//			for (UserLogedIn u : userList)
//				if (u.getUser().equals(m.user)) {
//					if (world[u.getUserPosX()][u.getUserPosY()] > 3 && world[u.getUserPosX()][u.getUserPosY()] < 7) {
//						int[] items = u.getItems();
//						items[world[u.getUserPosX()][u.getUserPosY()] - 4]++;
//						u.setItems(items);
//						world[u.getUserPosX()][u.getUserPosY()] = 1;
//						sendGround();
//						System.out.println("Der User " + u.getUser() + "hat das Item mit der ID"
//								+ (world[u.getUserPosX()][u.getUserPosY()] - 4) + " aufgenommen");
//					}
//					if (world[u.getUserPosX()][u.getUserPosY()] == 7) {
//						System.out.println("Der User " + u.getUser() + " hat den Schlï¿½ssel aufgenommen");
//						u.setGotKey(true);
//						world[u.getUserPosX()][u.getUserPosY()] = 1;
//						sendGround();
//					}
//				}
//		} else {
//			for (UserLogedIn u : userList)
//				if (u.getUser().equals(m.user)) {
//					int[] items = u.getItems();
//					items[m.itemID]--;
//					int health = u.getHealth();
//					health = health + 50;
//					if (health > 100) {
//						health = 100;
//					}
//					System.out.println("Der User " + u.getUser() + " hat ein Item mit der ID "
//							+ (world[u.getUserPosX()][u.getUserPosY()] - 4) + " aufgenommen");
//					u.setHealth(health);
//				}
//		}
	}

	// Diese Methode ï¿½berprï¿½ft ob der Nutzer vorhanden ist und logged ihn in das
	// System ein
	public void handleLogin(Login m) throws IOException {
		boolean userExisting = true;
		int levelDone = 0;
		Password p = new Password();
		String pw = p.hashing(m.getPassword());
		for (User u : db.userdata)
			if (u.getUser().equals(m.getUser()))
				if (u.getPassword().equals(pw)) {
					levelDone = u.getLevelDone();
					userExisting = true;
				}

		if (userExisting) {
			System.out.println("Der User ist im System vorhanden und wurde zur Liste hinzugefï¿½gt");
			int[] items = new int[1];
			items[0] = 0;
			userList.add(new UserLogedIn(100, items, m.getUser(), 0, false, userList.size() + 20, 0, 0, 20, levelDone, 0));
			getAllLevels();
			
			PlayersMessage msg = new PlayersMessage(userList);
			messageQueue.offer(msg);
			// Funktion um beim Start alle Level zu ï¿½bertragen
		}
	}

	// Diese Methode bewegt den Nutzer ï¿½ber die Karte mit Hilfe der neuen
	// Koordinaten
	public void handleMove(MoveMessage m) throws IOException {
		for(UserLogedIn u : userList) {
			if(u.getUser().equals(m.getUser())) {
				u.setUserPosX(m.getPosX());
				u.setUserPosY(m.getPosY());
				
				for(int i = -2; i <= 2; i++) {
					for(int j = -2; j <= 2; j++) {
						darkside.get(u.getLevelNow())[i][j] = false;
					}
				}
				
			}
		}
		
		PlayersMessage msg = new PlayersMessage(userList);
		messageQueue.offer(msg);
		
		
//		for (UserLogedIn u : userList) {
//			if (u.getUser().equals(m.getUser())) {
//				System.out.println("Der User " + u.getUser() + " befindet sich an der Position X: " + u.getUserPosX()
//						+ " und Y: " + u.getUserPosY());
//
//				// Sobald Level da sind kann es implementiert werden
//				world[u.getUserPosX()][u.getUserPosY()] = 1;
//				world[m.posx][m.posy] = u.getUserID();
//				u.setUserPosX(m.posx);
//				u.setUserPosY(m.posy);
//
//				System.out.println("Der User " + u.getUser() + " befindet sich an der neuen Position X: "
//						+ u.getUserPosX() + " und Y: " + u.getUserPosY());
//
//				sendGround();
//			}
//		}

	}

	// Sobald der User angreift ï¿½berprï¿½ft diese Klasse ob ein Monster in der
	// Nï¿½he ist und zieht dem Monster HP ab
	// Da keine Level vorhanden monentan ohne Funktion
	public void handlePlayerAttack(PlayerAttackMessage m) throws IOException {
		for (UserLogedIn u : userList)
			if (u.getUser().equals(m.getUser())) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j] >= 10
								&& world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j] <= 15) {
							int monsterID = world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j];
							for (MonsterList mon : monsterList)
								if (monsterID == mon.getMonsterID()) {
									mon.setHealth(mon.getHealth() - u.getDamage());
									System.out.println("Das Monster mit der ID " + mon.getMonsterID() + "hat nun "
											+ mon.getHealth() + " HP.");
								}
						}
					}
				}
				sendGround();
			}
	}

	// Logged den User aus und speichert das Spiel als txt.-Datei mit seinem
	// Namen ab
	public void handleLogout(LogoutMessage m) throws IOException {
		saveLevel(world, m.getUser());
		int i = 0;
		for (UserLogedIn u : userList) {
			if (m.getUser().equals(u.getUser())) {
				userList.remove(i);
			}
			i++;
		}
		
		PlayersMessage msg = new PlayersMessage(userList);
		messageQueue.offer(msg);
		
//		for (UserLogedIn u : userList) {
//			System.out.println("Nutzer online: " + u.getUser());
//		}

	}

	// Diese Methode registriert einen neuen Spieler
	public void handleNewPlayer(NewPlayer m) throws IOException {
		int[] items = new int[1];
		items[0] = 0;
<<<<<<< HEAD
		userList.add(new UserLogedIn(100, items, m.getUser(), 0, false, userList.size() + 20, 0, 0, 20, 0, 1));
		
		for (UserLogedIn l : userList) {
			System.out.println("Gib Userliste aus: " + l.getUserID() + l.getUser());
		}

		getAllLevels();
=======
		userList.add(new UserLogedIn(100, items, m.getUser(), 0, false, userList.size() + 20, 0, 0, 20, 0, 0));
		
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git
		PlayersMessage msg = new PlayersMessage(userList);
		messageQueue.offer(msg);
		getAllLevels();
		// Funktion um beim Start alle Level zu ï¿½bertragen

	}

	// Diese Methode ist ï¿½hnlich wie die PlayerAttack allerdings wird hier der
	// User angegriffen
	// Da keine Level vorhanden monentan ohne Funktion
	public void handleMonsterAttack(MonsterAttack m) throws IOException {
		for (UserLogedIn u : userList)
			if (u.getUser().equals(m.user)) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j] >= 10
								&& world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j] <= 15) {
							int monsterID = world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j];
							for (MonsterList mon : monsterList)
								if (monsterID == mon.getMonsterID()) {
									u.setHealth(u.getHealth() - mon.getDamage());
									System.out.println(
											"Der Spieler " + u.getUser() + " hat nun " + u.getHealth() + " HP");
								}
						}
					}
				}
				sendGround();
			}
	}

	// Sobald ein Nutzer gestorben ist wird die Highscore abgespeichert und eine
	// Nachricht ï¿½bergeben
	public void handlePlayerDead(GameOverMessage m) throws IOException {
		int score = 0;
		for (UserLogedIn u : userList)
			if (m.getName().equals(u.getUser()))
				score = u.getScore();
		Highscore highscore = new Highscore(m.getUser(), score);
		db.addHighscore(highscore);

		GameOverMessage msg = new GameOverMessage(m.getUser());
		messageQueue.offer(msg);
	}

	// Helfermethode um Monster hinzuzufï¿½gen
//	public void addMonsters() {
//		for (int i = 0; i < 3; i++) {
//			monsterList.add(new MonsterList(100, i + 10, i, i, 10));
//		}
//	}

	// speichert das aktuelle Level beim ausloggen ab
	public void saveLevel(int[][] level, String user) {
		// ï¿½berprï¿½ft ob der Ordner fï¿½r die Spielstï¿½nde existiert und erstellt
		// ihn notfalls
		String folderpath = System.getProperty("user.dir") + "\\src\\saves";
		Path folder = Paths.get(folderpath);
		if (!folder.toFile().exists())
			try {
				folder.toFile().createNewFile();
			} catch (IOException e) {
				System.out.println(e);
			}
		String path = System.getProperty("user.dir") + "\\src\\saves\\" + user + ".txt";
		Path file = Paths.get(path);
		File check = new File(path);
		// ï¿½berprï¿½ft ob bereits eine Datei besteht
		if (check.exists() == false) {
			try {
				// Erstellt leere Datei
				Files.createFile(file);
			} catch (IOException x) {
				System.out.println("Failed to create file");
			}
		}

		try {

			// Das Welt wird in der Datei abgespeichert
			// # wird benutzt um die Zahlen aus dem Array auseinander zu halten
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			for (int i = 0; i < level.length; i++) {
				String levelLine = "";
				for (int j = 0; j < level.length; j++) {
					levelLine = levelLine + level[i][j] + "#";
				}
				out.write(levelLine);
				out.newLine();
			}
			out.close();
		} catch (IOException e) {
			System.out.println("Exception ");
		}
	}

	// Das Laden von gespeicherten Spielen (wird bisher nicht aufgerufen)
	// Schreibt die gespeicherte Datei in die Welt rein
	public void getSafedLevel(String user) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			// Datei wird ï¿½ber FileReader und BufferedReader eingelesen
			fr = new FileReader(System.getProperty("user.dir") + "\\src\\saves\\" + user + ".txt");
			br = new BufferedReader(fr);

			// Datei wird auf die world geschrieben
			String readCurrentLine;
			String read = "";
			int x = 0;
			int y = 0;
			br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\saves\\" + user + ".txt"));
			while ((readCurrentLine = br.readLine()) != null) {
				for (int i = 0; i < readCurrentLine.length(); i++) {
					while (i < readCurrentLine.length() && readCurrentLine.charAt(i) != '#') {
						read = read + readCurrentLine.charAt(i);
						i++;
					}
					if (i < readCurrentLine.length() && readCurrentLine.charAt(i) == '#') {
						world[x][y] = Integer.parseInt(read);
						read = "";
						x++;
					}
				}
				x = 0;
				y++;
			}
			y = 0;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}

	}

	// entfernt den Nebel um alles zu sehen
	public void fogCheat() {
		int k = 0;
		for(boolean[][] u : darkside) {
		for (int i = 0; i <= world.length; i++)
			for (int j = 0; 0 <= world.length; j++)
				darkside.get(k)[i][j] = false;
			
		}
		k++;
		sendGround();
	}

	// Sendet die aktuelle Karte und eine Liste aller Spieler die momentan
	// eingeloggt sind
	private void sendGround() {
		for (LevelMessage u : levelList) {
			int[][] groundSend = new int[u.getWorld().length][u.getWorld().length];
			for (int i = 0; i <= u.getWorld().length; i++)
				for (int j = 0; j <= u.getWorld().length; j++) {
					if(darkside.get(u.getLevelID())[i][j] == true)
						groundSend[i][j] = 0;
					else
						groundSend[i][j] = u.getWorld()[i][j];
				}
					
		// Die Message geht an jeden momentan eingeloggten Nutzer
			
			messageQueue.offer(new LevelMessage(groundSend, userList, u.getDoorOpen()));
		}
	}

	// Funktion um alle Level auf einemal zu ï¿½bertragen
	public void getAllLevels() {
<<<<<<< HEAD

		System.out.println("hol die Levels ab");
		for (int i = 1; i <= 5; i++) {
			int[][] lvl = new int[50][50];
			// for(int k = 0; k < 50; k++){
			// for(int j = 0; j < 50; j++){
			// lvl[k][j] = 1;
			// }

			// }
			map = lg.getLevel(i);
			// System.out.println(map.levelID);
			lvl = mapToArray(map);
			// lvl = getLevel(i);
			// Funktion wird beim Levelgenerator aufgerufen um alle Level zu
			// bekommen
			System.out.println(map.levelID);
			LevelMessage msg = new LevelMessage(lvl, map.levelID);
			messageQueue.offer(msg);
			System.out.println("Map: " + i);
			for (int k = 0; k < 50; k++) {
				for (int j = 0; j < 50; j++) {
					System.out.print(lvl[k][j] + ", ");
				}
				System.out.println();

			}
			System.out.println();
		}
=======
//		System.out.println("hol die Levels ab");
//		if(start) {
//		for (int i = 1; i <= 5; i++) {
//			int[][] lvl = new int[50][50];
////			for(int k = 0; k < 50; k++){
////				for(int j = 0;  j < 50; j++){
////					lvl[k][j] = 1;
////				}
//				
////			}
//			map = lg.getLevel(i);
////			System.out.println(map.levelID);
//			lvl = mapToArray(map, i);
////			lvl = getLevel(i);
//			// Funktion wird beim Levelgenerator aufgerufen um alle Level zu
//			// bekommen
//			Level msg = new Level(lvl,map.levelID);
//			levelList.add(msg);
//			messageQueue.offer(msg);
//			System.out.println("Map: " + i);
////			for(int k = 0; k < 50; k++){
////				for(int j = 0;  j < 50; j++){
////					System.out.print(lvl[k][j] + ", ");
////				}
////				System.out.println();
////				
////			}
//
//			System.out.println();
//		}
//		start = false;
//		}
//		else
		
			for(LevelMessage l : levelList) {
				messageQueue.offer(l);
//				for(int i = 0; i < 50; i++) {
//					for(int j = 0; j < 50; j++) {
//						System.out.print(l.getWorld()[i][j]);
//					}
//					System.out.println();
//					
//				}
//				System.out.println();	
			}
>>>>>>> branch 'master' of https://github.com/Rasit1009/StudyAbgabe.git
	}

	// Fï¿½r Testzwecke erstelles Array
//	public void testArray() {
//		for (int i = 0; i < 50; i++) {
//			for (int j = 0; j < 50; j++)
//				world[i][j] = 1;
//			world[i][i] = 0;
//		}
//	}

	public void ausgabeArray() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				System.out.print(world[i][j] + "_");
			}
			System.out.println();
		}
	}
	
	public ArrayList<UserLogedIn> getUserList(){
		return userList;
	}
}
