package pp2017.team10.shared;

import java.util.ArrayList;

public class MonsterOnFieldMessage  extends Messages{

	private static final long serialVersionUID = -2282824687855589788L;
	private ArrayList<MonsterList> monsters;

	public MonsterOnFieldMessage(ArrayList<MonsterList> monsters) {
		this.monsters = monsters;
	}
	
	public ArrayList<MonsterList> getMonsters(){
		return monsters;
	}
}
