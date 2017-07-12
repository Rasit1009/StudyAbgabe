package test.environment.client.engine;

import pp2017.team10.client.engine.ClientEngineGUI;
import pp2017.team10.shared.Character;

/**
 * In this test environment we are testing the different methods from our the
 * movement and the consistency check. we are simulating an environment where we
 * have a Map with the Length 5x5. There we are moving by using simple Strings
 * and in the console we are able to see where our character is going and if
 * this move is even possible (if the position we want to go is within the given
 * map)
 * 
 * @author Güven, Rasit Matnr: 6019617
 *
 */

public class TestCE {
	public static int possibleMap[][] = new int[5][5];
	public static String up = "UP";
	public static String down = "DOWN";
	public static String right = "RIGHT";
	public static String left = "LEFT";
	public static int posx = 2;
	public static int posy = 1;

	public static void main(String[] args) {
		ClientEngineGUI neu = new ClientEngineGUI();
		Character c = new Character(possibleMap, posx, posy);
//		neu.setItemOnMap(possibleMap);
		
//		neu.consistency(possibleMap, up);
//		neu.movement(up);

/*		neu.consistency(possibleMap, down);
		neu.movement(down);

		neu.consistency(possibleMap, down);
		neu.movement(down);

		neu.consistency(possibleMap, down);
		neu.movement(down);

		neu.consistency(possibleMap, right);
		neu.movement(right);
*/
	}
}
