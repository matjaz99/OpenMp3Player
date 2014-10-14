package si.matjazcerkvenik.openmp3player.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlists;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlayerBean {
	
	private String selectedPlaylist = null;
	
	private SimpleLogger logger = null;
	
	public PlayerBean() {
		logger = OContext.getInstance().getLogger();
	}
	
//	private PlaylistBean getPlaylistBean() {
//		return (PlaylistBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("playlistBean");
//	}
	
	/**
	 * Get playlists for dropdown menu
	 * @return map
	 */
//	public Map<String, String> getPlaylists() {
//		
//		Playlists playlists = Mp3Player.getInstance().getPlaylists();
//		
//		Map<String, String> map = new LinkedHashMap<String, String>();
//		
//		if (playlists.getPlist() == null) {
//			return map;
//		}
//		
//		for (int i = 0; i < playlists.getPlist().size(); i++) {
//			String s = playlists.getPlist().get(i).getName();
//			String ss = playlists.getPlist().get(i).getSource();
//			map.put(s, ss);
//		}
//		
//		return map;
//	}
	
	public List<SelectItem> getPlaylists() {
		
		Playlists playlists = Mp3Player.getInstance().getPlaylists();
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		if (playlists.getPlist() == null) {
			return list;
		}
		
		for (int i = 0; i < playlists.getPlist().size(); i++) {
			String s = playlists.getPlist().get(i).getName();
			String ss = playlists.getPlist().get(i).getSource();
			list.add(new SelectItem(ss, s));
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
		Mp3Player.getInstance().setPlaylist(selectedPlaylist);
	}

	
	/**
	 * Get current playlist source.
	 * @return playlist
	 */
	public String getSelectedPlaylist() {
		if (selectedPlaylist == null) {
			selectedPlaylist = Mp3Player.getInstance().getPlaylist().getSource();
		}
		return selectedPlaylist;
	}
	
	public void setSelectedPlaylist(String selectedPlaylist) {
		this.selectedPlaylist = selectedPlaylist;
	}
	
	
	

	/**
	 * Return title of currently playing song.
	 * @return title
	 */
	public String getCurrentlyPlaying() {
		Mp3File m = Mp3Player.getInstance().getCurrentlyPlaying();
		if (m == null) {
			return "null";
		}
		return Mp3Player.getInstance().getCurrentlyPlaying().getTitle();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Start playing
	 * @return title of the song
	 */
	public String play() {
		return Mp3Player.getInstance().play(0);
	}
	

	/**
	 * Stop playing
	 * @return 'null'
	 */
	public String stop() {
		Mp3Player.getInstance().stop();
		return "null";
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
	
	public boolean isRepeatOn() {
		logger.info("isRepeatOn(): " + Mp3Player.getInstance().isRepeatOn());
		return Mp3Player.getInstance().isRepeatOn();
	}
	
	public void turnRepeatOn() {
		Mp3Player.getInstance().setRepeatOn(true);
		logger.info("turnRepeatOn(): repeat is now: " + Mp3Player.getInstance().isRepeatOn());
	}
	
	public void turnRepeatOff() {
		Mp3Player.getInstance().setRepeatOn(false);
		logger.info("turnRepeatOff(): repeat is now: " + Mp3Player.getInstance().isRepeatOn());
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * Get version of OpenMp3Player
	 * @return version
	 */
	public String getVersion() {
		return OContext.version;
	}
	
}
