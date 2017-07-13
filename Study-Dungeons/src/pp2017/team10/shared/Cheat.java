package pp2017.team10.shared;

/**
 * Author: Felix Schifferdecker, 5585147
 */
public class Cheat extends Messages {

public String content;
	
	private static final long serialVersionUID = -7123423244900792410L;

	public Cheat(String content) {
		this.content = content;
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


