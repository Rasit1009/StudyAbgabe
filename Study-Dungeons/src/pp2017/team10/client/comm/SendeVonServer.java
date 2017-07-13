/**
 * @author Overberg Jonathan 5579562
 */
package pp2017.team10.client.comm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.Queue;
import pp2017.team10.server.*;
import pp2017.team10.shared.*;
import pp2017.team10.client.engine.*;
public class SendeVonServer extends Thread {
			private ObjectInputStream sEingabe;
			private Messages nach;
			public Queue<Messages> empfangsSchlangeClient=new LinkedList<Messages>();
			private boolean an=true;
			//CE ce = new CE();
			ClientEngineGUI ce;
			
			//Konstruktor
			  public SendeVonServer(ObjectInputStream ois,ClientEngineGUI ce){
				this.sEingabe=ois;
			this.ce=ce;
				//empfangsSchlangeClient.add(new ItemMessage(1,1));
			  }
	 
			  /**
			   * runMethod empfaengt regelmaessig Nachrichtn vom Server
			   */
			  public void run() {
				
				  while(an) {
					 
					  try {
					  		nach=(Messages)sEingabe.readObject();
					  		System.out.println("");
					  		empfangsSchlangeClient.add(nach);
					  		//addElementeSC(nach);
					  		System.out.println(empfangsSchlangeClient.size());
	              
					  	  ce.handleMessage(empfangsSchlangeClient);
					  	  System.out.println("gehandlet");
						  while(!empfangsSchlangeClient.isEmpty()){
						empfangsSchlangeClient.poll();
					}
					  }
	               catch(IOException e) {
	            	   	System.out.println("Server hat die Verbindung getrennt: " + e);
	            	   an=false;
	            	   	break;
	               }
					catch(ClassNotFoundException e2) {
	                	  System.out.println("Fehler");
	                }
				 
			
				  }
			  }

			  
			  //Getter Setter
	       public ClientEngineGUI getCE(){
	    	   return ce;
	       }
			  
			  public void setempfangsSchlangeClient(Queue<Messages>schlange){
		  		 empfangsSchlangeClient=schlange;
		  	 }
		  	
	        public void addElementeSC(Messages nach){
		  			empfangsSchlangeClient.offer(nach);
		  		}	
		  		
	        public void removeElementeSC(){
		  			empfangsSchlangeClient.poll();
		  		}

		  	public Queue<Messages> geteSC(){
		  			return empfangsSchlangeClient;
		  		}
		  		
		  	public Messages getElementeSC(){
		  			return empfangsSchlangeClient.poll();
		  		}
 }
