package pp2017.team10.server.engine; //Tobias Reimann, MatrNr:5419662

public class Character { // Fuer mich relevante Character-Werte
	private int maxHp;
	private int hp;
	private int dmg; // Schaden, den der Spieler macht

	private int currentposx;
	private int currentposy;

	public Character(int lvl, int startposx, int startposy) {
		setmaxHp(20 + lvl * 2);
		setdmg(5 + lvl);
		setposx(startposx);
		setposy(startposy);
		sethp(getmaxHp());
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
		this.hp = hp;
	}

	public int gethp() {
		return hp;
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

}
