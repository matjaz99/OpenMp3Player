package si.matjazcerkvenik.openmp3player.player.jlayer;

import si.matjazcerkvenik.openmp3player.backend.Mng;
import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.openmp3player.player.IPlayerCallback;
import si.matjazcerkvenik.openmp3player.player.PlayerStatus;

public class JLayerPlayer implements IPlayer, IPlayerCallback {
	
	private SoundJLayer player = null;
	private Mng mng = null;
	private PlayerStatus status = PlayerStatus.STOPPED;
	

	public JLayerPlayer(Mng mng) {
		this.mng = mng;
	}
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play(Mp3File file) {
		
		
		player = new SoundJLayer(file.getFile().getAbsolutePath(), this);
		System.out.println("play: absolutePath: " + file.getFile().getAbsolutePath());
		player.play();
		status = PlayerStatus.PLAYING;
		
	}
	
	@Override
	public void pause() {
		player.pauseToggle();
		status = PlayerStatus.STOPPED;
	}

	@Override
	public void stop() {
		player.pauseToggle();
		status = PlayerStatus.STOPPED;
	}
	
	
	@Override
	public PlayerStatus getStatus() {
		return status;
	}

	@Override
	public void playEnded() {
//		mng.next();
		status = PlayerStatus.PLAY_ENDED;
	}
	
	
}
