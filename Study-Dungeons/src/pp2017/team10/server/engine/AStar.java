package pp2017.team10.server.engine; //Tobias Reimann, MatrNr:5419662

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class AStar {
	private Node start;

	public AStar(ArrayList<Node> Nodes, Node start, Node destination) { // Initialisierung
		this.start = start;
		for (Node v : Nodes) {
			v.cost = Double.POSITIVE_INFINITY;
			v.father = null;
		}
		findPath(start, destination);

	}

	private static void findPath(Node start, Node destination) {
		start.cost = 0;
		PriorityQueue<Node> NodeQueue = new PriorityQueue<Node>();
		NodeQueue.add(start);
		while (!NodeQueue.isEmpty()) { // Solange Schlange nicht leer
			Node u = NodeQueue.poll(); // Oberstes Element herausnehmen

			for (Edge e : u.getEdges()) { // fuer alle zu u gehoerenden Kanten
				Node v = e.toNode();
				double costToDestination = u.cost + e.cost + euclid(v, destination);// bisherige
																					// Kosten
																					// +
																					// Kosten
																					// des
																					// Weges
																					// von
																					// u
																					// nach
																					// v
																					// +
																					// euklidische
																					// Abstand
																					// zum
																					// Ziel
																					// =
																					// Effizienz
																					// zum
																					// Ziel
																					// des
																					// Knotens
																					// v
				if (costToDestination < v.cost) {
					NodeQueue.remove(v);
					v.cost = costToDestination;
					v.father = u;
					NodeQueue.add(v);

				}

			}
		}
	}

	public ArrayList<Node> shortestPath(Node destination) {
		ArrayList<Node> path = new ArrayList<Node>();
		Node node = destination;
		for (; node.father != null; node = node.father)
			path.add(node);
		if (node == start)
			path.add(node);
		Collections.reverse(path);
		return path;
	}

	private static double euclid(Node start, Node destination) { // euklidischer
																	// Abstand
																	// zweier
																	// Knoten
																	// ausrechnen
		int sposx = start.getposx(); // Start und Zielkoordinaten
		int sposy = start.getposy();
		int dposx = destination.getposx();
		int dposy = destination.getposy();
		double result = Math.sqrt((sposx - dposx) * (sposx - dposx) + (sposy - dposy) * (sposy - dposy));
		return result;
	}

}
