package pp2017.team10.server.map;

import java.util.Random;

import pp2017.team10.shared.Map;
import pp2017.team10.shared.Tiles;

/*
 * @author Burcu Akcay
 * 
 * Die Klasse LevelGenerator ist unsere Hauptklasse. Hier generieren wird den Algorithmus flood-fill zur Erzeugung von Dungeons.
 * Ich habe mich an die Datei "Strategien zur Dungeon-Erzeugung" im Ilias-Ordner angelehnt.
 * */

public class LevelGenerator {

	public Tiles[][] university;
	public int direction;;
	public int xCoord;
	public int yCoord;

	LevelGenerator(int s1, int s2) {
		this.university = new Tiles[s1][s2];
		this.xCoord = s1;
		this.yCoord = s2;
	}

	public LevelGenerator() {
		this(50, 50);
		buildWiso();

	}

	public Tiles[][] getUniversity() { // gibt University der Matrix-Art zurück
		return this.university;
	}

	public void setUniversity(Tiles[][] university) { // setzt die University
		this.university = university;
	}

	/*
	 * gibt einen Boolean-Wert zuürck, ob die Stelle erlaubt ist
	 */

	public boolean isOK(int xCoord, int yCoord) {
		if (xCoord >= 3 && xCoord < university.length - 4 && yCoord >= 3 && yCoord < university[0].length - 4) {
			return true;
		}
		return false;
	}

	/*
	 * Hier wird der Algorithmus FloodFill implementiert in Anlehnung an die
	 * Datei "Strategien zur Dungeon-Erzeugung.
	 */
	public void floodFill(int xCoord, int yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		if (isOK(xCoord, yCoord) == true) {
			university[xCoord][yCoord].setIsWall(false);
			Random random = new Random();
			this.direction = random.nextInt(4);
			switch (direction) {

			case 0:

				if (isOK(xCoord, yCoord + 2) == true && university[xCoord][yCoord + 2].isWall()) {
					university[xCoord][yCoord + 1].setIsWall(false);
					floodFill(xCoord, yCoord + 2);
				}

				break;

			case 1:
				if (isOK(xCoord + 2, yCoord) == true && university[xCoord + 2][yCoord].isWall()) {
					university[xCoord + 1][yCoord].setIsWall(false);
					floodFill(xCoord + 2, yCoord);
				}

				break;
			case 2:
				if (isOK(xCoord, yCoord - 2) == true && university[xCoord][yCoord - 2].isWall()) {
					university[xCoord][yCoord - 1].setIsWall(false);
					floodFill(xCoord, yCoord - 2);
				}
				break;

			case 3:
				if (isOK(xCoord - 2, yCoord) == true && university[xCoord - 2][yCoord].isWall()) {
					university[xCoord - 1][yCoord].setIsWall(false);
					floodFill(xCoord - 2, yCoord);
				}
				break;

			default:
				break;
			}

			// rechts
			if (isOK(xCoord, yCoord + 2) == true && university[xCoord][yCoord + 2].isWall()) {
				university[xCoord][yCoord + 1].setIsWall(false);
				floodFill(xCoord, yCoord + 2);
			}

			// unten
			if (isOK(xCoord + 2, yCoord) == true && university[xCoord + 2][yCoord].isWall()) {
				university[xCoord + 1][yCoord].setIsWall(false);
				floodFill(xCoord + 2, yCoord);
			}
			// links
			if (isOK(xCoord, yCoord - 2) == true && university[xCoord][yCoord - 2].isWall()) {
				university[xCoord][yCoord - 1].setIsWall(false);
				floodFill(xCoord, yCoord - 2);
			}

			// oben
			if (isOK(xCoord - 2, yCoord) == true && university[xCoord - 2][yCoord].isWall()) {
				university[xCoord - 1][yCoord].setIsWall(false);
				floodFill(xCoord - 2, yCoord);
			}

		}

	}

