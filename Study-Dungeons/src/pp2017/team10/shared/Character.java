package pp2017.team10.shared;

/**
 * 
 * @author Güven, Rasit Matnr: 6019617
 *
 */

public class Character {

	public int posx;
	public int posy;
	public int playerID;

	public Character(int playerID, int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
		this.playerID = playerID;

	}

	public int getPosX() {
		return posx;
	}

	public int getPosY() {
		return posy;
	}
	
	public int getplayerID(){
		return playerID;
	}

	public int setXPos(int posx) {

		this.posx = posx;
		return posx;

	}

	public int setYPos(int posy) {
		this.posy = posy;
		return posy;
	}

}
