package pp2017.team10.client.engine;

import java.io.IOException;

import pp2017.team10.client.comm.ClientComm;
import pp2017.team10.client.comm.SendQueue;
import pp2017.team10.client.gui.spielwelt;

public class Ce_main {

	public static spielwelt spiel;
	
	public static void main(String[] args) throws IOException {
		
		ClientEngine ce;
		ClientComm c = new ClientComm("localhost", 1500);
		if (!c.start())
			return;
		
		SendQueue ss = new SendQueue(c, c.getce());
		ss.run();
		ce = c.getSvS().getCE();

		spiel = new spielwelt();
		spiel.show();
	}
	
}
