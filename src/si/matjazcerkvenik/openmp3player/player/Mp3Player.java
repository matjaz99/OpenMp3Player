package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.backend.Playlists;
import si.matjazcerkvenik.openmp3player.io.PlaylistFactory;
import si.matjazcerkvenik.openmp3player.player.jlayer.JLayerPlayer;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class Mp3Player {
	
	private SimpleLogger logger = null;
	private static Mp3Player mp3player = null;
	
	private IPlayer player = null;
	private Mp3File currentlyPlaying = null;
	private boolean repeatOn = false;
	
	private Playlists playlists = null;
	private Playlist playlist = null;
	
	private Mp3Player() {
		
		logger = OContext.getInstance().getLogger();
		
		player = new JLayerPlayer();
		
		PlaylistFactory pFactory = new PlaylistFactory();
		playlists = pFactory.getPlaylists();
		playlist = pFactory.getPlaylist(playlists.getPlist().get(0).getSource());
		
	}
	
	public static Mp3Player getInstance() {
		
		if (mp3player == null) {
			mp3player = new Mp3Player();
		}
		return mp3player;
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

	
	
	
	
	/**
	 * Start playing song with index i
	 * @param i
	 * @return title of the song
	 */
	public String play(int i) {
		
		if (currentlyPlaying != null) {
			stop();
		}
		
		currentlyPlaying = playlist.getMp3Files().get(i);
		logger.info("Mp3Player:play(): playlist: " + playlist.getName());
		logger.info("Mp3Player:play: playlist: " + playlist.getName() 
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
		
		logger.info("Mp3Player:stop(): playlist: " + playlist.getName());
		logger.info("Mp3Player:stop: playlist: " + playlist.getName() 
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
				== playlist.getMp3Files().size() - 1) {
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
			play(playlist.getMp3Files().size() - 1);
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
	
	public PlayerStatus getPlayerStatus() {
		return player.getStatus();
	}
	
	/**
	 * Return active playlist
	 * @return size
	 */
	public Playlist getPlaylist() {
		logger.debug("Mp3Player:getActivePlaylist(): " + playlist.getName());
		return playlist;
	}
	
	public void setPlaylist(String name) {
		PlaylistFactory pf = new PlaylistFactory();
		playlist = pf.getPlaylist(name);
	}

	public Playlists getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Playlists playlists) {
		this.playlists = playlists;
	}

	public boolean isRepeatOn() {
		return repeatOn;
	}

	public void setRepeatOn(boolean repeatOn) {
		this.repeatOn = repeatOn;
	}
	
	
	
}
