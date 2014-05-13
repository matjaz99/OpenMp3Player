package si.matjazcerkvenik.openmp3player.player.jlayer2;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.openmp3player.player.PlayerStatus;
import si.matjazcerkvenik.openmp3player.player.jlayer.Pausable;

public class JLayerPlayer2 extends Pausable.PlaybackListener implements IPlayer {

	private Mp3File mp3File = null;
	private Thread playerThread = null;
	
	public JLayerPlayer2(Mp3File mp3File) {
		this.mp3File = mp3File;
	}

	@Override
	public void play() {
		
		if (playerThread == null) {
			
		}
		
	}
	
	@Override
	public void play(Mp3File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public PlayerStatus getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public void playEnded() {
//		// TODO Auto-generated method stub
//		
//	}

	
	
	
	
}
