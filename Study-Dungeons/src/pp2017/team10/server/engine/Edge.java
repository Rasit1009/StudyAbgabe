package pp2017.team10.server.engine; //Tobias Reimann, MatrNr:5419662

public class Edge { // Stark orientiert an der Edge-Klasse fuer Dijkstra aus der
					// Vorlesung

	final Node node;
	final double cost;

	public Edge(Node node, double costToDestination) {
		this.node = node;
		this.cost = costToDestination;
	}

	public double distance() {
		return cost;
	}

	// Zielknoten
	public Node toNode() {
		return node;
	}

}
