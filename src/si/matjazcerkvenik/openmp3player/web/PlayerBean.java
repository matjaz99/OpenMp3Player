package si.matjazcerkvenik.openmp3player.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.PlaylistFactory;
import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.openmp3player.player.Playlists;
import si.matjazcerkvenik.openmp3player.player.SoundControl;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

/**
 * This class handles all user interactions with player, including play/stop/next/prev buttons,
 * currently selected playlist, volume up/down buttons, repeat button...
 * 
 * @author matjaz
 *
 */
@ManagedBean
@RequestScoped
public class PlayerBean {
	
	private String selectedPlaylist = null;
	
	
	private SimpleLogger logger = null;
	
	public PlayerBean() {
		logger = OContext.getInstance().getLogger();
	}
	
	
	
	/**
	 * Get playlists for dropdown menu
	 * @return list
	 */
	public List<SelectItem> getPlaylists() {
		
		Playlists playlists = PlaylistFactory.getInstance().getPlaylists();
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		if (playlists.getPlist() == null) {
			return list;
		}
		
		for (int i = 0; i < playlists.getPlist().size(); i++) {
			String s = playlists.getPlist().get(i).getName();
			list.add(new SelectItem(s, s));
		}
		
		return list;
		
	}
	
	
	
	/**
	 * This method is fired when another playlist is selected from dropdown menu.
	 * @param e
	 */
	public void playlistChanged(ValueChangeEvent e) {
		selectedPlaylist = e.getNewValue().toString();
		logger.info("PlayerBean:playlistChanged(): event - selected playlist: " + selectedPlaylist);
		Mp3Player.getInstance().setPassivePlaylist(selectedPlaylist);
	}
	
	public String gotoQueue() {
		Mp3Player.getInstance().setPassivePlaylist("Queue");
		return "queue";
	}
	
	
	
	/**
	 * Get current playlist source.
	 * @return playlist
	 */
	public String getSelectedPlaylist() {
		if (selectedPlaylist == null) {
			selectedPlaylist = Mp3Player.getInstance().getPassivePlaylist().getName();
		}
		return selectedPlaylist;
	}
	
	
	
	public void setSelectedPlaylist(String selectedPlaylist) {
		this.selectedPlaylist = selectedPlaylist;
	}
	
	
	

	



	/**
	 * Return title of currently playing song. If no song is playing "null" is returned.
	 * @return title or "null"
	 */
	public String getCurrentlyPlaying() {
		Mp3File m = Mp3Player.getInstance().getCurrentlyPlaying();
		if (m == null) {
			return "null";
		}
		String s = m.getTitle();
		if (m.getArtist() != null && m.getArtist().length() > 0) {
			s = m.getArtist() + " - " + s;
		}
		return s;
	}
	
	
	
	/**
	 * Increase sound volume by 1.
	 */
	public void volumeUp() {
		SoundControl.volumeUp();
	}
	
	
	
	/**
	 * Decrease sound volume by 1.
	 */
	public void volumeDown() {
		SoundControl.volumeDown();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Start playing (first song in the playlist)
	 * @return title of the song
	 */
	public String play() {
		Mp3Player.getInstance().setPassiveToActive();
		return Mp3Player.getInstance().play(0);
	}
	
	

	/**
	 * Stop playing
	 * @return "null"
	 */
	public String stop() {
		Mp3Player.getInstance().stop();
		return "null";
	}
	
	
	public String togglePlayStop() {
		if (isPlaying()) {
			return stop();
		} else {
			return play();
		}
	}
	
	
	
	/**
	 * Play next
	 * @return title
	 */
	public String next() {
		return Mp3Player.getInstance().next();
	}
	
	
	
	/**
	 * Play previous
	 * @return title
	 */
	public String prev() {
		return Mp3Player.getInstance().prev();
	}
	
	
	
	/**
	 * Return true if song is currently playing
	 * @return true if playing
	 */
	public boolean isPlaying() {
		return Mp3Player.getInstance().isPlaying();
	}
	
	
	
	/**
	 * Return true if repeat button is turned on.
	 * @return true if on
	 */
	public boolean isRepeatOn() {
		// TODO why is this method called so many times???
//		logger.info("PlayerBean:isRepeatOn(): " + Mp3Player.getInstance().isRepeatOn());
		return Mp3Player.getInstance().isRepeatOn();
	}
	
	public void toggleRepeat() {
		Mp3Player.getInstance().setRepeatOn(!Mp3Player.getInstance().isRepeatOn());
		logger.info("PlayerBean:toggleRepeat(): repeat is now: " + Mp3Player.getInstance().isRepeatOn());
	}
	
	
	
}
