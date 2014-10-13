package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Utils;

/**
 * Watch when song will play-out and start playing next. 
 * @author matjaz
 *
 */
public class Watchdog extends Thread {
	
	private OContext ctx = null;
	
	
	
	public Watchdog(OContext ctx) {
		setName("Watchdog");
		this.ctx = ctx;
	}



	@Override
	public void run() {
		
		while (true) {
						
			if (ctx == null) {
				return;
			}
			
			if (Mp3Player.getInstance().getPlayerStatus() == PlayerStatus.PLAY_ENDED) {
				OContext.getInstance().getLogger().trace("Watchdog:run(): repeat is: " + Mp3Player.getInstance().isRepeatOn());
				if (Mp3Player.getInstance().isRepeatOn()) {
					int i = Mp3Player.getInstance().getCurrentlyPlaying().getIndex();
					Mp3Player.getInstance().play(i);
				} else {
					Mp3Player.getInstance().next();
				}
				
			}
			
			try {
				sleep(Utils.PLAYER_DELAY * 1000);
			} catch (InterruptedException e) {
				
			}
			
		}
		
	}
	
}
