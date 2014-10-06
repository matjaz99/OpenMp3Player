package si.matjazcerkvenik.openmp3player.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlists;
import si.matjazcerkvenik.openmp3player.backend.Utils;
import si.matjazcerkvenik.openmp3player.io.PlaylistFactory;
import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.openmp3player.player.jlayer.JLayerPlayer;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlayerBean {
	
	private String selectedPlaylist = null;
	
	private IPlayer player = null;
	private Mp3File currentlyPlaying = null;
	
	private SimpleLogger logger = null;
	
	public PlayerBean() {
		player = new JLayerPlayer();
		logger = OContext.getInstance().getLogger();
	}
	
	private PlaylistBean getPlaylistBean() {
		return (PlaylistBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("playlistBean");
	}
	
	/**
	 * Get playlists for dropdown menu
	 * @return map
	 */
	public Map<String, String> getPlaylists() {
		
		PlaylistFactory f = new PlaylistFactory();
		Playlists playlists = f.getPlaylists();
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		if (playlists.getPlist() == null) {
			return map;
		}
		
		for (int i = 0; i < playlists.getPlist().size(); i++) {
			String s = playlists.getPlist().get(i).getName();
			String ss = playlists.getPlist().get(i).getSource();
			map.put(s, ss);
		}
		
		return map;
	}
	
	/**
	 * This method is fired when another playlist is selected from dropdown menu.
	 * @param e
	 */
	public void playlistChanged(ValueChangeEvent e) {
		selectedPlaylist = e.getNewValue().toString();
		logger.info("PlayerBean:playlistChanged(): event - selected playlist: " + selectedPlaylist);
		getPlaylistBean().setActivePlaylist(selectedPlaylist);
	}

	
	/**
	 * Get default value for playlists dropdown menu.
	 * @return
	 */
//	public String getInitialPlaylistSource() {
//		return getPlaylistBean().getActivePlaylist().getSource();
//	}


	
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
		
		currentlyPlaying = getPlaylistBean().getActivePlaylist().getMp3Files().get(i);
		logger.info("PlayerBean:play(): playlist: " + getPlaylistBean().getActivePlaylist().getName());
		logger.info("play: playlist: " + getPlaylistBean().getActivePlaylist().getName() 
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
		
		logger.info("PlayerBean:stop(): playlist: " + getPlaylistBean().getActivePlaylist().getName());
		logger.info("stop: playlist: " + getPlaylistBean().getActivePlaylist().getName() 
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
				== getPlaylistBean().getActivePlaylist().getMp3Files().size() - 1) {
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
			play(getPlaylistBean().getActivePlaylist().getMp3Files().size() - 1);
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
	
	
	public String getVersion() {
		return OContext.version;
	}
	
}
