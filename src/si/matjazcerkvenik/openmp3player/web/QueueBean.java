package si.matjazcerkvenik.openmp3player.web;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean
@ApplicationScoped
public class QueueBean {
	
	private String newQueueName = null;
	
	@ManagedProperty(value="#{playerBean}")
	private PlayerBean playerBean;
	

	public void setPlayerBean(PlayerBean playerBean) {
		this.playerBean = playerBean;
	}
	
	public String getNewQueueName() {
		return newQueueName;
	}

	public void setNewQueueName(String newQueueName) {
		this.newQueueName = newQueueName;
	}
	
	public String saveQueue() {
		playerBean.getMp3Player().saveQueue(newQueueName);
		playerBean.getMp3Player().emptyQueue();
		playerBean.getMp3Player().setPassivePlaylist(newQueueName);
		return "home";
	}
	
	public void emptyQueue() {
		playerBean.getMp3Player().emptyQueue();
	}
	
	
}
