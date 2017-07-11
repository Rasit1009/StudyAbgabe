package pp2017.team10.client.comm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * @author Selcuk Oezer 
 * Client-Thread Klasse um Nachrichten mit dem Server auszutauschen.
 */
public class ClientThread extends Thread {
	// Attribute
	int nummer;
	public Socket clientSocket;
	public boolean clientisconnected;
	private String ende = "logout";

	public ClientThread(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		// this.nummer=nummer;
		// System.out.println("tet1");

		System.out.println("Client Thread wurde gestartet");
		DataInputStream dataIn = new DataInputStream(clientSocket.getInputStream());
		DataOutputStream dataOut = new DataOutputStream(clientSocket.getOutputStream());

		String antwort = dataIn.readUTF();

		System.out.println(antwort);
		/*
		 * Ein Data-Stream zum Server wird erzeugt ueber diesen werden die
		 * Leveldaten empfangen.
		 */

	}

	public void run() {

		try {

			/*
			 * Buffered-Reader-Stream zum Server ueber den man Nachrichten
			 * austauschen kann
			 */

			BufferedReader bufferedReaderClient = new BufferedReader(
					new InputStreamReader(clientSocket.getInputStream()));

			PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader ClientToServer = new java.io.BufferedReader(new InputStreamReader(System.in));

			while (true) {

				String readerInput = ClientToServer.readLine();
				printWriter.println(readerInput);

				if (readerInput.equals(ende)) {
					clientSocket.close();
					/*
					 * Eingabe wird auf logout ueberprueft, falls sich der
					 * Client abmelden will.
					 */
					System.out.println("erfolgreich ausgeloggt");
					clientisconnected = false;
				} else {

					System.out.println(bufferedReaderClient.readLine());

				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		catch (IOException e) {

			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}
}
