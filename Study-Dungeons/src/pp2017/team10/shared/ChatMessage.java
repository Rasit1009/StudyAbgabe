package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class ChatMessage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -9207628058769370829L;
	public String content;
	public String user;
	public String recipient;
	public String sender;
	
	public ChatMessage(String content, String sender){
		this.content = content;
		this.user = sender;
	}
	
	public ChatMessage(String content, String reciever, String sender){
		this.content = content;
		this.recipient = reciever;
		this.sender = sender;
	}
}
