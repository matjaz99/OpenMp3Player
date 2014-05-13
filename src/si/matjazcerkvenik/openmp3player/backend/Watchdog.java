package si.matjazcerkvenik.openmp3player.backend;

import si.matjazcerkvenik.openmp3player.player.PlayerStatus;

public class Watchdog extends Thread {
	
	private Mng mng = null;
	
	
	
	public Watchdog(Mng mng) {
		setName("Watchdog");
		this.mng = mng;
	}



	@Override
	public void run() {
		
		while (true) {
			
//			Mng.getLogger().info("Watchdog:status: " + Mng.getPlayer().getStatus());
			
			if (mng == null) {
				return;
			}
			
			if (Mng.getPlayer().getStatus() == PlayerStatus.PLAY_ENDED) {
//				if (Mng.currentlyPlaying != null) {
//					Mng.getLogger().info("Watchdog: autoplay next");
//					if (Mng.currentlyPlaying.getIndex() 
//							== mng.getPlistMng().getActivePlaylist().getMp3Files().size() - 1) {
//						mng.play(0);
//					} else {
//						mng.play(Mng.currentlyPlaying.getIndex() + 1);
//					}
//				}
				mng.next();
				
			}
			
			try {
				sleep(Utils.PLAYER_DELAY * 1000);
			} catch (InterruptedException e) {
				
			}
			
		}
		
	}
	
}
