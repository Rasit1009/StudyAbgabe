package pp2017.team10.server.engine; //Tobias Reimann, MatrNr:5419662

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Monster {
	private int currentHp;
	private int maxHp;
	private int dmg; // den Schaden, den das Monster macht
	private int dungeondepth; // Indikator wie tief im Spiel wir uns befinden

	ArrayList<Monster> ActiveMonsters = new ArrayList<Monster>();
	private int monsterid;
	UserLogedIn User = null;
	private int map[][] = null;

	private int currentposx;
	private int currentposy;

	Node start = null;
	Node destination = null;
	ArrayList<Node> graph = new ArrayList<Node>();

	// private boolean inSight;
	private boolean escaping;

	private int direction;
	private int type; // 3 Monstertypen(1,2,3): Ausgeglichen, Defense, Offense

	public Monster(int type, int posx, int posy, int dungeondepth) {
		this.type = type;
		this.dungeondepth = dungeondepth;
		setposx(posx);
		setposy(posy);

		if (type == 0) {
			setmaxHp(20 + dungeondepth);
			setdmg(5 + dungeondepth);
		}
		if (type == 1) {
			setmaxHp(maxHp + dungeondepth * 2);
			setdmg(dmg - dungeondepth * 2);
		}
		if (type == 2) {
			setdmg(dmg + dungeondepth * 2);
			setmaxHp(maxHp - dungeondepth * 2);
		}

	}

	/*
	 * public boolean attackPlayerSuccess() { // Vlt wird fuer spaeter, falls es
	 * so // etwas wie einen Blockwert gibt boolean success = true; return
	 * success; }
	 */

	public int takeDmg() { // Gibt das neue Leben des Monsters
							// wieder
		currentHp = maxHp - User.getDamage();
		return currentHp;
	}

	/*
	 * public boolean inRange() { return (Math.sqrt(Math.pow(C.getposx() -
	 * getposx(), 2) + Math.pow(C.getposy() - getposx(), 2)) < 2); }
	 */

	public int attackPlayer() { // gibt den neuen Lebensstand des Spielers
								// wieder
		User.setHealth(User.getHealth() - getdmg());
		return User.getHealth();

	}

	public void move(int direction) { // Geht in eine vorgebene Richtung
		this.direction = direction;
		if (direction == 0) { // Nord
			currentposy -= 1;
		}
		if (direction == 1) { // Ost
			currentposx += 1;
		}
		if (direction == 2) { // Sued
			currentposy += 1;
		}
		if (direction == 3) { // West
			currentposx -= 1;
		}
	}

	public void stroll(ArrayList<Node> graph, int currentposx, int currentposy) {
		boolean viable = false;
		while (viable != true) { // Der Modus in dem das Monster einfach wahllos
									// rumlaeuft
			Random random = new Random();
			direction = random.nextInt(4);// zufaellige Richtung erzeugen
			if (direction == 0) { // jetzt wird ueberprueft, ob in der
									// gewaehlten Richtung ein zulaessiges
									// "Feld" ist.
				for (Node v : graph) { // die Ueberpruefung geschieht, indem im
										// von mir auf Basis der Levelkarte
										// erstellten Graphen einen Knoten mit
										// den selben Koordinaten suchen. Wenn
										// er existiert, ist die Richtung
										// zulaessig
					if (v.getposy() - currentposy == -1 && v.getposx() == currentposx) {
						viable = true;
					}
				}
			} else {
				direction = random.nextInt(4);
			}
			if (direction == 1) {
				for (Node v : graph) {
					if (v.getposx() - currentposx == 1 && v.getposy() == currentposy) {
						viable = true;
					}
				}
			} else {
				direction = random.nextInt(4);
			}
			if (direction == 2) {
				for (Node v : graph) {
					if (v.getposy() - currentposy == 1 && v.getposx() == currentposx) {
						viable = true;
					}
				}
			} else {
				direction = random.nextInt(4);
			}
			if (direction == 3) {
				for (Node v : graph) {
					if (v.getposx() - currentposx == -1 && v.getposy() == currentposy) {
						viable = true;
					}
				}
			} else {
				direction = random.nextInt(4);
			}

		}
		move(direction);

	}

	public void hunt(int dir) { // Monster verfolgt und attackiert den Spieler
		if (Math.pow(getposx() + getposy() - User.getUserPosX() - User.getUserPosY(), 2) == 1) { // Wenn
																					// sich
																					// die
																					// Koordinaten
																					// nur
																					// in
																					// einem
																					// Punkt
																					// unterscheiden,
																					// steht
																					// der
																					// Spieler
																					// neben
																					// dem
																					// Monster
																					// und
																					// wird
																					// angegriffen.
																					// Die
																					// Potenzierung
																					// ist
																					// eine
																					// Vereinfach,
																					// da
																					// bei
																					// dieser
																					// Rechnung
																					// entweder
																					// 0
																					// oder
																					// 1
																					// rauskommen
																					// kann
			attackPlayer();
		} else {
			move(dir);
		}
	}

	public void escape() {// Monster fluechtet aus dem Kampf
		int mx = getposx();
		int my = getposy();
		int px = User.getUserPosX();
		int py = User.getUserPosY();
		int nextStepDirection = 0;
		Node currentMonsterNode = null;
		for (Node u : graph) { // den aktuellen Knoten des Monsters finden
			if (u.getposx() == mx && u.getposy() == my) {
				currentMonsterNode = u;
			}
		}
		if (mx - px == 0 && my - py == 1) { // Monster ist suedlich vom Spieler

			for (Node v : graph) {
				int nodex = v.getposx(); // Zielknoten wird in vom Monster aus
											// gesehen in
				int nodey = v.getposy(); // entgegengesetzter Richtung des
											// Spielers innerhalb einr
											// Reichweite gesetzt
				if (nodey > py && (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) > 2
						&& (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) < 4) {
					nextStepDirection = useAStar(graph, currentMonsterNode, v);
					break;
				}
			}
		}
		if (mx - px == 0 && my - py == -1) { // Monster ist noerdlich vom
												// Spieler

			for (Node v : graph) {
				int nodex = v.getposx();
				int nodey = v.getposy();
				if (nodey < py && (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) > 2
						&& (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) < 4) {
					nextStepDirection = useAStar(graph, currentMonsterNode, v);
					break;
				}
			}
		}
		if (mx - px == 1 && my - py == 0) { // Monster ist oestlich vom Spieler

			for (Node v : graph) {
				int nodex = v.getposx();
				int nodey = v.getposy();
				if (nodex > px && (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) > 2
						&& (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) < 4) {
					nextStepDirection = useAStar(graph, currentMonsterNode, v);
					break;
				}
			}
		}
		if (mx - px == -1 && my - py == 0) { // Monster ist westlich vom Spieler

			for (Node v : graph) {
				int nodex = v.getposx();
				int nodey = v.getposy();
				if (nodex < px && (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) > 2
						&& (int) Math.sqrt(Math.pow(px - nodex, 2) + Math.pow(py - nodey, 2)) < 4) {
					nextStepDirection = useAStar(graph, currentMonsterNode, v);
					break;
				}
			}
		}
		move(nextStepDirection);
		System.out.println("der naechste Schritt auf der Flucht ist " + nextStepDirection);

	}

	public void death(int id) { // Monster stirbt
		ArrayList<Monster> clone = new ArrayList<Monster>(ActiveMonsters);
		for (Monster M : clone) {
			if (M.getid() == id) {
				System.out.println("Folgendes Monster wurde getoetet: " + M.getid());
				ActiveMonsters.remove(M);
			} // es fehlt noch Schluessel fallen lassen
		}

	}

	public void fsm(int playerposx, int playerposy, int[][] map) { // es fehlt
																	// noch eine
																	// Regenaration
																	// der
																	// Monster
																	// und damit
																	// Zustandswechsel
																	// nach
																	// Stroll
																	// und Hunt
		// finite state machine. Der endliche Zustandsautomat
		// Zustaende rumlaufen, jagen und fluechten
		this.map = map;
		// Monster koennen nicht um die Ecke gucken, daher Verfolgung auf diese
		// Weise
		if (gethp() > getmaxHp() / 2) { // Verfolgezustaende, wenn Leben ueber
										// der Haelfte. In Hunt wird verfolgt
										// und angegriffen
			if (playerInSight(playerposx, playerposy) == true) {
				hunt(directToPlayer(playerposx, playerposy));

			} else {
				stroll(graph, getposx(), getposy());
			}
		} else if (gethp() <= getmaxHp() / 2 && gethp() > 0) { // Ab der Haelfte
																// fluechtet das
																// Monster
			escape();
		} else if (gethp() == 0) { // Bei 0 HP stirbt das Monster
			death(getid());
		}

	}

	public boolean playerInSight(int playerposx, int playerposy) {
		boolean inSight = false;
		if ((playerposx == getposx() && playerposy - getposy() <= 3 && playerposy - getposy() > 1)
				|| (playerposx == getposx() && playerposy - getposy() >= -3 && playerposy - getposy() < -1)
				|| (playerposy == getposy() && playerposx - getposx() >= -3 && playerposx - getposx() < -1)
				|| (playerposy == getposy() && playerposx - getposx() <= 3 && playerposx - getposx() > 1))
			inSight = true;
		return inSight;
	}

	public int directToPlayer(int playerposx, int playerposy) { // gibt die
																// Richtung an,
																// in der das
																// Monster den
																// Spieler sieht
		int dir = 0;
		if (playerposx == getposx() && playerposy - getposy() <= 3 && playerposy - getposy() > 1) { // Spieler
			// ist
			// suedlich
			// vom
			// Monster
			dir = 2;
		} else if (playerposx == getposx() && playerposy - getposy() >= -3 && playerposy - getposy() < -1) { // Spieler
			// ist
			// noerdlich
			// vom
			// Monster
			dir = 0;
		} else if (playerposy == getposy() && playerposx - getposx() >= -3 && playerposx - getposx() < -1) { // Spieler
			// ist
			// westlich
			// vom
			// Monster
			dir = 3;
		} else if (playerposy == getposy() && playerposx - getposx() <= 3 && playerposx - getposx() > 1) {// Spieler
			// ist
			// oestlich
			// vom
			// Monster
			dir = 1;
		}
		return dir;
	}

	public ArrayList<Node> readMap(int[][] map, int dungeondepth) { // Die Karte
																	// wird
																	// ausgelesen
																	// und die
																	// Nicht-Wand-Felder
																	// werden in
																	// einen
																	// Graphen
																	// gespeichert
		Node mapgraph[][] = new Node[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			int id;
			for (int j = 0; j < map.length; j++) { // Durchgehen der Karte mit
													// Erstellung eines Knotens
													// fuer jeden Punkt der
													// Karte
				id = 1000 + j + i * 10;
				mapgraph[i][j] = new Node(id, j, i);

				if (map[i][j] == 0) { // Konsistenzcheck ob die Karte keine Wand
										// ist
					mapgraph[i][j].accessible = false;
				} else {
					mapgraph[i][j].accessible = true;
					graph.add(mapgraph[i][j]);
					if (map[i][j] == 2) {
						destination = mapgraph[i][j];
						//User = new UserLogedIn(1, j, i);
					}
					if (map[i][j] == 3) {
						start = mapgraph[i][j];
						monsterid = 0;
						Random random = new Random();
						type = random.nextInt(3);
						Monster M = new Monster(type, j, i, dungeondepth);
						M.monsterid = id;
						id++;
						ActiveMonsters.add(M);
					}

				}

			}

		}
		for (int i = 0; i < mapgraph.length; i++) {
			for (int j = 0; j < mapgraph.length; j++) {
				if (i != mapgraph.length - 1) {
					if (mapgraph[i + 1][j].accessible == true) { // 4
																	// if-Abfragen
																	// um zu
																	// garantieren,
																	// dass wir
																	// innerhalb
																	// der Karte
																	// bleiben
						mapgraph[i][j].addEdge(mapgraph[i + 1][j], 1);
					}
				} // Hinzufuegen der Kanten zu den begehbaren Kanten
				if (i != 0) {
					if (mapgraph[i - 1][j].accessible == true) {
						mapgraph[i][j].addEdge(mapgraph[i - 1][j], 1);
					}
				}
				if (j != mapgraph.length - 1) {
					if (mapgraph[i][j + 1].accessible == true && j != map.length) {
						mapgraph[i][j].addEdge(mapgraph[i][j + 1], 1);
					}
				}
				if (j != 0) {
					if (mapgraph[i][j - 1].accessible == true && j != 0) {
						mapgraph[i][j].addEdge(mapgraph[i][j - 1], 1);
					}
				}

			}
		}
		return graph;
	}

	public int useAStar(ArrayList<Node> graph, Node start, Node destination) { // Astern
																				// wird
																				// verwendet
																				// um
																				// die
																				// Richtung
																				// des
																				// naechsten
																				// Schrittes
																				// zu
																				// erfahren
		int currentdirection = 0;
		this.start = start;
		this.destination = destination;
		this.graph = graph;
		AStar as = new AStar(this.graph, this.start, this.destination);
		ArrayList<Node> path = as.shortestPath(this.destination);
		Node nextNode = path.get(1);
		if (nextNode.getposx() - start.getposx() == 0 && nextNode.getposy() - start.getposy() == 1)
			currentdirection = 2;
		if (nextNode.getposx() - start.getposx() == 0 && nextNode.getposy() - start.getposy() == -1)
			currentdirection = 0;
		if (nextNode.getposx() - start.getposx() == 1 && nextNode.getposy() - start.getposy() == 0)
			currentdirection = 1;
		if (nextNode.getposx() - start.getposx() == -1 && nextNode.getposy() - start.getposy() == 0)
			currentdirection = 3;
		return currentdirection;
	}

	public void setposx(int posx) {
		this.currentposx = posx;
	}

	public int getposx() {
		return this.currentposx;
	}

	public void setposy(int posy) {
		this.currentposy = posy;
	}

	public int getposy() {
		return this.currentposy;
	}

	public void sethp(int hp) {
		this.currentHp = hp;
	}

	public int gethp() {
		return currentHp;
	}

	public void setmaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getmaxHp() {
		return maxHp;
	}

	public void setdmg(int dmg) {
		this.dmg = dmg;
	}

	public int getdmg() {
		return dmg;
	}

	public void setescaping(boolean escaping) {
		this.escaping = escaping;
	}

	public boolean getescaping() {
		return escaping;
	}

	public void setid(int id) {
		this.monsterid = id;
	}

	public int getid() {
		return monsterid;
	}

	public ArrayList<Monster> getActiveMonsters() {
		return ActiveMonsters;
	}

}
