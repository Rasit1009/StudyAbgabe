package pp2017.team10.client.engine;

import java.io.IOException;

import pp2017.team10.client.comm.ClientComm;
import pp2017.team10.client.comm.SendQueue;
import pp2017.team10.client.gui.spielwelt;
import pp2017.team10.client.engine.ClientEngine;
import pp2017.team10.shared.Messages;

public class CE_Main {
	public static spielwelt spiel;

	public static void main(String[] args) throws IOException {

		ClientEngine ce;

		ClientComm c = new ClientComm("localhost", 1500);
		if (!c.start())
			return;
		SendQueue ss = new SendQueue(c, c.getce());
		ss.start();
		ce=c.getSvS().getCE();
		System.out.println(ce);
		
		// bSendQueue.add(m);

		spiel = new spielwelt();
		spiel.show();
		System.out.println(ce.bSendQueue.isEmpty());

	}

}
