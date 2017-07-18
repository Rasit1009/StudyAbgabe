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

		ClientEngine ce = new ClientEngine();

		ClientComm c = new ClientComm("localhost", 1500);
		spiel = new spielwelt();
		ce = spiel.returnEngine(ce);
		if (!c.start())
			return;
		System.out.println(ce.bSendQueue.isEmpty());
		SendQueue ss = new SendQueue(c, ce);
		System.out.println(ce.bSendQueue.isEmpty());
		ss.start();
		System.out.println(ce);
		System.out.println(ce);
		ce = c.getSvS().getCE();
		System.out.println(ce);

		// bSendQueue.add(m);

		spiel.setVisible(true);

	}

}
