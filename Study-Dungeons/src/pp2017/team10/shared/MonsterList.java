package pp2017.team10.shared;

public class MonsterList {
	
	private int posX;
	private int posY;
	private int monsterID;
	private int maxHealth;
	private int health;
	private boolean key;
	public int damage;
	
	public MonsterList(int monsterID, int posX, int posY, int maxHealth, int health, boolean key, int damage) {
		this.posX = posX;
		this.posY = posY;
		this.monsterID = monsterID;
		this.maxHealth = maxHealth;
		this.health = health;
		this.key = key;
		this.damage = damage;
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
	
	public int getDamage() {
		return damage;
	}
	
	public boolean getKey() {
		return key;
	}
	
}
