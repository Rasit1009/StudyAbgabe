package pp2017.team10.client.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class MiniMap {
	MapImage mapimage = new MapImage();
	BufferedImage wall = mapimage.wall;
	BufferedImage ground = mapimage.ground;
	BufferedImage player = mapimage.player;

	public MiniMap() {
	}

	public static void main(String args[]) {
		MapLabel mf = new MapLabel();

		mf.setSize(181, 169);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mf.setUndecorated(true);
		mf.setLocationRelativeTo(null);
		mf.setVisible(true);
		mf.setResizable(false);

		while (true) {
			mf.repaintGamefield();

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void paintLevel(Graphics g) {

	}

}
