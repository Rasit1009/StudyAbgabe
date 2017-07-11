package test.environment.client.engine;

import pp2017.team10.client.engine.LevelHandle;


/**
 * This class is testing the handling of the Level Information. we are currently
 * just sending an integer to the communication, but after integration we are
 * going to send objects with every information needed to the communication
 * 
 * @author Güven, Rasit Matnr: 6019617
 *
 */

public class TestLevelHandle {

	public static int currentLevel = 1;

	public static void main(String[] args) {
		
		LevelHandle lvl = new LevelHandle(currentLevel);
		lvl.sendLevelInfo();
		lvl.getLevelInfo();

		LevelHandle lvlo = new LevelHandle(currentLevel);
		LevelHandle read = lvlo.readObject("levelInfo");
	
		System.out.println(read);
	}

}
