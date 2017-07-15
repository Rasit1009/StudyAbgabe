/**
 * @author Overberg Jonathan 5579562
 */
package pp2017.team10.server.comm;

import java.io.*;
import java.net.*;
import java.util.*;
import pp2017.team10.server.*;
import pp2017.team10.shared.*;

public class ServerComm {
	public static int einId;
	private ArrayList<ClientThread> al;
	private ClientThread ct;
	private int port;
	private boolean laeuft;

	// Konstruktor
	public ServerComm(int port) {
		this.port = port;

		al = new ArrayList<ClientThread>();
	}

	/**
	 * @author Overberg Jonathan 5579562 start Methode startet Server mit port
	 */
	public void start() {
		laeuft = true;

		try {
			ServerSocket serverSocket = new ServerSocket(port);

			while (laeuft) {

				System.out.println("Server wartet auf Client mit Port " + port + ".");
				Socket socket = serverSocket.accept();
				if (!laeuft)
					break;

				ClientThread t = new ClientThread(socket, einId, al);
				// uniqueId++;
				al.add(t);
				t.start();

			}
			try {
				serverSocket.close();

				for (int i = 0; i < al.size(); ++i) {
					ClientThread tc = al.get(i);

					try {
						tc.getInput().close();
						tc.getOutput().close();
						tc.getSocket().close();
					} catch (IOException ioE) {
						System.out.println("Fehler s 1");
					}
				}
			} catch (Exception e) {
				System.out.println("Exception schlie�t Server und Clients: " + e);
			}
		} catch (IOException e) {
			System.out.println("Fehler s 2");
		}

	}

}