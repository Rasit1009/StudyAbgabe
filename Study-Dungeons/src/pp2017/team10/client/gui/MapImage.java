package pp2017.team10.client.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapImage {

	static BufferedImage ground = null;
	static BufferedImage wall = null;
	static BufferedImage player = null;

	public MapImage() {
		try {
			ground = ImageIO.read(new File("src/braun.jpg"));
			wall = ImageIO.read(new File("src/grau.jpg"));
			player = ImageIO.read(new File("src/rot.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.print("Spieler nicht gefunden");
		}
	}

}