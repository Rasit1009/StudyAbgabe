package pp2017.team10.shared;

public class MonsterList {
	
	private int posX;
	private int posY;
	private int monsterID;
	private int maxHealth;
	private int health;
	private boolean key;
	
	public MonsterList(int monsterID, int posX, int posY, int maxHealth, int health, boolean key) {
		this.posX = posX;
		this.posY = posY;
		this.monsterID = monsterID;
		this.maxHealth = maxHealth;
		this.health = health;
		this.key = key;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMonsterID() {
		return monsterID;
	}
	
	public boolean getKey() {
		return key;
	}
	
}
