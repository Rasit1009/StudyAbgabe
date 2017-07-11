package pp2017.team10.client.gui2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//ProbeKommentar
public class MapImage {
	
	//Diese Initialisierung wird benoetigt um auf die Bilder im Konstruktor zugreifen zu koennen
	static BufferedImage ground = null;
	static BufferedImage wall = null;
	static BufferedImage stone = null;
	static BufferedImage playerArcher = null;
	static BufferedImage monster = null;
	static BufferedImage monsterCroco = null;
	static BufferedImage helm2 = null;
	static BufferedImage schwert = null;
	
	   
	 
	//Bilder werden das erste mal geladen
	public MapImage(){
		try{
			ground = ImageIO.read(new File("src/Ground.png"));
			wall   = ImageIO.read(new File("src/Wall.png"));
			stone  = ImageIO.read(new File("src/Stone.png"));
			playerArcher = ImageIO.read(new File("src/playerArcher.png"));
			monster = ImageIO.read(new File("src/Monster.png"));
			monsterCroco = ImageIO.read(new File("src/MonsterCroco.png"));
			//helm2 = ImageIO.read(new File("src/helm2.png"));
			schwert = ImageIO.read(new File("src/swordC2.png"));
			
		}catch (IOException e){
			e.printStackTrace();
			//Falls ein Bild erstellt werden soll was nicht existiert wird die folgende Nachricht angezeigt
			System.err.print("Spieler nicht gefunden");
		}
	}

}
