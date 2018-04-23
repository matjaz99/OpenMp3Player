package si.matjazcerkvenik.openmp3player.jlayer;

import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.openmp3player.player.IPlayerCallback;
import si.matjazcerkvenik.openmp3player.player.PlayerStatus;

public class JLayerPlayer implements IPlayer, IPlayerCallback {
	
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
