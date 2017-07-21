package pp2017.team10.shared;

import pp2017.team10.shared.Messages;

public class ChatMessage extends Messages {

	/**
	 * Author: Felix Schifferdecker, 5585147
	 */
	private static final long serialVersionUID = -9207628058769370829L;
	private String content;
	private int currentLevel;

	public ChatMessage(String content, int currentLevel) {
		this.content = content;
		this.currentLevel = currentLevel;
	}

	public String getContent() {
		return content;
	}
	
	public int getCurrentLevel() {
		return currentLevel;
	}
	
}
