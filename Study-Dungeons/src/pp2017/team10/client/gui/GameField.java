package pp2017.team10.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class GameField extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10657275074551353L;

	Map2 map = new Map2();

	private final int WIDTH = 185;
	private final int HEIGHT = 185;

	MapImage mapimage = new MapImage();

	BufferedImage ground = mapimage.ground;
	BufferedImage wall = mapimage.wall;
	BufferedImage player = mapimage.player;

	public GameField() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		setVisible(true);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		try {
			for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[i].length; j++) {
					g.drawImage(ground, i * 7, j * 7, 7, 7, null);
					if (map.map[i][j].getGround() == 2)
						g.drawImage(player, i * 7, j * 7, 7, 7, null);
					else if (map.map[i][j].getGround() == 0)
						g.drawImage(ground, i * 7, j * 7, 7, 7, null);
					else if (map.map[i][j].getGround() == 1)
						g.drawImage(wall, i * 7, j * 7, 7, 7, null);
				}

			}

		} catch (Exception e) {
		}

	}

}