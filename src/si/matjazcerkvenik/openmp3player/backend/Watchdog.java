package si.matjazcerkvenik.openmp3player.backend;

import si.matjazcerkvenik.openmp3player.player.PlayerStatus;

public class Watchdog extends Thread {
	
	private Mng mng = null;
	
	
	
	public Watchdog(Mng mng) {
		this.mng = mng;
	}



	@Override
	public void run() {
		
		while (true) {
			
//			System.out.println("Player status: " + mng.getPlayer().getStatus());
			
			if (mng == null) {
				return;
			}
			
			if (Mng.getPlayer().getStatus() == PlayerStatus.PLAY_ENDED) {
				mng.next();
			}
			
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
