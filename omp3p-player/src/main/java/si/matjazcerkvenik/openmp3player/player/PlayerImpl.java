package si.matjazcerkvenik.openmp3player.player;

import org.springframework.stereotype.Service;

import si.matjazcerkvenik.simplelogger.SimpleLogger;

@Service
public class PlayerImpl {

	private SimpleLogger logger = null;

	private IPlayer player = null;
	
	public String getPlayerRef() {
		return this.toString();
	}

}
