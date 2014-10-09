package si.matjazcerkvenik.openmp3player.player;


public interface IPlayer {
	
	public void	play(String filepath);
	
	public void	stop();
	
	public void	pause();
	
	public void resume();
	
	public PlayerStatus getStatus();
	
}
