package pp2017.team10.server.map;

public class Tiles {

	/*
	 * @author Burcu Akcay
	 * 
	 * Die Klasse Tiles beinhaltet die einfache Datenstuktur der Tiles.
	 */

	public boolean isEntrance;
	public boolean isExit;
	public boolean isMonster;
	public boolean isPlayer;
	public boolean isWall;
	public boolean isDoor;
	public boolean isFloor;
	public boolean isWalkable;
	public boolean isItem;
	public boolean isStone;
	public boolean isMarble;
	public boolean isColumn;
	public boolean isKey;

	/*
	 * Konstruktor erhält einen boolean-Wert, ob es begehbar ist oder ob dort
	 * eine Wand ist
	 */
	public Tiles(boolean isWalkable, boolean isWall) {
		this.isWalkable = isWalkable;
		this.isWall = isWall;
	}

	public boolean isWalkable() { // boolean-Wert ob begehbar
		return isWalkable;
	}

	public boolean isEntrance() { // boolean-Wert für den Eingang
		return isEntrance;
	}

	public void setIsEntrance(boolean status) { // setzt den Eingang
		this.isEntrance = status;
	}

	public boolean isExit() { // boolean-Wert für Ausgang
		return isExit;
	}

	public void setIsExit(boolean status) { // setzt den Ausgang
		this.isExit = status;
	}

	public boolean isMonster() { // boolean-Wert für Monster
		return isMonster;
	}

	public void setIsMonster(boolean status) { // setzt den Monster
		this.isMonster = status;
	}

	public boolean isPlayer() { // boolean-Wert für den Spieler
		return isPlayer;
	}

	public void setIsPlayer(boolean status) { // setzt den Spieler
		this.isPlayer = status;
	}

	public boolean isWall() { // boolean-Wert für die Wand
		return isWall;
	}

	public void setIsWall(boolean status) { // setzt die Wand
		this.isWall = status;
	}

	public boolean isDoor() { // boolean-Wert für die Tür
		return isWall;
	}

	public void setIsDoor(boolean status) { // setzt die Tür
		this.isDoor = status;
	}

	public boolean isItem() { // boolean-Wert für Item
		return isItem;
	}

	public void setIsItem(boolean status) { // setzt Item
		this.isItem = status;
	}

	public boolean isStone() { // boolean-Wert für Steon
		return isStone;
	}

	public void setIsStone(boolean status) { // setzt Stein
		this.isStone = status;
	}

	public boolean isMarble() { // boolean-Wert für Marmor
		return isStone;
	}

	public void setIsMarble(boolean status) { // setzt Marmor
		this.isMarble = status;
	}

	public boolean isFloor() { // boolean-Wert für Boden
		return isFloor;
	}

	public void setIsFloor(boolean status) { // setzt Boden
		this.isFloor = status;
	}

	public boolean isColumn() { // boolean-Wert für Säule
		return isColumn;
	}

	public void setIsColumn(boolean status) { // setzt Säule
		this.isColumn = status;
	}

	public boolean isKey() { // boolean-Wert für Schlüssel
		return isKey;
	}

	public void setIsKey(boolean status) { // setzt den Schlüssel
		this.isKey = status;
	}
}
