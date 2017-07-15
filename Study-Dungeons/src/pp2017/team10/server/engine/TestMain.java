package pp2017.team10.server.engine; //Tobias Reimann, MatrNr:5419662

import java.util.ArrayList;
import java.util.Random;
import pp2017.team10.shared.*;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int currentposx = 0;// aktuelleposition
		int currentposy = 0;
		int dposx = 0;// Zielposition
		int dposy = 0;

		Node start = null;
		Node destination = null;

		int[][] map = new int[10][10];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = 0; // 0 steht hier fuer eine Wand
			}
		}
		map[5][1] = 1; // Erstellen eines Testlevels fuer meine Wegfindung
		map[4][1] = 1;
		map[3][1] = 1;
		map[3][2] = 1;
		map[2][3] = 1;
		map[4][3] = 1;
		map[5][3] = 1;
		map[6][3] = 1;
		map[3][4] = 1;
		map[6][4] = 1;
		map[4][5] = 1;
		map[3][4] = 1;
		map[3][5] = 1;
		map[5][5] = 1;
		map[6][5] = 1;
		map[3][6] = 1;
		map[6][6] = 1;
		map[7][6] = 1;
		map[8][6] = 1;
		map[6][7] = 1;
		map[6][8] = 1;
		map[7][8] = 1;
		map[8][8] = 1;
		map[3][3] = 2;
		map[8][7] = 3;
		// map[1][0]=7;
		Node[][] mapgraph = new Node[map.length][map.length];
		for (int i = 0; i < mapgraph.length; i++) {
			for (int j = 0; j < mapgraph.length; j++) {
				mapgraph[i][j] = null;
			}
		}

		ArrayList<Node> graph = new ArrayList<Node>();

		for (int i = 0; i < map.length; i++) {
			int id;
			for (int j = 0; j < map.length; j++) { // Durchgehen der Karte mit
													// Erstellung eines Knotens
													// fuer jeden Punkt der
													// Karte
				id = 1000 + j + i * 10;
				mapgraph[i][j] = new Node(id, i, j);

				if (map[i][j] == 0) { // Konsistenzcheck ob die Karte keine Wand
										// ist
					mapgraph[i][j].accessible = false;
				} else {
					mapgraph[i][j].accessible = true;
					graph.add(mapgraph[i][j]);
					if (map[i][j] == 2) {
						destination = mapgraph[i][j];
					}
					if (map[i][j] == 3) {
						start = mapgraph[i][j];
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

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println(" ");
		}

		System.out.println("Folgende Knoten sind erreichbar:");
		for (int i = 0; i < mapgraph.length; i++) {
			for (int j = 0; j < mapgraph.length; j++) {
				if (mapgraph[i][j].accessible == true) {
					System.out.println("Knotenid: " + mapgraph[i][j].id + " X-Koord: " + mapgraph[i][j].getposx()
							+ " Y-Koord: " + mapgraph[i][j].getposy());
				}
			}
		}

		AStar as = new AStar(graph, start, destination);
		// System.out.println("Distanz von Startknoten " + start.getId() + " zum
		// Zielknoten " + destination.getId() + ": " + destination.cost + "
		// [LE]");
		ArrayList<Node> path = as.shortestPath(destination);
		System.out.println(path.get(1).getId());
		while (!path.isEmpty()) {
			int i = 0;
			int id = path.get(i).getId();
			path.remove(i);
			System.out.print(id + "-->");
		}
		Monster M = new Monster(0, 0, 0, 1);
		ArrayList<Node> graph2 = M.readMap(map, 2);// Ich gebe die Karte und die
													// Tiefe des Dungeons an die
													// Kartenauswertung
		int nextdirection = M.useAStar(graph2, M.start, M.destination);
		System.out.println(" ");
		System.out.println(nextdirection);

		M.stroll(graph, 3, 1);

		M.death(0);
		System.out.println("Das Leben des Monsters nach dem Angriff ist " + M.takeDmg());
		System.out.println("Das Leben des Spielers nach dem Angriff ist " + M.attackPlayer());

		Monster M2 = new Monster(0, 4, 3, 2);
		ArrayList<Node> graph3 = M2.readMap(map, 2);
		System.out.println("Das Leben des Monsters nach dem Angriff ist " + M2.takeDmg());
		System.out.println("Das Leben des Spielers nach dem Angriff ist " + M2.attackPlayer());
		M2.escape();
		System.out.println(
				"Y-Koordinate des Monsters ist " + M2.getposx() + " X-Koordinate des Monsters ist " + M2.getposy());// X
																													// und
																													// Y
																													// noch
																													// vertauscht
																													// in
																													// Darstellung
		Monster M3 = new Monster(0, 6, 3, 2);
		ArrayList<Node> graph4 = M3.readMap(map, 2);
		M3.hunt(0);
		System.out.println(
				"Y-Koordinate des Monsters ist " + M3.getposx() + " X-Koordinate des Monsters ist " + M3.getposy());
	}

}
