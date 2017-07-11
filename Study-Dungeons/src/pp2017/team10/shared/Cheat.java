package pp2017.team10.shared;

/**
 * In the following class we are going to define our cheats, which are activated
 * by typing the right String into the Chat Window.
 * 
 * @author Güven, Rasit Matnr: 6019617
 */
public class Cheat extends Messages {

public String content;
public int pID;
	
	private static final long serialVersionUID = -7123423244900792410L;

	public Cheat(String content, int pID) {
		this.content = content;
		this.pID = pID;
	}

	/*
	 * This String is to be exchanged by a CheatMessage Object after
	 * integrating. String is used for test purposes.
	 */
	public void handleCheat(String content) {
		if (content.equals("noFogAnymore")) {
			activateFogCheat();
		} else if (content.equals("maxHP")) {
			activateHPCheat();
		} else if (content.equals("allDeadEnemy")) {
			activateEnemyCheat();
		} else if (content.equals("ScottyBeamMeUp")) {
			activateTeleportCheat();
		}
	}

	/*
	 * The following methods have to be adjusted to GUI and Server after
	 * integrating. For test purposes simply put a system.out.println into every
	 * method. After integrating we are creating objects out of the Messages and
	 * send them to the server. the server will be executing the cheat and the
	 * client engine will hand the information to the GUI. 
	 */

	private void activateTeleportCheat() {
		System.out.println("Player teleported");
	}

	private void activateEnemyCheat() {
		System.out.println("All Enemies are dead!!");
	}

	private void activateHPCheat() {
		System.out.println("You are immortal!!");
	}

	private void activateFogCheat() {
		System.out.println("The Fog disappeared!");
	}

}


