package pp2017.team10.client.gui2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Map {
	//ProbeKommentar2  
	 
	//Attribute
	MapImage mapimage = new MapImage();
	BufferedImage wall = mapimage.wall;
	BufferedImage ground = mapimage.ground;
	BufferedImage stone = mapimage.stone;
	BufferedImage playerArcher = mapimage.playerArcher;
	BufferedImage schwert = mapimage.schwert;
	

	
	public Map(){}
	
	public static void main (String args[]){
		MapFrame mf =new MapFrame();
		
		mf.setSize(800,640);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mf.setLocationRelativeTo(null);
		mf.setVisible(true);
		mf.setResizable(false);
		
		while(true){
		//	if(mf.getUp())mf.player_posy-=32;
			//if(mf.getDown())mf.player_posy+=32;
			//if(mf.getLeft())mf.player_posx-=32;
			//if(mf.getRight())mf.player_posx+=32;
			mf.repaintGamefield();
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		
		
		
	}
	
	public void paintLevel(Graphics g){

		  /*for(int i=0; i<Map2.map.length;i++){
			for(int j=0; j<Map2.map[0].length; j++){
				g.drawImage(ground, i*32, j*32, 32, 32, null);
				if(Map2.map[i][j]==0)
					g.drawImage(ground, i*32, j*32, 32, 32, null);
	       else if(Map2.map[i][j]==1)
	    	   		g.drawImage(ground, i*32, j*32, 32, 32, null);
	       else if(Map2.map[i][j]==2)
	    	   		g.drawImage(ground, i*32, j*32, 32, 32, null);
	       else if(Map2.map[i][j]==5)
	    	   		g.drawImage(playerArcher, i*32, j*32, 32, 32, null);
	       
		
			}
			
		}*/
	
	}
	
}
