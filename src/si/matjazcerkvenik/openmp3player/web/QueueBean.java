package si.matjazcerkvenik.openmp3player.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import si.matjazcerkvenik.openmp3player.player.Mp3Player;

@ManagedBean
@RequestScoped
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
		Mp3Player.getInstance().emptyQueue();
		Mp3Player.getInstance().setPassivePlaylist(newQueueName);
		return "home";
	}
	
	public void emptyQueue() {
		Mp3Player.getInstance().emptyQueue();
	}
	
	
}
