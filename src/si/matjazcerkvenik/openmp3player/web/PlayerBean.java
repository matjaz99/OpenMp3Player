package si.matjazcerkvenik.openmp3player.web;

import javax.faces.context.FacesContext;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.PlistMng;
import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.openmp3player.player.jlayer.JLayerPlayer;

public class PlayerBean {
	
	private IPlayer player = null;
	private Mp3File currentlyPlaying = null;
	
	public PlayerBean() {
		player = new JLayerPlayer();
	}
	
	private PlistMng getPlistMng() {
		return (PlistMng) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("plistMng");
	}
	
	/**
	 * Return title of currently playing song.
	 * @return title
	 */
	public String getCurrentlyPlaying() {
		if (currentlyPlaying == null) {
			return "null";
		}
		return currentlyPlaying.getTitle();
	}
	
	public String play() {
		return play(0);
	}
	
	/**
	 * Start playing song with index i
	 * @param i
	 * @return title of the song
	 */
	public String play(int i) {
		
		if (currentlyPlaying != null) {
			stop();
		}
		
		
		
		currentlyPlaying = getPlistMng().getActivePlaylist().getMp3Files().get(i);
		OContext.getInstance().getLogger().info("play: playlist: " + OContext.getInstance().getPlistMng().getActivePlaylist().getName() 
				+ ", MP3: [" + currentlyPlaying.getIndex() + "] " + currentlyPlaying.getFile());
		player.play(currentlyPlaying.getPath());
		
		return currentlyPlaying.getTitle();
		
	}
	
	/**
	 * Stop playing song
	 * @return 'null'
	 */
	public String stop() {
		if (currentlyPlaying == null) {
			return "null";
		}
		
		OContext.getInstance().getLogger().info("stop: playlist: " + getPlistMng().getActivePlaylist().getName() 
				+ ", MP3: [" + currentlyPlaying.getIndex() + "] " + currentlyPlaying.getFile());
		player.stop();
		currentlyPlaying = null;
		
		return "null";
	}
	
	/**
	 * Play next song
	 * @return title
	 */
	public String next() {
		if (currentlyPlaying == null) {
			return "null";
		}
		
		if (currentlyPlaying.getIndex() 
				== getPlistMng().getActivePlaylist().getMp3Files().size() - 1) {
			play(0);
		} else {
			play(currentlyPlaying.getIndex() + 1);
		}
		return currentlyPlaying.getTitle();
	}
	
	/**
	 * Play previous song
	 * @return title
	 */
	public String prev() {
		if (currentlyPlaying == null) {
			return "null";
		}
		
		if (currentlyPlaying.getIndex() == 0) {
			play(getPlistMng().getActivePlaylist().getMp3Files().size() - 1);
		} else {
			play(currentlyPlaying.getIndex() - 1);
		}
		return currentlyPlaying.getTitle();
	}
	
	/**
	 * Return true if song is currently playing
	 * @return true if playing
	 */
	public boolean isPlaying() {
		return currentlyPlaying != null;
	}
	
}
