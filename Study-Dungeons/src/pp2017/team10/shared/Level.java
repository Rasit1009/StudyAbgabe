package pp2017.team10.shared;

import java.util.ArrayList;

public class Level extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -856955435338211577L;
	private int[][] world;
	private ArrayList<UserLogedIn> u;
	private boolean doorOpen;
	private int levelID;

	public Level(int[][] world, ArrayList<UserLogedIn> u, boolean doorOpen) {
		this.world = world;
		this.u = u;
		this.doorOpen = doorOpen;
	}

	public int[][] getWorld() {
		return world;
	}

	public Level(int[][] world, boolean doorOpen, int levelID) {
		this.world = world;
		this.levelID = levelID;
		this.doorOpen = doorOpen;
	}
	
	public boolean getDoorOpen() {
		return doorOpen;
	}
	
	public int getLevelID() {
		return levelID;
	}
	
}
