package pp2017.team10.client.comm;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import pp2017.team10.client.comm.*;

/* 
 * @author Selcuk Oezer
 * In dieser Klasse werden die einzelnen Clients mit dem Server verbunden.
 * */
public class Client extends Thread {
	// Attribute
	public Socket clientSocket;
	public Thread ClientThread;

	public Client() {
		System.out.println("Clientverbindung wird hergestellt");

		try {
			/*
			 * Socket fuer den Client wird erstellt
			 */
			clientSocket = new Socket("localhost", 5777);
			System.out.println("Verbindung zum Server wurde hergestellt");
			/*
			 * Client-Thread wird gestartet
			 */
			ClientThread sendClient = new ClientThread(clientSocket, "Rasit");
			sendClient.start();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
