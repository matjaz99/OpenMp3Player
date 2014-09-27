package si.matjazcerkvenik.openmp3player.backend;

import si.matjazcerkvenik.openmp3player.player.PlayerStatus;

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
			
			if (Mng.getPlayer().getStatus() == PlayerStatus.PLAY_ENDED) {
				Mng.getLogger().trace("Watchdog:run(): repeat is: " + Mng.repeatSong);
				if (Mng.repeatSong) {
					ctx.play(Mng.currentlyPlaying.getIndex());
				} else {
					ctx.next();
				}
				
			}
			
			try {
				sleep(Utils.PLAYER_DELAY * 1000);
			} catch (InterruptedException e) {
				
			}
			
		}
		
	}
	
}
