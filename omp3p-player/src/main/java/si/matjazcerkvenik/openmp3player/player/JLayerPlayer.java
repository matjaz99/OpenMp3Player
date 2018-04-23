package si.matjazcerkvenik.openmp3player.player;

import org.springframework.stereotype.Service;

import si.matjazcerkvenik.openmp3player.jlayer.SoundJLayer;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

@Service
public class JLayerPlayer implements IPlayer, IPlayerCallback {
	
	private SimpleLogger logger = null;
	
	private SoundJLayer player = null;
	private PlayerStatus status = PlayerStatus.STOPPED;
	

	public JLayerPlayer() {
	}


	@Override
	public void play(String filepath) {
		
		player = new SoundJLayer(filepath, this);
		player.play();
		status = PlayerStatus.PLAYING;
		
	}
	
	@Override
	public void pause() {
		// no implementation
	}

	@Override
	public void stop() {
		player.pauseToggle();
		status = PlayerStatus.STOPPED;
	}
	
	@Override
	public void resume() {
		// no implementation
	}
	
	@Override
	public PlayerStatus getStatus() {
		return status;
	}

	@Override
	public void playEnded() {
		status = PlayerStatus.PLAY_ENDED;
//		OContext.getInstance().getLogger().info(status.toString());
	}
	
	
}
