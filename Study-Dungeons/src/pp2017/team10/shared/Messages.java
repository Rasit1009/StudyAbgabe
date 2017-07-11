package pp2017.team10.shared;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pp2017.team10.client.engine.ClientEngineGUI;

public class Messages implements Serializable {
	public String filename;
	public Messages msg;
	public ClientEngineGUI ceg;

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	private static final long serialVersionUID = 1171553980546243488L;

	/*
	 * This method is responsible for sending Requests to the Server. we are
	 * getting our Object from the MessageHandle class and write them in an
	 * object to pass them to the communication. Our communication just has to
	 * create a socket and get the Object by using getInputStream.
	 */

	public void sendRequest(Messages msg) {
		this.msg = msg;
		try (FileOutputStream lo = new FileOutputStream("actionInfo.ser");
				ObjectOutputStream ol = new ObjectOutputStream(lo)) {
			ol.writeObject(msg);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		receiveResponse(msg);
	}

	/*
	 * In this method we are getting the response from our server to check if
	 * our message is permitted.
	 */
	public void receiveResponse(Messages msg) {
		try (FileInputStream li = new FileInputStream("actionInfo.ser");
				ObjectInputStream il = new ObjectInputStream(li)) {
			final Messages input = (Messages) il.readObject();
			assert (input.getName().equals(msg));
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public Messages getName() {
		return msg;
	}

	/*
	 * source:
	 * https://www.mkyong.com/java/how-to-read-an-object-from-file-in-java/ this
	 * method is only used for test purposes. it tests if the file is written
	 * correctly into an Object. In the console we can see that it addresses the
	 * right package, so I assume that our method sendRequest is working
	 * correctly.
	 * 
	 */
	public Messages readObject(String filnename) {

		Messages address = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {

			fin = new FileInputStream("actionInfo.ser");
			ois = new ObjectInputStream(fin);
			address = (Messages) ois.readObject();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

			if (fin != null) {
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return address;

	}

}
