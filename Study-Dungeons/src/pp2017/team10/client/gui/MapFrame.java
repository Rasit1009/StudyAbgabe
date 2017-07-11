package pp2017.team10.client.gui;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import StudyDungeons1.Gamefield;

public class MapFrame extends JFrame implements ActionListener {

	// ProbeKommentar2

	// Position des Spielers
	// int player_posx=300;
	// int player_posy=300;

	// Objekt der Gamefield Klasse wurde erstellt
	private Gamefield gf;

	// Auf diese Boolean Werte wird im Keyhandler spaeter zugegriffen
	private boolean key_up = false;
	private boolean key_down = false;
	private boolean key_left = false;
	private boolean key_right = false;

	// public static JScrollPane scrollen = new JScrollPane();

	// Konstruktor der MapFrame Klasse
	public MapFrame() {

		// Name des Fensters
		super("FantasticFour");
		gf = new Gamefield();
		// Position und Groesse des Spielfeldes wird konfiguriert
		gf.setBounds(0, 0, 800, 640);
		// Spielfeld wird dem Frame ssbergeben
		add(gf);
		// Keylistener wird dem Frame hinzugefssgt
		addKeyListener(new KeyHandler());

	}

	// Die get Methoden sind dazu da, um auf die jeweiligen Tastendruecke
	// zugreifen zu koennen
	public boolean getUp() {
		return key_up;
	}

	public boolean getDown() {
		return key_down;
	}

	public boolean getLeft() {
		return key_left;
	}

	public boolean getRight() {
		return key_right;
	}

	public void repaintGamefield() {
		gf.repaint();
	}

	// Objekt der Map Klasse wird erstellt
	// Map map = new Map();

	// Graphics werden in das JFrame gemalt
	// public void paint(Graphics g){
	// super.paint(g);
	// }

	public void actionPerformed(ActionEvent e) {
	}

	private class KeyHandler implements KeyListener {

		// Tasten und deren Funktionen werden das erste mal konfiguriert
		public void keyPressed(KeyEvent e) {
			// System.out.println(e.getKeyCode());
			if (e.getKeyCode() == KeyEvent.VK_W) {
				gf.map.map[gf.player_posx][gf.player_posy].setGround(0);
				gf.player_posy--;
				gf.map.map[gf.player_posx][gf.player_posy].setGround(5);
				key_up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				gf.map.map[gf.player_posx][gf.player_posy].setGround(0);
				gf.player_posy++;
				gf.map.map[gf.player_posx][gf.player_posy].setGround(5);
				key_down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				gf.map.map[gf.player_posx][gf.player_posy].setGround(0);
				gf.player_posx--;
				gf.map.map[gf.player_posx][gf.player_posy].setGround(5);
				key_left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				gf.map.map[gf.player_posx][gf.player_posy].setGround(0);
				gf.player_posx++;
				gf.map.map[gf.player_posx][gf.player_posy].setGround(5);
				key_right = true;
			}
			repaintGamefield();

		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W)
				key_up = false;
			if (e.getKeyCode() == KeyEvent.VK_S)
				key_down = false;
			if (e.getKeyCode() == KeyEvent.VK_A)
				key_left = false;
			if (e.getKeyCode() == KeyEvent.VK_D)
				key_right = false;
		}

		public void keyTyped(KeyEvent e) {
		}

	}

}
