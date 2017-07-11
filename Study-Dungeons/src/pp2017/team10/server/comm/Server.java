package pp2017.team10.server.comm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import pp2017.team10.client.comm.ClientConnection;

/* 
 * @author Selcuk Oezer
 * In dieser Klasse wird der Server erstellt,
 * mit dem sich die Clients verbinden können.
 * */
public class Server extends Thread {
	// Attribute
	public int nummer = 1;
	private ServerSocket testSocket;

	// TODO Auto-generated method stub
	public Server() {
		try {
			/*
			 * hier wird der ServerSocket eingerichtet mit dem Port (5777) ueber
			 * den er erreichbar ist
			 */
			testSocket = new ServerSocket(5777);

			System.out.println("Server gestartet!");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Server konnte nicht gestartet werden" + e);

		}

		while (true) {
			/*
			 * While-schleife dient dazu, den Server für mehrere Clients
			 * zugänglich zu machen, d.h. der Server wartet immer wieder auf
			 * neue Verbindungen.
			 */

			Socket clientSocket;
			try {
				clientSocket = testSocket.accept();
				System.out.println("Es hat sich der Client mit der Nummer: " + nummer + " angemeldet");

				ClientConnection newClient = new ClientConnection(clientSocket, nummer);
				newClient.start();
				/*
				 * Sobald sich ein neuer Client mit dem Server verbindet erhaelt
				 * er seine eigene ID-nummer.
				 */

			} catch (IOException e) {
				System.out.println("Verbindung fehlgeschlagen");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			nummer++;
		}

	}

}
