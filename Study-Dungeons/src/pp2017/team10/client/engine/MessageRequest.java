package pp2017.team10.client.engine;

import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.DoorUsageMessage;
import pp2017.team10.shared.ItemUsageMessage;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.MoveMessage;
import pp2017.team10.shared.PlayerAttackMessage;
import pp2017.team10.shared.StartMessage;
import pp2017.team10.shared.Messages;

/**
 * In this class we are getting the Message Object and assign the Object to the
 * right Method and according to the type of Object we are handling the Message.
 * Because we are only responsible for our own component at this point of
 * moment, the functionality of the handles is not implemented yet, due to lack
 * of knowledge of our GUI (because it is not integrated yet)
 * 
 * @author Güven, Rasit Matnr: 6019617
 *
 */
public class MessageRequest {

	public String content;
	public Messages msg;
	public ClientEngine ce;

	public MessageRequest(Messages msg) {
		receiveRequest(msg);
	}

	public void receiveRequest(Messages msg) {

		if (msg instanceof StartMessage) {
			handleStart(msg);
		} else if (msg instanceof Login) {
			handleLogin(msg);
		} else if (msg instanceof MoveMessage) {
			handleMove(msg);
		} else if (msg instanceof ItemUsageMessage) {
			handleItem(msg);
		} else if (msg instanceof DoorUsageMessage) {
			handleDoor(msg);
		} else if (msg instanceof PlayerAttackMessage) {
			handleAttack(msg);
		} else if (msg instanceof ChatMessage) {
			handleChat(msg);
		}
	}

	/*
	 * this methods have to be adjusted to the GUI. we get the Object from our
	 * GUI and then send them to our Server with our method sendRequest in our
	 * Messages class.
	 * 
	 */

	public void handleStart(Messages msg) {
		System.out.println("This is a Start message");
		ce.addQueue(msg);
	}

	public void handleLogin(Messages msg) {

		System.out.println("This is a Login message");
		ce.addQueue(msg);
	}

	public void handleMove(Messages msg) {

		System.out.println("This is a Move message");
		ce.addQueue(msg);
	}

	public void handleItem(Messages msg) {

		System.out.println("This is a Item message");
		ce.addQueue(msg);
	}

	public void handleDoor(Messages msg) {

		System.out.println("This is a Door message");
		ce.addQueue(msg);

	}

	public void handleAttack(Messages msg) {

		System.out.println("This is a Attack message");
		ce.addQueue(msg);
	}

	public void handleLevelSwitch(Messages msg) {

		System.out.println("This is a Level message");	
		ce.addQueue(msg);
	}

	public void handleChat(Messages msg) {
		
		System.out.println("This is a Chat Message");
		ce.addQueue(msg);
	}

}