package pp2017.team10.client.gui;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapLabel extends JFrame {

	private GameField gf;

	public MapLabel() {

		gf = new GameField();
		gf.setBounds(0, 0, 185, 185);
		add(gf);
	}

	public void repaintGamefield() {
		gf.repaint();
	}

}