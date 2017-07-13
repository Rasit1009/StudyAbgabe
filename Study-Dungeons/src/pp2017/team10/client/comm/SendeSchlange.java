/**
* @author Overberg Jonathan 5579562
 */
package pp2017.team10.client.comm;

import java.util.LinkedList;
import java.util.Queue;
import pp2017.team10.server.*;
import pp2017.team10.shared.*;
import pp2017.team10.client.engine.*;
public class SendeSchlange extends Thread{
	private Queue<Messages> sendeSchlange=new LinkedList<Messages>();
	private ClientComm ct;
	private ClientEngineGUI ce;
	//Konstruktor
	public SendeSchlange(ClientComm ct,ClientEngineGUI ce){
		this.ct=ct;
		this.ce=ce;
	}
	
	/**
	 * runMethode sendet regelm��ig Nachrichten zum Server
	 */
	public void run(){
		//sendeSchlange=ct.getSvS().getCE().getSchlange();
		boolean lauf=true;
		while(lauf){
			if(ce.getSchlange().isEmpty()){
				//System.out.println("SendeSchlange leer");
			try{
				sleep(0);
			}
			catch(Exception e){
				
			}
			}
		if(!ce.getSchlange().isEmpty()){
			while(!ce.getSchlange().isEmpty()){
				
				ct.sendeNach(ce.entferneSchlange());
			}
			//lauf=false;
		}
		}
	}
	
	//Getter Setter
	public void setSchlange(Queue<Messages> schlange){
	sendeSchlange=schlange;
	}
	
	public void addElement(Messages msg){
	sendeSchlange.offer(msg);
	}	
	
	public void removeElement(){
	sendeSchlange.poll();
	}

	public Queue<Messages> getSchlange(){
	return sendeSchlange;
	}
	
	public Messages getElement(){
	return sendeSchlange.poll();
	}
}