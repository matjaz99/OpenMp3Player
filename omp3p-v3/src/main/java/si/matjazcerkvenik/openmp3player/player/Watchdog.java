package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Utils;

/**
 * Watch when song will play-out and start playing next. 
 * @author matjaz
 *
 */
public class Watchdog extends Thread {
	
	private boolean running = true;
	
	private Mp3Player mp3Player;
	
	
	public Watchdog(Mp3Player mp3Player) {
		setName("Watchdog");
		this.mp3Player = mp3Player;
	}



	@Override
	public void run() {
		
		while (running) {
			
			if (mp3Player.getPlayerStatus() == PlayerStatus.PLAY_ENDED) {
				OContext.getInstance().getLogger().trace("Watchdog:run(): repeat is: " + mp3Player.isRepeatOn());
				mp3Player.updateCurrentlyPlaying();
				if (mp3Player.isRepeatOn()) {
					int i = mp3Player.getCurrentlyPlaying().getIndex();
					mp3Player.play(i);
				} else {
					mp3Player.next();
				}
				
			}
			
			try {
				sleep(Utils.PLAYER_DELAY * 1000);
			} catch (InterruptedException e) {
				running = false;
			}
			
		}
		
	}
	
	public void stopWatchdog() {
		running = false;
	}
	
}
