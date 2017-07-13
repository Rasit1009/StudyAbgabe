package pp2017.team10.server.engine;

/*
 * Klasse um Monster auf dem Feld zu definieren und zu steuern inklusiv der get- und set- Methoden
 */
/**
 * Author: Felix Schifferdecker, 5585147
 */

public class MonstersOnField {
	private int health;
	private int monsterID;
	private int monsterPosX;
	private int monsterPosY;
	private int damage;
	
	public MonstersOnField(int health, int monsterID, int monsterPosX, int monsterPosY, int damage){
		this.health = health;
		this.monsterID = monsterID;
		this.monsterPosX = monsterPosX;
		this.monsterPosY = monsterPosY;
		this.damage = damage;
	}
	
	public int getHealth(){
		return health;
	}
	public int getMonsterID(){
		return monsterID;
	}
	public int getMonserPosX(){
		return monsterPosX;
	}
	public int getMonsterPosY(){
		return monsterPosY;
	}
	public int getDamage(){
		return damage;
	}
	public void setHealth(int health){
		this.health = health;
	}
}
