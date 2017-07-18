package pp2017.team10.server.engine;

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

import pp2017.team10.shared.Messages;
import pp2017.team10.server.map.LevelGenerator;
import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.Cheat;
import pp2017.team10.shared.DoorUsage;
import pp2017.team10.shared.ItemUsage;
import pp2017.team10.shared.Level;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.Logout;
import pp2017.team10.shared.MonsterAttack;
import pp2017.team10.shared.Move;
import pp2017.team10.shared.NewPlayer;
import pp2017.team10.shared.PlayerAttack;
import pp2017.team10.shared.PlayerDead;
import pp2017.team10.shared.Map;

/**
 * Author: Felix Schifferdecker, 5585147
 */

/*
 * Die Hauptklasse um das Spiel zu verwalten. Hier werden Nachrichten
 * verarbeitet, mit Usern gearbeitet, Spielst�nde geladen/gespeichert und die
 * Item- bzw. Kampfnachrichten verarbeitet. Au�erdem loggt diese Klasse alle
 * User ein und aus und legt neue User �ber die Database-Klasse an
 */

public class GameServer {

	/*
	 * Beim Aufruf des GameServers wird eine neue Instanz der Datenbank erstellt
	 * wodurch alle User dort eingelesen werden.
	 */
	Database db = new Database();

	public GameServer() throws IOException {

	}

	private int[][] world = new int[50][50];
	private boolean[][] darkside;
	private int currentLvl;
	private LevelGenerator lg = new LevelGenerator();
	public Map map;

	private Queue<Messages> messageQueue = new LinkedList<Messages>();
	private ArrayList<UserLogedIn> userList = new ArrayList<UserLogedIn>();

	// Monster werden in dieser Liste abgespeichert und ver�ndert
	private ArrayList<MonstersOnField> monsterList = new ArrayList<MonstersOnField>();

	// Methode um ausgehende Nachrichten vom Server abzurufen
	public Queue<Messages> getMessageQueue() {
		return this.messageQueue;
	}

	// Methode um die ausgehenden Nachrichten zu loeschen bzw. neu zu definieren
	public void setMessageQueue(Messages m) {
		messageQueue.offer(m);
	}

