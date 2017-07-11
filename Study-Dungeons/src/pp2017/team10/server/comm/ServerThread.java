package pp2017.team10.server.comm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * @author Selcuk Oezer 
 * Server-Thread Klasse um Nachrichten mit dem Client auszutauschen.
 */
public class ServerThread extends Thread {
	// Attribute
	int nummer;
	public Socket socket;
	public boolean clientisconnected = true;
	public String levelDaten = "Level Daten vom Server empfangen";

	public ServerThread(Socket socket, int nummer) throws IOException {
		this.socket = socket;
		this.nummer = nummer;
		System.out.println("Server Thread zum Client mit der nummer " + nummer + " wurde gestartet");

		/*
		 * Ein Data-Stream zum Client wird erzeugt ueber diesen werden die
		 * Leveldaten gesendet.
		 */
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();
		DataInputStream dataIn = new DataInputStream(in);
		DataOutputStream dataOut = new DataOutputStream(out);

		dataOut.writeUTF(levelDaten);

	}

	public void run() {
		while (clientisconnected) {
			/*
			 * Durch den boolean clientisconnected wird ueberprueft ob der
			 * client noch mit dem Server verbunden ist.
			 */

			try {
				/*
				 * Buffered-Reader-Stream zum Client ueber den Nachrichten
				 * ausgetauscht werden.
				 */
				String message = null;
				PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				while ((message = bufferedReader.readLine()) != null) {

					System.out.println("Nachricht von Client mit der nummer" + nummer + ":" + message);
					printWriter.println("server echo : " + message);
				}
				socket.close();

			} catch (IOException e) {
				System.out.println("Client mit der ID " + nummer + " hat die Verbindung zum Server abbgebrochen");
				/*
				 * Der server erkennt das der Client nicht verfuegbar ist und
				 * gibt dies in der Konsole aus.
				 */
				// TODO Auto-generated catch block

			}
			clientisconnected = false;
			// e.printStackTrace();
		}

	}

}
