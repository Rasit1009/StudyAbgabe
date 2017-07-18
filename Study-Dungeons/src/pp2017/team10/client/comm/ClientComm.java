/**ereugt eine Client mit Port und Benutzername
 * @author Overberg Jonathan 5579562
 */
package pp2017.team10.client.comm;

import java.net.*;
import java.util.LinkedList;
import java.util.Queue;
import pp2017.team10.server.*;
import pp2017.team10.shared.*;
import pp2017.team10.client.engine.*;
import pp2017.team10.client.gui.spielwelt;

import java.io.*;

public class ClientComm{
	// Attribute
	private ObjectInputStream Input;
	private ObjectOutputStream Output;
	private Socket socket;
	private String server;
	private int port;
	private int id;
	private boolean runs = false;
	boolean serverruns = true;
	private Messages to;
	private Queue<Messages> bSendQueue = new LinkedList<Messages>();
	private ClientEngine ce = new ClientEngine();
	public SendfromServer sFs;

	// Konstruktor der Client erzeugt
	public ClientComm(String server, int port) {
		// Client(String server,int port,Message nach);

		this.server = server;
		this.port = port;
	}

	/**
	 * @author Overberg Jonathan 5579562 startMethode startet ClientSocket
	 */
	public boolean start() {
		try {
			// oeffnet socket
			socket = new Socket(server, port);

		} catch (Exception ec) {
			System.out.println("Cannot connect to Server");
			return false;
		}
		String nach = "Connection accepted " + socket.getInetAddress() + ";" + socket.getPort();
		System.out.println(nach);
		try {
			// oeffnet Streams
			Input = new ObjectInputStream(socket.getInputStream());
			Output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException eIO) {

			System.out.println("Error creating Stream " + eIO);
			return false;
		}

		// erzeugt Klasse SendeVonServer und startet sie
		sFs = new SendfromServer(Input, ce);
		sFs.start();

		runs = true;
		return true;
	}

	/**
	 * @author Overberg Jonathan 5579562 methode die Objekte vom Typ Message vom
	 *         Client zum Server sendet
	 */
	void sendTo(Messages to) {
		try {
			// schreibt in Outputstream
			Output.writeObject(to);
			System.out.println("verschickt");
		} catch (IOException e) {
			setserverruns(false);

			System.out.println("Fehler: " + e);

		}
	}

	/**
	 * @author Overberg Jonathan 5579562 disconnect schliesst
	 *         Outputstream,Inputstream und Socket
	 */
	public void disconnect() {
		try {
			// schliesst inputstream
			if (Input != null)
				Input.close();
		} catch (Exception e) {

		}
		try {
			// schliesst outputstream
			if (Output != null)
				Output.close();
		} catch (Exception e) {
			System.out.println("Fehler");
		}
		try {
			// schliesst socket
			if (socket != null) {

				socket.close();
				runs = false;
			}
		} catch (Exception e) {
			System.out.println("Fehler");
		}
	}

	// Setter Getter
	public ClientEngine getce() {
		return ce;
	}

	public SendfromServer getSvS() {
		return sFs;
	}

	public void setserverruns(boolean b) {
		serverruns = b;
	}

	public boolean getruns() {
		return runs;
	}

	public boolean getserverruns() {
		return serverruns;
	}

	public void setbQueue(Queue<Messages> schlange) {
		bSendQueue = schlange;
	}

	public void addElementzw(Messages nach) {
		bSendQueue.offer(nach);
	}

	public void removeElementzw() {
		bSendQueue.poll();
	}

	public Queue<Messages> getbQueue() {
		return bSendQueue;
	}

	public Messages getElementzw() {
		return bSendQueue.poll();
	}
}