	public int[][] mapToArray(Map map) {

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
				else if (map.getTile(i, j).isItem())
					world[i][j] = 20;
				else if (map.getTile(i, j).isMonster())
					world[i][j] = 150;
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
//				System.out.print(world[i][j] + ", ");
			}
//			System.out.println();
		}
		return world;

	}

	// bearbeitet eingehende Nachrichten von der ClientEngine
	// Jede Nachricht ist eine Instanz einer anderen Klasse und ruft eine andere
	// Methode auf
	public void handleMessage(Queue<Messages> messages) {
		try {
			while (!messages.isEmpty()) {
				Messages m = messages.poll();

				if (m instanceof ChatMessage) {
					System.out.println("This is a ChatMessage");
					handleChatMessage((ChatMessage) m);
				} else if (m instanceof Cheat) {
					System.out.println("This is a CheatMessage");
					handleCheat((Cheat) m);
				} else if (m instanceof DoorUsage) {
					System.out.println("This is a DoorUsageMessage");
					handleDoorUsage((DoorUsage) m);
				} else if (m instanceof ItemUsage) {
					System.out.println("This is a ItemUsageMessage");
					handleItemUsage((ItemUsage) m);
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
				}
			}
		} catch (Exception e) {

		}
	}

	// Sendet eine Chat-Nachricht an alle Nutzer au�er den Empf�nger
	public void handleChatMessage(ChatMessage m) throws IOException {
		for (UserLogedIn u : userList)
			if (!m.user.equals(u.getUser())) {
				ChatMessage msg = new ChatMessage(m.content, u.getUser(), m.sender);
				messageQueue.offer(msg);
			}
	}

	// Ruft verschiedene Cheatmethoden auf. Bisher nur noFogAnymore
	// implementiert
	public void handleCheat(Cheat m) throws IOException {
		System.out.println("Cheat wurde aktiviert: " + m.content);
		if (m.content == "noFogAnymore")
			fogCheat();
		else if (m.content == "maxHP") {
		}
		// Funtion to set the the HP to the max
		else if (m.content == "allDeadEnemy") {
		}
		// Function to Monster System
		else if (m.content == "ScottyBeamMeUp") {
		}
		// Function for ScottyBeamMeUp

	}

	// Ueberprueft, ob der Nutzer den Schluessel fuer die Tuer hat, falls ja
	// wird getLevel(currentlvl++) aufgerufen
	public void handleDoorUsage(DoorUsage m) throws IOException {
		boolean userKey = false;
		for (UserLogedIn u : userList)
			if (m.user.equals(u.getUser()) && u.getGotKey() == true) {
				u.setGotKey(false);
				userKey = true;
			}
		if (userKey) {
			System.out.println("Die Tuer ist auf");
			currentLvl++;
			System.out.println("To the next Level");
			// getLevel muss von Levelgenerator zur Verf�gung gestellt werden um
			// neues Level zu laden
			// world = getLevel(currentLvl);
			sendGround();
		} else
			System.out.println("Der User hat keinen Key");
	}

	// �berpr�ft ob der Nutzer ein Item aufheben oder Nutzen will und
	// verarbeitet dieses
	// (Bisher nur Trank implementiert) und Levelabh�ngig
	public void handleItemUsage(ItemUsage m) throws IOException {
		if (m.pickup == true) {
			for (UserLogedIn u : userList)
				if (u.getUser().equals(m.user)) {
					if (world[u.getUserPosX()][u.getUserPosY()] > 3 && world[u.getUserPosX()][u.getUserPosY()] < 7) {
						int[] items = u.getItems();
						items[world[u.getUserPosX()][u.getUserPosY()] - 4]++;
						u.setItems(items);
						world[u.getUserPosX()][u.getUserPosY()] = 1;
						sendGround();
						System.out.println("Der User " + u.getUser() + "hat das Item mit der ID"
								+ (world[u.getUserPosX()][u.getUserPosY()] - 4) + " aufgenommen");
					}
					if (world[u.getUserPosX()][u.getUserPosY()] == 7) {
						System.out.println("Der User " + u.getUser() + " hat den Schl�ssel aufgenommen");
						u.setGotKey(true);
						world[u.getUserPosX()][u.getUserPosY()] = 1;
						sendGround();
					}
				}
		} else {
			for (UserLogedIn u : userList)
				if (u.getUser().equals(m.user)) {
					int[] items = u.getItems();
					items[m.itemID]--;
					int health = u.getHealth();
					health = health + 50;
					if (health > 100) {
						health = 100;
					}
					System.out.println("Der User " + u.getUser() + " hat ein Item mit der ID "
							+ (world[u.getUserPosX()][u.getUserPosY()] - 4) + " aufgenommen");
					u.setHealth(health);
				}
		}
	}

	// Diese Methode �berpr�ft ob der Nutzer vorhanden ist und logged ihn in das
	// System ein
	public void handleLogin(Login m) throws IOException {
		boolean userExisting = true;
		Password p = new Password();
		String pw = p.hashing(m.pass);
		for (User u : db.userdata)
			if (u.getUser().equals(m.user))
				if (u.getPassword().equals(pw))
					userExisting = true;

		if (userExisting) {
			System.out.println("Der User ist im System vorhanden und wurde zur Liste hinzugef�gt");
			int[] items = new int[1];
			items[0] = 0;
			userList.add(new UserLogedIn(100, items, m.user, 0, false, userList.size() + 20, 0, 0, 20));
			getAllLevels();
			// Funktion um beim Start alle Level zu �bertragen
		}
	}

	// Diese Methode bewegt den Nutzer �ber die Karte mit Hilfe der neuen
	// Koordinaten
	public void handleMove(Move m) throws IOException {
		for (UserLogedIn u : userList) {
			if (u.getUser().equals(m.user)) {
				System.out.println("Der User " + u.getUser() + " befindet sich an der Position X: " + u.getUserPosX()
						+ " und Y: " + u.getUserPosY());

				// Sobald Level da sind kann es implementiert werden
				world[u.getUserPosX()][u.getUserPosY()] = 1;
				world[m.posx][m.posy] = u.getUserID();
				u.setUserPosX(m.posx);
				u.setUserPosY(m.posy);

				System.out.println("Der User " + u.getUser() + " befindet sich an der neuen Position X: "
						+ u.getUserPosX() + " und Y: " + u.getUserPosY());

				sendGround();
			}
		}

	}

	// Sobald der User angreift �berpr�ft diese Klasse ob ein Monster in der
	// N�he ist und zieht dem Monster HP ab
	// Da keine Level vorhanden monentan ohne Funktion
	public void handlePlayerAttack(PlayerAttack m) throws IOException {
		for (UserLogedIn u : userList)
			if (u.getUser().equals(m.user)) {
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j] >= 10
								&& world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j] <= 15) {
							int monsterID = world[u.getUserPosX() - 1 + i][u.getUserPosY() - 1 + j];
							for (MonstersOnField mon : monsterList)
								if (monsterID == mon.getMonsterID()) {
									mon.setHealth(mon.getHealth() - m.damage);
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
	public void handleLogout(Logout m) throws IOException {
		saveLevel(world, m.user);
		int i = 0;
		for (UserLogedIn u : userList) {
			if (m.user.equals(u.getUser())) {
				userList.remove(i);
			}
			i++;
		}
		for (UserLogedIn u : userList) {
			System.out.println("Nutzer online: " + u.getUser());
		}

	}

	// Diese Methode registriert einen neuen Spieler
	public void handleNewPlayer(NewPlayer m) throws IOException {
		NewPlayer msg = new NewPlayer(m.getUser(), db.addUser(m.getUser(), m.getPw()));
		int[] items = new int[1];
		items[0] = 0;
		userList.add(new UserLogedIn(100, items, m.getUser(), 0, false, userList.size() + 20, 0, 0, 20));
		messageQueue.offer(msg);
		System.out.println("Der Nutzer " + m.getUser() + " wurde registriert.");
		getAllLevels();
		// Funktion um beim Start alle Level zu �bertragen

	}

	// Diese Methode ist �hnlich wie die PlayerAttack allerdings wird hier der
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
							for (MonstersOnField mon : monsterList)
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
	// Nachricht �bergeben
	public void handlePlayerDead(PlayerDead m) throws IOException {
		System.out.println("Player killed");
		int score = 0;
		for (UserLogedIn u : userList)
			if (m.getName().equals(u.getUser()))
				score = u.getScore();
		Highscore highscore = new Highscore(m.getUser(), score);
		db.addHighscore(highscore);

		PlayerDead msg = new PlayerDead(m.getUser(), "GAME OVER");
		messageQueue.offer(msg);
	}

	// Helfermethode um Monster hinzuzuf�gen
	public void addMonsters() {
		for (int i = 0; i < 3; i++) {
			monsterList.add(new MonstersOnField(100, i + 10, i, i, 10));
		}
	}

	// speichert das aktuelle Level beim ausloggen ab
	public void saveLevel(int[][] level, String user) {
		// �berpr�ft ob der Ordner f�r die Spielst�nde existiert und erstellt
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
		// �berpr�ft ob bereits eine Datei besteht
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
			// Datei wird �ber FileReader und BufferedReader eingelesen
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
		for (int i = 0; i <= world.length; i++)
			for (int j = 0; 0 <= world.length; j++)
				darkside[i][j] = false;
		sendGround();
	}

	// Sendet die aktuelle Karte und eine Liste aller Spieler die momentan
	// eingeloggt sind
	private void sendGround() {
		int[][] groundSend = new int[world.length][world.length];
		for (int i = 0; i <= world.length; i++)
			for (int j = 0; j <= world.length; j++)
				if (darkside[i][j] == true)
					groundSend[i][j] = 0;
		// Die Message geht an jeden momentan eingeloggten Nutzer
		for (UserLogedIn u : userList) {
			Level msg = new Level(world, userList);
			messageQueue.offer(msg);
		}
	}

	// Funktion um alle Level auf einemal zu �bertragen
	public void getAllLevels() {
		System.out.println("hol die Levels ab");
		for (int i = 1; i <= 5; i++) {
			int[][] lvl = new int[50][50];
//			for(int k = 0; k < 50; k++){
//				for(int j = 0;  j < 50; j++){
//					lvl[k][j] = 1;
//				}
				
//			}
			map = lg.getLevel(i);
//			System.out.println(map.levelID);
			lvl = mapToArray(map);
//			lvl = getLevel(i);
			// Funktion wird beim Levelgenerator aufgerufen um alle Level zu
			// bekommen
			Level msg = new Level(lvl,map.levelID);
			messageQueue.offer(msg);
//			for(int k = 0; k < 50; k++){
//				for(int j = 0;  j < 50; j++){
//					System.out.print(lvl[k][j] + ", ");
//				}
//				System.out.println();
//				
//			}

			System.out.println();
		}
	}

	// F�r Testzwecke erstelles Array
	public void testArray() {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++)
				world[i][j] = 1;
			world[i][i] = 0;
		}
	}

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
