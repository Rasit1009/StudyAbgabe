package pp2017.team10.shared;

public class ChatMessage extends Messages {

	/**
	 * @author Güven, Rasit Matnr: 6019617
	 */
	
	public String content;
	public int sender;
	public int recipient;
	
	public ChatMessage(String content, int sender, int recipient){
		this.content = content;
		this.sender = sender;
		this.recipient = recipient;
	}
}
