package si.matjazcerkvenik.openmp3player.web;

import si.matjazcerkvenik.openmp3player.player.Mp3Player;

public class QueueBean {
	
	private String newQueueName = null;
	
	public String getNewQueueName() {
		return newQueueName;
	}

	public void setNewQueueName(String newQueueName) {
		this.newQueueName = newQueueName;
	}
	
	public String saveQueue() {
		Mp3Player.getInstance().saveQueue(newQueueName);
		return "home";
	}
	
	public void emptyQueue() {
		Mp3Player.getInstance().emptyQueue();
	}
	
	
}
