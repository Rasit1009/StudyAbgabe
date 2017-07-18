package pp2017.team10.shared;

import java.util.ArrayList;

import pp2017.team10.server.engine.UserLogedIn;

public class Level extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -856955435338211577L;
	private int[][] world = new int[50][50];
	private int levelID;
	private ArrayList<UserLogedIn> u;

	public Level(int[][] world, ArrayList<UserLogedIn> u) {
		this.world = world;
		this.u = u;
	}

	public Level(int[][] world, int levelID) {
		this.world = world;
		this.levelID = levelID;
	}

	public int[][] getWorld() {
		return world;
	}
	
	public int getLevelID(){
		return levelID;
	}
}
