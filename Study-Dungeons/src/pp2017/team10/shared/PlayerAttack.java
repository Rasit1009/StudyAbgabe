package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class PlayerAttack extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = 5006047215823560813L;

	public String user;
	public int attack;
	public int damage;

	public PlayerAttack(String pID, int attack, int damage) {
		this.user = pID;
		this.attack = attack;
		this.damage = damage;
	}
}
