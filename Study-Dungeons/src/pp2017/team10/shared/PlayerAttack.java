package pp2017.team10.shared;

public class PlayerAttack extends Messages {

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	private static final long serialVersionUID = 5006047215823560813L;

	public int pID;
	public int attack;
	public int damage;
	
	public PlayerAttack(int pID, int attack, int damage){
		this.pID = pID;
		this.attack = attack;
		this.damage = damage;
	}
}
