package pp2017.team10.client.comm;

import java.io.IOException;
import pp2017.team10.server.comm.*;
import java.net.Socket;


public class ClientConnection extends Thread {
	// Attribute
	public int ID;
	public Thread ServerThread;
	public Socket clientSocket;

	public ClientConnection(Socket clientSocket, int ID) throws IOException {

		this.ID = ID;
		this.clientSocket = clientSocket;
		ServerThread serverThread = new ServerThread(clientSocket, ID);
		serverThread.start();
		/*
		 * Für jeden Client wird ein ServerThread gestartet.
		 */

	}
}