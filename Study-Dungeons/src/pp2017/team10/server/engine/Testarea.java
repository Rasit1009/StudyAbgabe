package pp2017.team10.server.engine;

import java.io.File;
import java.io.IOException;

public class Testarea {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Database db = new Database();
		db.addUser("halle", "vespaPX90");
		for(User u : db.userdata){
			System.out.println(u.user);
			System.out.println(u.password);
		}
		
	}

}