	public Map buildWiso() { // unser Level Wiso wird erstellt
		// WISO wird in Map gespeichert
		Map wiso = new Map();
		wiso.setLevelID(1);
		wiso.setSize_x(50);
		wiso.setSize_y(50);

		wiso.setStartpos(university.length / 2, university.length - 3);
		wiso.setExitpos(1, university.length / 2);

		for (int i = 0; i < university.length; i++) {
			for (int j = 0; j < university.length; j++) {
				university[i][j] = new Tiles(false, true);
				university[i][j].setIsWall(true);
			}
		}

		floodFill(3, 3);

		// verschiedene Tiles werden gesetzt

		for (int i = 6; i < university.length - 7; i++) {
			for (int j = 7; j < university.length - 8; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsFloor(true);
			}
		}

		for (int i = 6; i < university.length - 7; i++) {
			for (int j = (university.length / 3) + 15; j < university.length - 8; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsMarble(true);
			}
		}

		for (int i = 16; i < 37; i++) {
			for (int j = 37; j < 47; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsFloor(true);
			}
		}

		for (int i = (university.length / 2); i < (university.length) - 15; i++) {
			for (int j = 10; j < 40; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsColumn(true);
			}
		}

		randomMonster(2);
		randomItem(2);
		door();
		printUniversity();

		wiso.setGround(university);
		return wiso;
	}

	public Map buildPhilo() {
		// Philo wird in Map gespeichert
		Map philo = new Map();
		philo.setLevelID(2);
		philo.setSize_x(50);
		philo.setSize_y(50);

		philo.setStartpos(university.length / 2, university.length - 3);
		philo.setExitpos(1, university.length / 2);

		for (int i = 0; i < university.length; i++) {
			for (int j = 0; j < university.length; j++) {
				university[i][j] = new Tiles(false, true);
				university[i][j].setIsStone(true);
			}
		}
		floodFill(3, 3);

		// verschiedene Tiles werden gesetzt
		for (int i = 3; i < 11; i++) {
			for (int j = 16; j < 37; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsMarble(true);
			}
		}

		for (int i = 35; i < 45; i++) {
			for (int j = 8; j < 17; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsFloor(true);
			}
		}

		for (int i = 16; i < 37; i++) {
			for (int j = 37; j < 47; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsFloor(true);
			}
		}
		randomMonster(3);
		randomItem(2);
		door();
		printUniversity();

		philo.setGround(university);
		return philo;
	}

	public Map buildLibrary() {

		// libary wird in Map gespeichert
		Map library = new Map();
		library.setLevelID(3);
		library.setSize_x(50);
		library.setSize_y(50);

		library.setStartpos(university.length / 2, university.length - 3);
		library.setExitpos(1, university.length / 2);

		for (int i = 0; i < university.length; i++) {
			for (int j = 0; j < university.length; j++) {
				university[i][j] = new Tiles(false, true);
				university[i][j].setIsFloor(true);
			}
		}

		floodFill(3, 3);

		// verschiedene Tiles werden gesetzt

		for (int i = 5; i < university.length / 3; i++) {
			for (int j = 5; j < university.length / 3; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsColumn(true);
			}
		}

		for (int i = 5; i < university.length / 3; i++) {
			for (int j = (university.length / 3) + 15; j < university.length - 8; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsColumn(true);
			}
		}

		for (int i = (university.length / 2); i < (university.length) - 15; i++) {
			for (int j = 10; j < 40; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsColumn(true);
			}
		}
		randomMonster(4);
		randomItem(2);
		door();
		printUniversity();

		library.setGround(university);

		return library;
	}

	public Map buildPhysik() {
		// physik wird in Map gespeichert
		Map physik = new Map();
		physik.setLevelID(4);
		physik.setSize_x(50);
		physik.setSize_y(50);

		physik.setStartpos(university.length / 2, university.length - 3);
		physik.setExitpos(1, university.length / 2);

		for (int i = 0; i < university.length; i++) {
			for (int j = 0; j < university.length; j++) {
				university[i][j] = new Tiles(false, true);
				university[i][j].setIsFloor(true);
			}
		}
		floodFill(3, 3);

		randomMonster(5);
		randomItem(2);
		door();
		printUniversity();

		physik.setGround(university);

		return physik;
	}

	public Map buildCopt() {
		// copt wird in Map gespeichert
		Map copt = new Map();
		copt.setLevelID(5);
		copt.setSize_x(50);
		copt.setSize_y(50);

		copt.setStartpos(university.length / 2, university.length - 3);
		copt.setExitpos(1, university.length / 2);

		for (int i = 0; i < university.length; i++) {
			for (int j = 0; j < university.length; j++) {
				university[i][j] = new Tiles(false, true);
				university[i][j].setIsStone(true);
			}
		}

		floodFill(3, 3);

		// verschiedene Tiles werden gesetzt
		for (int i = 16; i < 35; i++) {
			for (int j = 16; j < 35; j++) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsMarble(true);
			}
		}

