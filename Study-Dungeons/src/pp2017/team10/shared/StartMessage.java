package pp2017.team10.shared;

public class StartMessage extends Messages {

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	private static final long serialVersionUID = 5064319678323944159L;
	private String user; 
	private int levelID; 
	
	public StartMessage(String user, int levelID){
		user = this.user; 
		levelID = this.levelID; 
	}
	
	public String getUser(){
		return user; 
	}
	
	public int levelID(){
		return levelID; 
	}

}
