/**
* @author Overberg Jonathan 5579562
 */
package pp2017.team10.client.comm;

import java.util.LinkedList;
import java.util.Queue;
import pp2017.team10.server.*;
import pp2017.team10.shared.*;
import pp2017.team10.client.engine.*;

public class SendQueue extends Thread {
	public Queue<Messages> sendQueue = new LinkedList<Messages>();
	private ClientComm ct;
	private ClientEngine ce;

	// Konstruktor
	public SendQueue(ClientComm ct, ClientEngine ce) {
		this.ct = ct;
		this.ce = ce;
	}

	/**
	 * runMethode sendet regelm��ig Nachrichten zum Server
	 */
	public void run() {
		boolean lauf = true;
		sendQueue = ce.bSendQueue;
		System.out.println(sendQueue);
		while (lauf) {
			if (sendQueue.isEmpty()) {
				// System.out.println("SendeSchlange leer");
				try {
					sleep(0);
				} catch (Exception e) {

				}
			}
			if (!sendQueue.isEmpty()) {
				System.out.println("wurde gefüllt");
				while (!sendQueue.isEmpty()) {
					ct.sendTo(ce.deleteQueue());
				}
				// lauf=false;
			}
		}
	}

	// Getter Setter
	public void setQueue(Queue<Messages> schlange) {
		sendQueue = schlange;
	}

	public void addElement(Messages msg) {
		sendQueue.offer(msg);
	}

	public void removeElement() {
		sendQueue.poll();
	}

	public Queue<Messages> getQueue() {
		return sendQueue;
	}

	public Messages getElement() {
		return sendQueue.poll();
	}
}