package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;

public interface IPlayer {
	
	public void	play();
	
	public void	play(Mp3File file);
	
	public void	stop();
	
	public void	pause();
	
	public PlayerStatus getStatus();
	
}
