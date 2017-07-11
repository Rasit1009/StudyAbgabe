package pp2017.team10.server.map;

public class Map {

	/*
	 * @author Burcu Akcay
	 * 
	 * Diese Klasse beinhaltet die Datenstuktur für die Map
	 * 
	 * 
	 */

	public int levelID;
	public int size_x;
	public int size_y;
	public Tiles[][] ground;
	public int[] startpos;
	public int[] exitpos;

	public Map() {

	}

	public Map(int levelID, int size_x, int size_y, Tiles[][] ground, int[] startpos, int[] exitpos) {
		this.levelID = levelID;
		this.size_x = size_x;
		this.size_y = size_y;
		this.ground = ground;
		this.startpos = startpos;
		this.exitpos = exitpos;
	}

	public Tiles getTile(int pos_x, int pos_y) { // gibt die Tile zurück
		return this.ground[pos_x][pos_y];
	}

	public void setTile(int pos_x, int pos_y, Tiles newTile) { // setzt die Tile
		this.ground[pos_x][pos_y] = newTile;
	}

	public int getLevelID() { // gibt die levelID zurück
		return levelID;
	}

	public void setLevelID(int levelID) { // setzt die levelID
		this.levelID = levelID;
	}

	public int getSize_x() { // gibt die Länge der X-Achse zurück
		return size_x;
	}

	public void setSize_x(int size_x) { // setzt die Länge der X-Achse
		this.size_x = size_x;
	}

	public int getSize_y() { // gibt die Länge der Y-Achse zurück
		return size_y;
	}

	public void setSize_y(int size_y) { // setzt die Länge der Y-Achse
		this.size_y = size_y;
	}

	public Tiles[][] getGround() { // gibt das Spielfeld zurück
		return ground;
	}

	public void setGround(Tiles[][] ground) { // setzt das Spielfeld
		this.ground = ground;
	}

	public int[] getStartpos() { // gibt die Startposition zurück
		return startpos;
	}

	public void setStartpos(int[] startpos) { // setzt die Startposition
		this.startpos = startpos;
	}

	public void setStartpos(int pos_x, int pos_y) { // wird überladen
		int[] position = { pos_x, pos_y };
		this.startpos = position;
	}

	public int[] getExitpos() { // gibt die Endposition zurück
		return exitpos;
	}

	public void setExitpos(int[] exitpos) { // setzt die Endposition
		this.exitpos = exitpos;
	}

	public void setExitpos(int pos_x, int pos_y) { // wird überladen
		int[] position = { pos_x, pos_y };
		this.exitpos = position;
	}
}
