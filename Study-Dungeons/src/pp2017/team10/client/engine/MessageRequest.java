package pp2017.team10.client.engine;

import pp2017.team10.shared.ChatMessage;
import pp2017.team10.shared.Cheat;
import pp2017.team10.shared.DoorUsage;
import pp2017.team10.shared.ItemUsage;
import pp2017.team10.shared.Login;
import pp2017.team10.shared.Move;
import pp2017.team10.shared.PlayerAttack;
import pp2017.team10.shared.Start;
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

	public MessageRequest(Messages msg) {
		receiveRequest(msg);
	}

	public void receiveRequest(Messages msg) {

		if (msg instanceof Start) {
			handleStart(msg);
		} else if (msg instanceof Login) {
			handleLogin(msg);
		} else if (msg instanceof Move) {
			handleMove(msg);
		} else if (msg instanceof ItemUsage) {
			handleItem(msg);
		} else if (msg instanceof DoorUsage) {
			handleDoor(msg);
		} else if (msg instanceof PlayerAttack) {
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
		msg.sendRequest(msg);
	}

	public void handleLogin(Messages msg) {

		System.out.println("This is a Login message");
		msg.sendRequest(msg);
	}

	public void handleMove(Messages msg) {

		System.out.println("This is a Move message");
		msg.sendRequest(msg);
	}

	public void handleItem(Messages msg) {

		System.out.println("This is a Item message");
		msg.sendRequest(msg);
	}

	public void handleDoor(Messages msg) {

		System.out.println("This is a Door message");
		msg.sendRequest(msg);

	}

	public void handleAttack(Messages msg) {

		System.out.println("This is a Attack message");
		msg.sendRequest(msg);
	}

	public void handleLevelSwitch(Messages msg) {

		System.out.println("This is a Level message");
		msg.sendRequest(msg);
	}

	public void handleChat(Messages msg) {
		System.out.println("This is a Chat Message");
		msg.sendRequest(msg);
	}

}