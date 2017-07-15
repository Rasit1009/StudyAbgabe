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

public class SendfromServer extends Thread {
	private ObjectInputStream sEingabe;
	private Messages nach;
	public Queue<Messages> receiveQueueClient = new LinkedList<Messages>();
	private boolean an = true;
	// CE ce = new CE();
	ClientEngine ce;

	// Konstruktor
	public SendfromServer(ObjectInputStream ois, ClientEngine ce) {
		this.sEingabe = ois;
		this.ce = ce;
		// empfangsSchlangeClient.add(new ItemMessage(1,1));
	}

	/**
	 * runMethod empfaengt regelmaessig Nachrichtn vom Server
	 */
	public void run() {

		while (an) {

			try {
				nach = (Messages) sEingabe.readObject();
				System.out.println("");
				receiveQueueClient.add(nach);
				// addElementeSC(nach);
				System.out.println(receiveQueueClient.size());

				ce.handleMessage(receiveQueueClient);
				System.out.println("gehandlet");
				while (!receiveQueueClient.isEmpty()) {
					receiveQueueClient.poll();
				}
			} catch (IOException e) {
				System.out.println("Server hat die Verbindung getrennt: " + e);
				an = false;
				break;
			} catch (ClassNotFoundException e2) {
				System.out.println("Fehler");
			}

		}
	}

	// Getter Setter
	public ClientEngine getCE() {
		return ce;
	}

	public void setempfangsSchlangeClient(Queue<Messages> schlange) {
		receiveQueueClient = schlange;
	}

	public void addElementsSC(Messages nach) {
		receiveQueueClient.offer(nach);
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
