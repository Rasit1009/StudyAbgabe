package pp2017.team10.client.gui2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import StudyDungeons1.Map2;
import StudyDungeons1.MapImage;


public class Gamefield extends JLabel{
	
	//ProbeKommentar2
	
	public Map2 map = new Map2();
	
	
	//Position des Spielers
	public int player_posx=10;
	public int player_posy=10;
	
	//Breite und Hoehe des Spielfeldes
	private final int WIDTH = 800;
	private final int HEIGHT= 632;
	
	
	 //Objekt der MapImage Klasse wird erstellt
	MapImage mapimage = new MapImage();
	
	//Auf die Bilder, die in der MapImage Klasse sind wird durch das neu erstellte Objekt zugegriffen
	BufferedImage stone = mapimage.stone;
	BufferedImage ground= mapimage.ground;
	BufferedImage playerArcher = mapimage.playerArcher;
	BufferedImage wall = mapimage.wall;
	BufferedImage monster = mapimage.monster;
	BufferedImage monsterCroco = mapimage.monsterCroco;
	BufferedImage helm2 = mapimage.helm2;
	BufferedImage schwert = mapimage.schwert;
	
	
	
	//Weiss nicht ob ich das brauche!!!
	//public BufferedImage image;
	
	
	
	//Konstruktor des Gamefield Klasse
	public Gamefield(){
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.DARK_GRAY);
		setVisible(true);
		map.map[10][10].setGround(5);
		
		
	}
	
	//Der Spieler wird mit seiner jeweiligen Position gezeichnet
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		//Es wird auf das gegebene Array zugegriffen und die jeweiligen Bilder werden gezeichnet
		try{
			
//			for(int i=0; i<map.map.length; i++){
//				for(int j=0; j<map.map[i].length; j++){
//					g.drawImage(ground, i*32, j*32, 32, 32, null);
//					if(pm.probemap[i][j]==0)
//						g.drawImage(ground, i*32, j*32, 32, 32, null);
//		       else if(pm.probemap[i][j]==1)
//		    	   		g.drawImage(wall, i*32, j*32, 32, 32, null);
//		       else if(pm.probemap[i][j]==2)
//		    	   		g.drawImage(stone, i*32, j*32, 32, 32, null);
//		       else if(pm.probemap[i][j]==5)
//		    	    player_posx=i;
//					player_posy=j;
//		    	   		g.drawImage(playerArcher, i*32, j*32, 32, 32, null);
//				}
//		}
			
//			for(int i=0; i<map.map.length; i++){
//				for(int j=0; j<map.map[i].length; j++){
//					g.drawImage(ground, i*32, j*32, 32, 32, null);
//					if(map.map[i][j].getGround()==5)
//						g.drawImage(playerArcher, i*32, j*32, 32, 32, null);
//			   else if(map.map[i][j].getGround()==0)
//						g.drawImage(ground, i*32, j*32, 32, 32, null);
//		       else if(map.map[i][j].getGround()==1)
//		    	   		g.drawImage(wall, i*32, j*32, 32, 32, null);
//		       else if(map.map[i][j].getGround()==2)
//		    	   		g.drawImage(stone, i*32, j*32, 32, 32, null);
//		       else if(map.map[i][j].getGround()==6)
//		    	   		g.drawImage(monster, i*32, j*32, 32, 32, null);
//		       else if(map.map[i][j].getGround()==7)
//		    	   		g.drawImage(monsterCroco, i*32, j*32, 32, 32, null);
int minY=-1, minX=-1, maxY=-1, maxX=-1;
			
			if (player_posx-12>=0){
				if (player_posy-10>=0){
					if (player_posx+12<map.map.length){
						if(player_posy+10<map.map.length){
							//Hier ist X min 12 und Y min 10 sowie X max lenght-12 und Y max length-10
							//Es gibt also keine Gefahr eine ArrayoutofBoundsException zu werfen
							minX=player_posx-12;
							maxX=player_posx+12;
							minY=player_posy-10;
							maxY=player_posy+10;									
				} else {  
					//Hier ist X min 12, Y min 10 und X max length-12, aber Y>length-10
					//Es wird also der untere Rand getroffen
					minX=player_posx-12;
					maxX=player_posx+12;
					minY=map.map.length-20;
					maxY=map.map.length;
						} 
					} else {
						//hier ist X min 12, Y min 10, aber X>lenght-12
						if(player_posy+10<map.map.length){
							//Hier ist X min 12, Y min 10, X>length-12, Y>lenght-10
							//Es wird die untere rechte Ecke getroffen
							minX=map.map.length-25;
							maxX=map.map.length;
							minY=0;
							maxY=20;
						}else{
							//hier ist X min 12, Y min 10, X>length-12, Y max  length-10
							//Es wird also der rechte Rand getroffen
							minX=map.map.length-25;
							maxX=map.map.length;
							minY=player_posy-10;
							maxY=player_posy+10;
						}
						
					}
				} else{
					//Hier ist X min 12, Y<10
					if (player_posx+12<map.map.length){
						//Hier ist X min 12, Y<10, X max length-12 (Y logischerweise auch kleiner length-10)
						//Es wird also der obere Rand getroffen
						minX=player_posx-12;
						maxX=player_posx+12;
						minY=0;
						maxY=20;
					} else{
						//hier ist X min 12, Y<10, X>length-12
						//Es wird die obere rechte Ecke getroffen
						minX=map.map.length-25;
						maxX=map.map.length;
						minY=0;
						maxY=20;
					}					
				}
			} else{
				//Hier ist X<12
				if (player_posy-10>=0){
					if(player_posy+10<map.map.length) {
						//Hier ist X<12, Y min 10 und Y<length-10
						//Es wird der linke Rand getroffen
						minX=0;
						maxX=25;
						minY=player_posy-10;
						maxY=player_posy+10;	
					} else{
						//Hier ist X<12, Y min 10 und Y>length-10
						//es wird die untere linke Ecke getroffen
						minX=0;
						maxX=25;
						minY=map.map.length-20;
						maxY=map.map.length;
					}
				} else{
					//Hier ist X<12 und Y<10
					//Es wird die obere linke Ecke getroffen
					minX=0;
					maxX=25;
					minY=0;
					maxY=20;
					
				}
				
			}
			 
			for(int i=minX; i<maxX; i++){
				for(int j=minY; j<maxY; j++){
					g.drawImage(ground, (i-minX)*32, (j-minY)*32, 32, 32, null);
					if(map.map[i][j].getGround()==5){		
						g.drawImage(playerArcher, (i-minX)*32, (j-minY)*32, 32, 32, null);						
					} else if(map.map[i][j].getGround()==0)
						g.drawImage(ground, (i-minX)*32, (j-minY)*32, 32, 32, null);
					 else if(map.map[i][j].getGround()==2)
		    	   		g.drawImage(wall, (i-minX)*32, (j-minY)*32, 32, 32, null);
					 else if(map.map[i][j].getItemType()==1)
		    	   		g.drawImage(schwert, (i-minX)*32, (j-minY)*32, 32, 32, null);
					 else if(map.map[i][j].getItemType()==2)
						 g.drawImage(helm2, (i-minX)*32, (j-minY)*32, 32, 32, null);
					 else if(map.map[i][j].getGround()==6)
		    	   		g.drawImage(monster, (i-minX)*32, (j-minY)*32, 32, 32, null);
					 else if(map.map[i][j].getGround()==7)
		    	   		g.drawImage(monsterCroco, (i-minX)*32, (j-minY)*32, 32, 32, null);
					
				}
				
			}
			
		}catch(Exception e){}
		//g.drawImage(playerArcher, player_posx, player_posy, null);
		
	}
	
}
