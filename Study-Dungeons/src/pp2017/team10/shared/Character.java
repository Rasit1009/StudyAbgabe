package pp2017.team10.shared;

/**
 * 
 * @author Güven, Rasit Matnr: 6019617
 *
 */

public class Character {

	public int posx;
	public int posy;
	public int[][] world;

	public Character(int[][] world, int posx, int posy) {
		this.posx = posx;
		this.posy = posy;
		world[posx][posy] = 2;

	}

	public int getPosX() {
		return posx;
	}

	public int getPosY() {
		return posy;
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
