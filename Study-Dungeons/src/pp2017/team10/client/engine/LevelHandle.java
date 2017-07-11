package pp2017.team10.client.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import pp2017.team10.shared.Messages;

public class LevelHandle implements Serializable {
	/**
	 * this class is handling our levels. we are getting our level, setting the
	 * next level, sending the information to our communication and receive
	 * information from the communication. not integrated yet.
	 * @author Güven, Rasit Matnr: 6019617
	 */

	private static final long serialVersionUID = 3313882026388609621L;
	public int currentLevel;
	public String levelName;

	public LevelHandle(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		System.out.println("current Level is" + currentLevel);
		this.currentLevel = currentLevel;
	}

	// gets the next level
	public int getNextLevel(int currentLevel) {
		this.currentLevel = currentLevel + 1;
		System.out.println("next Level is" + currentLevel);
		return this.currentLevel;
	}

	// sends levelInformation to the Server.
	public void sendLevelInfo() {
		getCurrentLevel();
		setCurrentLevel(currentLevel);
		LevelHandle level = new LevelHandle(currentLevel);

		try (FileOutputStream lo = new FileOutputStream("levelInfo.ser");
				ObjectOutputStream ol = new ObjectOutputStream(lo)) {
			ol.writeObject(level);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void getLevelInfo() {
		try (FileInputStream li = new FileInputStream("levelInfo.ser");
				ObjectInputStream il = new ObjectInputStream(li)) {
			final LevelHandle level = (LevelHandle) il.readObject();
			assert (level.getName().equals(currentLevel));
		} catch (IOException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public String getName() {

		return levelName;
	}

	public LevelHandle readObject(String filnename) {

		LevelHandle address = null;

		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try {

			fin = new FileInputStream("levelInfo.ser");
			ois = new ObjectInputStream(fin);
			address = (LevelHandle) ois.readObject();

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
