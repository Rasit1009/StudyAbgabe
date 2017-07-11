package pp2017.team10.server.engine; //Tobias Reimann, MatrNr:5419662

import java.util.LinkedList;

public class Node implements Comparable<Node> {
	// Hier werden die Knoten fuer den AStern verwaltet. Ich habe mich stark an
	// der Vertex-Klasse fuer Dijkstra aus der Vorlesung orientiert

	// Hier die Eigenschaften des Knotens selbst
	final int id;
	private int posx;
	private int posy;
	boolean accessible;

	// Hier notwendige Variablen fuer den AStern
	public double cost = Double.POSITIVE_INFINITY;
	public Node father = null;

	// Adjazenzliste
	private LinkedList<Edge> edges = null;

	public Node(int id, int posx, int posy) {
		this.id = id;
		this.posx = posx;
		this.posy = posy;
		edges = new LinkedList<Edge>();
	}

	public LinkedList<Edge> getEdges() {
		return edges;

	}

	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}

	// nun die adjazenten Knoten hinzufuegen mit id und Kosten

	public void addEdge(Node node, double cost) {
		edges.add(new Edge(node, cost));
	}

	// pruefen ob Knoten adjazent
	public boolean hasEdge(Node node) {
		for (Edge e : edges) {
			if (e.toNode() == node)
				return true;
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public int getposx() {
		return posx;
	}

	public void setposx(int posx) {
		this.posx = posx;
	}

	public int getposy() {
		return posy;
	}

	public void setposy(int posy) {
		this.posy = posy;
	}

	@Override
	public int compareTo(Node anotherNode) {
		// TODO Auto-generated method stub
		return Double.compare(cost, anotherNode.cost);
	}

}
