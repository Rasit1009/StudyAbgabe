/**
 * @author Overberg Jonathan 5579562
 */
package pp2017.team10.server.comm;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import pp2017.team10.shared.*;
import pp2017.team10.server.engine.*;

public class ClientThread extends Thread {
	private Socket socket;
	private ObjectInputStream sInput;
	private ObjectOutputStream sOutput;
	private int id;
	private String username;
	private String pw;
	private Messages to;
	private ArrayList<ClientThread> al;
	GameServer handler = new GameServer();
	private Queue<Messages> getQueueServer = new LinkedList<Messages>();
	private Queue<Messages> sendQueueServer = new LinkedList<Messages>();

	// Konstruktor
	ClientThread(Socket socket, int eindId, ArrayList<ClientThread> al) throws IOException {
		// sendeSchlangeServer=schlange;
		// handler.setTestSchlange(new ItemMessage(1,1));

		id = ++eindId;
		this.al = al;
		this.socket = socket;
		try {
			sOutput = new ObjectOutputStream(socket.getOutputStream());
			sInput = new ObjectInputStream(socket.getInputStream());
			// benutzerName = (String) sEingabe.readObject();
			// pw=(String) sEingabe.readObject();
			// System.out.println(benutzerName + " hat sich mit Server verbunden
			// mit id"+id);
			// System.out.println(pw);
		} catch (IOException e) {
			System.out.println("Fehler ct 1");
			return;
		}
	}

	/**
	 * @author Overberg Jonathan 5579562 runMethode laeuft fuer client und ruf
	 *         spielweltverwaltung auf
	 */
	public void run() {
		boolean lauft = true;
		while (lauft) {

			try {
				to = (Messages) sInput.readObject();
				getQueueServer.add(to);
				// System.out.println("EmpfangsschlangeServer wird
				// bef�llt"+nach);
			} catch (IOException e) {
				System.out.println("Fehler ct 3");
				break;
			} catch (ClassNotFoundException e2) {
				break;
			}
			// System.out.println(nach);
			handler.handleMessage(getQueueServer);

			int i = 1;
			for (Messages m : getQueueServer) {

				// System.out.println(m.getClientID()+" Nachricht"+i);
				i++;
			}

			// System.out.println("im Client"+handler.getTestSchlange().peek());
			if (handler.getMessageQueue().isEmpty()) {

			}
			if (!handler.getMessageQueue().isEmpty()) {
				// System.out.println("r�ckschlange voll
				// SENDE"+handler.getTestSchlange().element());
				while (!handler.getMessageQueue().isEmpty()) {
					sendeNachSer(handler.getMessageQueue().poll());
				}
				// lauf=false;
			}

		}
		entferne(id);
		close();

	}

	/**
	 * @author Overberg Jonathan 5579562 entfernt client aus clientschlange
	 */
	synchronized void entferne(int id) {
		for (int i = 0; i < al.size(); ++i) {
			ClientThread ct = al.get(i);
			if (ct.id == id) {
				al.remove(i);
				return;
			}
		}
	}

	/**
	 * @author Overberg Jonathan 5579562 Methode die Nachrichten vom Typ
	 *         Nachrichten verschickt
	 */
	public boolean sendeNachSer(Messages neueNachricht) {
		if (!socket.isConnected()) {
			close();
			return false;
		}
		// schreibt die Nachricht in den Stream
		try {
			sOutput.writeObject(neueNachricht);
		} catch (IOException e) {
			System.out.println("Fehler beim Verschicken der Nachricht " + username);
		}
		return true;
	}

	/**
	 * @author Overberg Jonathan 5579562 closeMethode die Outputstream,Socket
	 *         und Inpustream schliesst
	 */
	private void close() {
		try {
			if (sOutput != null)
				sOutput.close();
		} catch (Exception e) {
			System.out.println("Fehler ct 4");
		}
		try {
			if (sInput != null)
				sInput.close();
		} catch (Exception e) {
			System.out.println("Fehler ct 5");
		}
		try {
			if (socket != null)
				socket.close();
		} catch (Exception e) {
			System.out.println("Fehler ct 6");
		}
	}

	// Setter Getter
	public ObjectInputStream getInput() {
		return sInput;
	}

	public ObjectOutputStream getOutput() {
		return sOutput;
	}

	public Socket getSocket() {
		return socket;
	}

	public int getID() {
		return id;
	}

	public String getpw() {
		return pw;
	}

	public String getusername() {
		return username;
	}

	public void setgetQueueServer(Queue<Messages> schlange) {
		getQueueServer = schlange;
	}

	public void addElementSS(Messages msg) {
		getQueueServer.offer(msg);
	}

	public void removeElementSS() {
		getQueueServer.poll();
	}

	public Queue<Messages> getSS() {
		return getQueueServer;
	}

	public Messages getElementSS() {
		return getQueueServer.poll();
	}

	public void setsendQueueServer(Queue<Messages> schlange) {
		sendQueueServer = schlange;
	}

	public void addElementsSS(Messages msg) {
		sendQueueServer.offer(msg);
	}

	public void removeElementsSS() {
		sendQueueServer.poll();
	}

	public Queue<Messages> getsSS() {
		return sendQueueServer;
	}

	public Messages getElementsSS() {
		return sendQueueServer.poll();
	}
}