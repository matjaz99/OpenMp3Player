package si.matjazcerkvenik.openmp3player.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.PlaylistDAO;
import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.openmp3player.player.Playlists;
import si.matjazcerkvenik.openmp3player.player.SoundControl;
import si.matjazcerkvenik.openmp3player.player.Watchdog;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

/**
 * This class handles all user interactions with player, including play/stop/next/prev buttons,
 * currently selected playlist, volume up/down buttons, repeat button...
 * 
 * @author matjaz
 *
 */
@ManagedBean
@ApplicationScoped
public class PlayerBean {
	
	private SimpleLogger logger = null;
	
	private String selectedPlaylist = null;
	
	private Mp3Player mp3Player = null;
	private Watchdog watchdog = null;
	
	
	public PlayerBean() {
		logger = OContext.getInstance().getLogger();
		mp3Player = new Mp3Player();
		watchdog = new Watchdog(mp3Player);
		watchdog.start();
		OContext.getInstance().startCli(mp3Player);
	}
	
	
	
	public Mp3Player getMp3Player() {
		return mp3Player;
	}



	/**
	 * Get playlists for dropdown menu
	 * @return list
	 */
	public List<SelectItem> getPlaylists() {
		
		Playlists playlists = PlaylistDAO.getInstance().getPlaylists();
		
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
		mp3Player.setPassivePlaylist(selectedPlaylist);
	}
	
	public String gotoQueue() {
		mp3Player.setPassivePlaylist("Queue");
		return "queue";
	}
	
	
	
	/**
	 * Get current playlist source.
	 * @return playlist
	 */
	public String getSelectedPlaylist() {
		if (selectedPlaylist == null) {
			selectedPlaylist = mp3Player.getPassivePlaylist().getName();
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
		Mp3File m = mp3Player.getCurrentlyPlaying();
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
		mp3Player.setPassiveToActive();
		return mp3Player.play(0);
	}
	
	

	/**
	 * Stop playing
	 * @return "null"
	 */
	public String stop() {
		mp3Player.stop();
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
		return mp3Player.next();
	}
	
	
	
	/**
	 * Play previous
	 * @return title
	 */
	public String prev() {
		return mp3Player.prev();
	}
	
	
	
	/**
	 * Return true if song is currently playing
	 * @return true if playing
	 */
	public boolean isPlaying() {
		return mp3Player.isPlaying();
	}
	
	
	
	/**
	 * Return true if repeat button is turned on.
	 * @return true if on
	 */
	public boolean isRepeatOn() {
		// TODO why is this method called so many times???
//		logger.info("PlayerBean:isRepeatOn(): " + Mp3Player.getInstance().isRepeatOn());
		return mp3Player.isRepeatOn();
	}
	
	public void toggleRepeat() {
		mp3Player.setRepeatOn(!mp3Player.isRepeatOn());
		logger.info("PlayerBean:toggleRepeat(): repeat is now: " + mp3Player.isRepeatOn());
	}
	
	
	
}
