/**ereugt eine Client mit Port und Benutzername
 * @author Overberg Jonathan 5579562
 */
package pp2017.team10.client.comm;

import java.net.*;
import java.util.LinkedList;
import java.util.Queue;
import pp2017.team10.server.*;
import pp2017.team10.shared.*;
import pp2017.team10.client.engine.*;
import java.io.*;

public class ClientComm  {
  //Attribute
	private ObjectInputStream eingabe;       
	private ObjectOutputStream ausgabe;     
	private Socket socket;
	private String server;
	private int port;
	private int id;
	private boolean laeuft=false;
	boolean serverlauft=true;
	private Messages nach;
	private Queue<Messages> zwischenSchlange=new LinkedList<Messages>();
 private ClientEngineGUI ce = new ClientEngineGUI();
	public SendeVonServer sVs;
	    //Konstruktor der Client erzeugt
   		public ClientComm(String server, int port) {
	      //Client(String server,int port,Message nach);
   			
   			this.server = server;
	        this.port = port;
	        
	    }

   		/**
   		 * @author Overberg Jonathan 5579562
   		 * startMethode startet ClientSocket
   		 */
   public boolean start() {
	    	try {
	    		//oeffnet socket
	    		socket = new Socket(server, port);
	    		
	    	}
	    	catch(Exception ec) {
	        	System.out.println("Kann nicht mit Server verbunden werden");
	           return false;
	    	}
	    	String nach = "Verbindung akkzeptiert " + socket.getInetAddress() + ";" + socket.getPort();
	    		System.out.println(nach);
	    		try
	    		{
	    			//oeffnet Streams 
	    			eingabe  = new ObjectInputStream(socket.getInputStream());
	    			ausgabe = new ObjectOutputStream(socket.getOutputStream());
	    		}
	    		catch (IOException eIO) {

	    			System.out.println("Fehler beim erzuegen der Streams: " + eIO);
	    			return false;
	    		}

	        //erzeugt Klasse SendeVonServer und startet sie
	    		sVs = new SendeVonServer(eingabe,ce);
	       sVs.start(); 
	       
	       laeuft=true;
	       return true;
	    }

	   
   /**
    * @author Overberg Jonathan 5579562
    *  methode die Objekte vom Typ Message vom Client zum Server sendet
	*/
   void sendeNach(Messages nach) {
	    	try {
	        	//schreibt in Outputstream
	    		ausgabe.writeObject(nach);
	    	}
	    	catch(IOException e) {
	    	setserverlauft(false);
	    		
	    		System.out.println("Fehler: " + e);
	    	
	    	}
	    }

   /**
    * @author Overberg Jonathan 5579562
    * disconnect schliesst Outputstream,Inputstream und Socket
    */
		public void disconnect() {
		  try {
			  //schliesst inputstream
			  if(eingabe != null) eingabe.close();
		  }
		  catch(Exception e) {
			  
		  } 
		  try {
			 //schliesst outputstream
			  if(ausgabe != null) ausgabe.close();
		  	}
		  catch(Exception e) {
			  System.out.println("Fehler");
		  } 
		  try{
			  //schliesst socket
			  if(socket != null){ 
				  
				  socket.close();
				  laeuft=false;
			  }
		  	}
		  catch(Exception e) {
			  System.out.println("Fehler");
		  } 
		}
	    
	    //Setter Getter
		public ClientEngineGUI getce(){
			return ce;
		}
		public SendeVonServer getSvS(){
			return sVs;
		}
		public void setserverlauft(boolean b){
			serverlauft=b;
		}
		public boolean getlaeuft(){
			return laeuft;
		}
		public boolean getserverlauft(){
			return serverlauft;
		}
		
		public void setzwischenSchlange(Queue<Messages>schlange){
		 zwischenSchlange=schlange;
	    }
	    
	    public void addElementzw(Messages nach){
			zwischenSchlange.offer(nach);
		}	
		
	    public void removeElementzw(){
			zwischenSchlange.poll();
		}

		public Queue<Messages> getzwischenSchlange(){
			return zwischenSchlange;
		}
		
		public Messages getElementzw(){
			return zwischenSchlange.poll();
		}
}