		for (int i = 36; i < 43; i++) {
			for (int j = 20; j < 30; j = j + 2) {
				university[i][j] = new Tiles(true, false);
				university[i][j].setIsExit(true);
			}
		}
		randomMonster(6);
		randomItem(2);
		door();
		printUniversity();
		copt.setGround(university);
		return copt;
	}

	/*
	 * zufällige Verteilung von Items Umgebung wird geprüft, ob ein Item
	 * hingelegt werden kann
	 */
	public void randomItem(int amount) {

		Random rand = new Random();
		int count = amount;

		for (int i = 0; i < count; i++) {
			xCoord = rand.nextInt(university.length);
			yCoord = rand.nextInt(university.length);
			if (isOK(xCoord, yCoord) == true) {
				if (university[xCoord][yCoord].isItem() == false && university[xCoord][yCoord].isMonster() == false
						&& university[xCoord][yCoord].isWall() == false
						&& university[xCoord][yCoord].isColumn() == false
						&& university[xCoord + 1][yCoord].isItem() == false
						&& university[xCoord + 1][yCoord].isMonster() == false
						&& university[xCoord + 1][yCoord].isWall() == false
						&& university[xCoord + 1][yCoord].isColumn() == false
						&& university[xCoord - 1][yCoord].isItem() == false
						&& university[xCoord - 1][yCoord].isMonster() == false
						&& university[xCoord - 1][yCoord].isWall() == false
						&& university[xCoord][yCoord].isColumn() == false
						&& university[xCoord][yCoord + 1].isItem() == false
						&& university[xCoord][yCoord + 1].isMonster() == false
						&& university[xCoord][yCoord + 1].isWall() == false
						&& university[xCoord][yCoord + 1].isColumn() == false
						&& university[xCoord][yCoord - 1].isItem() == false
						&& university[xCoord][yCoord - 1].isMonster() == false
						&& university[xCoord][yCoord - 1].isWall() == false
						&& university[xCoord][yCoord - 1].isColumn() == false
						&& university[xCoord - 1][yCoord + 1].isItem() == false
						&& university[xCoord - 1][yCoord + 1].isMonster() == false
						&& university[xCoord - 1][yCoord + 1].isWall() == false
						&& university[xCoord - 1][yCoord + 1].isColumn() == false
						&& university[xCoord + 1][yCoord + 1].isItem() == false
						&& university[xCoord + 1][yCoord + 1].isMonster() == false
						&& university[xCoord + 1][yCoord + 1].isWall() == false
						&& university[xCoord + 1][yCoord + 1].isColumn() == false
						&& university[xCoord + 1][yCoord - 1].isItem() == false
						&& university[xCoord + 1][yCoord - 1].isMonster() == false
						&& university[xCoord + 1][yCoord - 1].isWall() == false
						&& university[xCoord + 1][yCoord - 1].isColumn() == false
						&& university[xCoord - 1][yCoord - 1].isItem() == false
						&& university[xCoord - 1][yCoord - 1].isMonster() == false
						&& university[xCoord - 1][yCoord - 1].isWall() == false
						&& university[xCoord - 1][yCoord - 1].isColumn() == false) {
					university[xCoord][yCoord].setIsFloor(true);
					university[xCoord][yCoord].setIsItem(true);
					university[xCoord][yCoord].setIsStone(true);
					university[xCoord][yCoord].setIsMarble(true);

				} else {
					count++;
				}
			} else {
				count++;
			}
		}
	}

	/*
	 * zufällige Verteilung von Monster Umgebung wird geprüft, ob ein Monster
	 * hingelegt werden kann
	 * 
	 */

	public void randomMonster(int amount) {

		Random rand = new Random();
		int count = amount;

		for (int i = 0; i < count; i++) {
			xCoord = rand.nextInt(university.length);
			yCoord = rand.nextInt(university.length);
			if (isOK(xCoord, yCoord) == true) {
				if (university[xCoord][yCoord].isMonster() == false && university[xCoord][yCoord].isItem() == false
						&& university[xCoord][yCoord].isWall() == false
						&& university[xCoord][yCoord].isColumn() == false
						&& university[xCoord + 1][yCoord].isMonster() == false
						&& university[xCoord + 1][yCoord].isItem() == false
						&& university[xCoord + 1][yCoord].isWall() == false
						&& university[xCoord + 1][yCoord].isColumn() == false
						&& university[xCoord - 1][yCoord].isMonster() == false
						&& university[xCoord - 1][yCoord].isItem() == false
						&& university[xCoord - 1][yCoord].isWall() == false
						&& university[xCoord - 1][yCoord].isColumn() == false
						&& university[xCoord][yCoord + 1].isMonster() == false
						&& university[xCoord][yCoord + 1].isItem() == false
						&& university[xCoord][yCoord + 1].isWall() == false
						&& university[xCoord][yCoord + 1].isColumn() == false
						&& university[xCoord][yCoord - 1].isMonster() == false
						&& university[xCoord][yCoord - 1].isItem() == false
						&& university[xCoord][yCoord - 1].isWall() == false
						&& university[xCoord][yCoord - 1].isColumn() == false
						&& university[xCoord - 1][yCoord + 1].isMonster() == false
						&& university[xCoord - 1][yCoord + 1].isItem() == false
						&& university[xCoord - 1][yCoord + 1].isWall() == false
						&& university[xCoord - 1][yCoord + 1].isColumn() == false
						&& university[xCoord + 1][yCoord + 1].isMonster() == false
						&& university[xCoord + 1][yCoord + 1].isItem() == false
						&& university[xCoord + 1][yCoord + 1].isWall() == false
						&& university[xCoord + 1][yCoord + 1].isColumn() == false
						&& university[xCoord + 1][yCoord - 1].isMonster() == false
						&& university[xCoord + 1][yCoord - 1].isItem() == false
						&& university[xCoord + 1][yCoord - 1].isWall() == false
						&& university[xCoord + 1][yCoord - 1].isColumn() == false
						&& university[xCoord - 1][yCoord - 1].isMonster() == false
						&& university[xCoord - 1][yCoord - 1].isItem() == false
						&& university[xCoord - 1][yCoord + 1].isWall() == false
						&& university[xCoord - 1][yCoord - 1].isColumn() == false) {
					university[xCoord][yCoord].setIsFloor(true);
					university[xCoord][yCoord].setIsMonster(true);
					university[xCoord][yCoord].setIsStone(true);
					university[xCoord][yCoord].setIsMarble(true);
					/*
					 * in der folgenden if-Abfrage wird dem ersten Monster, das
					 * auf die Map gelegt wird ein Key zugewiesen. Dieser ist
					 * notwendig, um das nächste Level zu erreichen.
					 */
					if (count < 1) {
						university[xCoord][yCoord].setIsKey(true);
					}
				} else {
					count++;
				}
			} else {
				count++;
			}
		}
	}

	// Türen für Eingang und Ausgang werden gesetzt
	public void door() {
		// Ausgang
		university[0][university.length / 2].setIsWall(false);
		university[1][university.length / 2].setIsWall(false);
		university[2][university.length / 2].setIsWall(false);

		university[1][university.length / 2].setIsStone(false);
		university[1][university.length / 2].setIsMarble(false);
		university[1][university.length / 2].setIsFloor(false);

		university[1][university.length / 2].setIsExit(true);

		// Eingang
		university[university.length - 1][university.length / 2].setIsWall(false);
		university[university.length - 2][university.length / 2].setIsWall(false);
		university[university.length - 3][university.length / 2].setIsWall(false);
		university[university.length - 4][university.length / 2].setIsWall(false);

		university[university.length - 3][university.length / 2].setIsStone(false);
		university[university.length - 3][university.length / 2].setIsMarble(false);
		university[university.length - 3][university.length / 2].setIsFloor(false);

		university[university.length - 3][university.length / 2].setIsEntrance(true);

		/*
		 * Player wird direkt vor die Tür gestellt
		 */

		university[university.length - 4][university.length / 2].setIsPlayer(true);

	}

	public void printUniversity() {

		for (int i = 0; i < university.length - 1; i++) {
			for (int j = 0; j < university[j].length - 1; j++) {
				if (university[i][j].isWall()) {
					System.out.print("X");
				} else if (university[i][j].isItem()) {
					System.out.print("<");
				} else if (university[i][j].isMonster()) {
					System.out.print("M");
				} else if (university[i][j].isPlayer()) {
					System.out.print("P");
				} else if (university[i][j].isDoor()) {
					System.out.print("D");
				} else if (university[i][j].isExit()) {
					System.out.print("@");
				} else if (university[i][j].isFloor()) {
					System.out.print(".");
				} else if (university[i][j].isStone()) {
					System.out.print("+");
				} else if (university[i][j].isMarble()) {
					System.out.print("~");
				} else if (university[i][j].isEntrance()) {
					System.out.print("E");
				} else if (university[i][j].isColumn()) {
					System.out.print("F");
				} else if (university[i][j].isKey()) {
					System.out.print("K");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}
