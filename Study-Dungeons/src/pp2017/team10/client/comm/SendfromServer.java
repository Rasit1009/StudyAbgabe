/**
 * @author Overberg Jonathan 5579562
 */
package pp2017.team10.client.comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Queue;
import pp2017.team10.server.*;
import pp2017.team10.shared.*;
import pp2017.team10.client.engine.*;
import pp2017.team10.client.gui.spielwelt;

public class SendfromServer extends Thread {
	private ObjectInputStream sInput;
	private Messages to;
	public Queue<Messages> receiveQueueClient = new LinkedList<Messages>();
	private boolean on = true;
//	 ClientEngine ce = new ClientEngine();
	ClientEngine ce;
	public spielwelt spiel;


	// Konstruktor
	public SendfromServer(ObjectInputStream ois, ClientEngine ce) {
		this.sInput = ois;
		this.ce = ce;
	}

	/**
	 * runMethod empfaengt regelmaessig Nachrichtn vom Server
	 */
	public void run() {

		while (on) {

			try {
				System.out.println("EINGELESEN?");
				System.out.println(to);
				System.out.println(sInput);
				to = (Messages)sInput.readObject();
				System.out.println(to);
				System.out.println("eingelesen");
				receiveQueueClient.add(to);
//				for (Messages n : receiveQueueClient){
//					System.out.println(n);
//				}
				// addElementeSC(nach);
				System.out.println(receiveQueueClient.size());

				ce.handleMessage(receiveQueueClient);
				System.out.println("handled");
				while (!receiveQueueClient.isEmpty()) {
					receiveQueueClient.poll();
				}
			} catch (IOException e) {
				System.out.println("Server disabled Connection " + e);
				on = false;
				break;
			} catch (ClassNotFoundException e2) {
				System.out.println("Error");
			}

		}
	}

	// Getter Setter
	public ClientEngine getCE() {
		return ce;
	}

	public void setgetQueueClient(Queue<Messages> queue) {
		receiveQueueClient = queue;
	}

	public void addElementsSC(Messages to) {
		receiveQueueClient.offer(to);
	}

	public void removeElementsSC() {
		receiveQueueClient.poll();
	}

	public Queue<Messages> getSC() {
		return receiveQueueClient;
	}

	public Messages getElementSC() {
		return receiveQueueClient.poll();
	}
}
