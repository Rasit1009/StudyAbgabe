package pp2017.team10.shared;

/**
 * 
 * @author Güven, Rasit Matnr: 6019617
 *
 */

public class Character {

	public int posx;
	public int posy;
	public int[][] charPos;

	public Character(int posx, int posy) {
	this.posx = posx;
	this.posy = posy;
		
	}

	public int getPosX() {
		return posx;
	}

	public int getPosY() {
		return posy;
	}

	public int setNewXPos(int posx) {

		this.posx = posx;
		return posx;

	}

	public int setNewYPos(int posy) {
		this.posy = posy;
		return posy;
	}

}
