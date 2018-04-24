package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.jlayer.SoundJLayer;
import si.matjazcerkvenik.openmp3player.model.Mp3File;
import si.matjazcerkvenik.openmp3player.model.Playlist;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlayerImpl implements IPlayer, IPlayerCallback {
	
	private SimpleLogger logger = null;
	
	private SoundJLayer player = null;
	private PlayerStatus status = PlayerStatus.STOPPED;
	

	public PlayerImpl() {
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
