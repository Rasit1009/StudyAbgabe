 package pp2017.team10.client.engine;

import pp2017.team10.shared.DoorUsage;
import pp2017.team10.shared.ItemUsage;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.Messages;
import pp2017.team10.shared.Move;
import pp2017.team10.shared.PlayerAttack;
import pp2017.team10.shared.Start;

/**
 * this class is the equivalent to the MessageRequest class. we sent our request
 * to the server, got our response and then handled the action. Here we are also
 * simulating the responses of our server, because we did not integrate our
 * components yet.
 * 
 @author Güven, Rasit Matnr: 6019617
 *
 */

public class MessageResponse {

	public MessageResponse(Messages msg) {
		receiveResponse(msg);
	}

	public void receiveResponse(Messages msg) {

		if (msg instanceof Start) {
			respondStart(msg);
		} else if (msg instanceof Login) {
			respondLogin(msg);
		} else if (msg instanceof Move) {
			respondMove(msg);
		} else if (msg instanceof ItemUsage) {
			respondItem(msg);
		} else if (msg instanceof DoorUsage) {
			respondDoor(msg);
		} else if (msg instanceof PlayerAttack) {
			respondAttack(msg);
		}
	}

	public void respondStart(Messages msg) {

		System.out.println("Server said start is ok");
	}

	public void respondLogin(Messages msg) {

		System.out.println("Server said login is ok");
	}

	public void respondMove(Messages msg) {

		System.out.println("Server said move is ok");
	}

	public void respondItem(Messages msg) {

		System.out.println("Server said item is ok");
	}

	public void respondDoor(Messages msg) {

		System.out.println("Server said door is ok");

	}

	public void respondAttack(Messages msg) {

		System.out.println("Server said attack is ok ");
	}

	public void respondLevelSwitch(Messages msg) {

		System.out.println("Server said level switch is ok");
	}

	public void respondChat(Messages msg) {
		System.out.println("Server said chat is ok");
	}

}
