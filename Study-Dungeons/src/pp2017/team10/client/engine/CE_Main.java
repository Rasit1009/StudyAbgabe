
package pp2017.team10.client.engine;

import java.io.IOException;
import java.io.IOException;

import pp2017.team10.client.comm.ClientComm;
import pp2017.team10.client.comm.SendQueue;
import pp2017.team10.client.gui.MainMenuB;
import pp2017.team10.client.gui.spielwelt;
import pp2017.team10.client.engine.ClientEngine;
import pp2017.team10.shared.Messages;

public class CE_Main {

	public static void main(String[] args) throws IOException {
		// spielwelt spiel;
		ClientEngine ce;
		ClientComm c = new ClientComm("localhost", 1500);
		if (!c.start())
			return;
		System.out.println(ClientEngine.getEngine().bSendQueue.isEmpty());

		SendQueue ss = new SendQueue(c, ClientEngine.getEngine());
		System.out.println(ClientEngine.getEngine().bSendQueue.isEmpty());
		ss.start();
		System.out.println(ClientEngine.getEngine());
		System.out.println(ClientEngine.getEngine());
		ce = c.getSvS().getCE();
		System.out.println(ce);

		spielwelt.getSpielwelt();

		// spielwelt.getSpielwelt().setVisible(true);
		// bSendQueue.add(m);

	}

}
