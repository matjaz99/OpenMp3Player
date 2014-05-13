package si.matjazcerkvenik.openmp3player.player;


public interface IPlayer {
	
	public void	play();
	
	public void	play(String filepath);
	
	public void	stop();
	
	public void	pause();
	
	public PlayerStatus getStatus();
	
}
