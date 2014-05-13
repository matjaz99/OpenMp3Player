package si.matjazcerkvenik.openmp3player.backend;

import si.matjazcerkvenik.openmp3player.player.PlayerStatus;

/**
 * Watch when song will play-out and start playing next. 
 * @author matjaz
 *
 */
public class Watchdog extends Thread {
	
	private Mng mng = null;
	
	
	
	public Watchdog(Mng mng) {
		setName("Watchdog");
		this.mng = mng;
	}



	@Override
	public void run() {
		
		while (true) {
						
			if (mng == null) {
				return;
			}
			
			if (Mng.getPlayer().getStatus() == PlayerStatus.PLAY_ENDED) {
				Mng.getLogger().trace("Watchdog:run(): repeat is: " + Mng.repeatSong);
				if (Mng.repeatSong) {
					mng.play(Mng.currentlyPlaying.getIndex());
				} else {
					mng.next();
				}
				
			}
			
			try {
				sleep(Utils.PLAYER_DELAY * 1000);
			} catch (InterruptedException e) {
				
			}
			
		}
		
	}
	
}
