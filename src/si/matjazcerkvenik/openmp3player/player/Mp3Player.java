package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.PlaylistDAO;
import si.matjazcerkvenik.openmp3player.player.jlayer.JLayerPlayer;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class Mp3Player {
	
	private SimpleLogger logger = null;
	
	private IPlayer player = null;
	private Mp3File currentlyPlaying = null;
	private boolean repeatOn = false;
	
	
	private Playlist activePlaylist = null;
	private Playlist passivePlaylist = null;
	private Playlist queue = null;
	
	public Mp3Player() {
		
		logger = OContext.getInstance().getLogger();
		
		player = new JLayerPlayer();
		
		Playlists p = PlaylistDAO.getInstance().getPlaylists();
		if (p.getPlist().size() > 0) {
			activePlaylist = PlaylistDAO.getInstance().getPlaylist(p.getPlist().get(0).getName());
		} else {
			activePlaylist = new Playlist();
		}
		passivePlaylist = activePlaylist;
		
		queue = new Playlist();
		queue.setName("Queue");
		queue.setSource("queue");
		
	}
	
	
	
	/**
	 * Return title of currently playing song.
	 * @return title
	 */
	public Mp3File getCurrentlyPlaying() {
		return currentlyPlaying;
	}
	
	
	
	
	
	/**
	 * Start playing song with index i in active playlist
	 * @param i
	 * @return title of the song
	 */
	public String play(int i) {
		
		if (currentlyPlaying != null) {
			stop();
		}
		
		if (activePlaylist.getMp3files().getFiles().isEmpty()) {
			return "null";
		}
		
		currentlyPlaying = activePlaylist.getMp3files().getFiles().get(i);
		logger.info("Mp3Player:play(): playlist: "
				+ activePlaylist.getName() + ", MP3: "
				+ currentlyPlaying.toString());
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
		
		logger.info("Mp3Player:stop(): playlist: " + activePlaylist.getName() 
				+ ", MP3: " + currentlyPlaying.toString());
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
				== activePlaylist.getMp3files().getFiles().size() - 1) {
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
			play(activePlaylist.getMp3files().getFiles().size() - 1);
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
	public Playlist getActivePlaylist() {
		logger.debug("Mp3Player:getActivePlaylist(): " + activePlaylist.getName());
		return activePlaylist;
	}
	
	public void setActivePlaylist(String name) {
		logger.debug("Mp3Player:setActivePlaylist(): " + name);
		if (name.equals("Queue")) {
			activePlaylist = queue;
		} else {
			activePlaylist = PlaylistDAO.getInstance().getPlaylist(name);
		}
		
	}
	
	/**
	 * Return passive playlist
	 * @return size
	 */
	public Playlist getPassivePlaylist() {
		logger.trace("Mp3Player:getPassivePlaylist(): " + passivePlaylist.getName());
		return passivePlaylist;
	}
	
	public void setPassivePlaylist(String name) {
		logger.debug("Mp3Player:setPassivePlaylist(): " + name);
		if (name.equals("Queue")) {
			passivePlaylist = queue;
		} else {
			passivePlaylist = PlaylistDAO.getInstance().getPlaylist(name);
		}
		
	}
	
	public void setPassiveToActive() {
		activePlaylist = passivePlaylist;
		logger.debug("Mp3Player:setPassiveToActive(): active: " + activePlaylist.getName());
	}
	

	public boolean isRepeatOn() {
		return repeatOn;
	}

	public void setRepeatOn(boolean repeatOn) {
		this.repeatOn = repeatOn;
	}
	
	public void putToQueue(Mp3File mp3) {
		
		try {
			
			Mp3File clone = (Mp3File) mp3.clone();
			clone.setIndex(queue.getMp3files().getFiles().size());
			queue.addMp3File(clone);
			
		} catch (CloneNotSupportedException e) {
			logger.error("Mp3Player:putToQueue(): CloneNotSupportedException", e);
		}
		
	}
	
	
	
	public void saveQueue(String name) {
		
		if (name == null || name.trim().length() == 0) {
			return;
		}
		
		Playlist p = new Playlist();
		p.setName(name);
		p.setSource(name + ".xml");
		
		for (int i = 0; i < queue.getMp3files().getFiles().size(); i++) {
			try {
				Mp3File mp3 = (Mp3File) queue.getMp3files().getFiles().get(i).clone();
				mp3.setIndex(i);
				p.addMp3File(mp3);
			} catch (CloneNotSupportedException e) {
				logger.error("Mp3Player:saveQueue(): CloneNotSupportedException", e);
			}
		}
		
		PlaylistDAO.getInstance().addPlaylist(p);
	}
	
	/**
	 * Remove all songs from queue.
	 */
	public void emptyQueue() {
		queue.getMp3files().getFiles().clear();
	}
	
	
	
	/**
	 * Get i-th mp3file from the passive playlist
	 * @param i
	 * @return mp3File
	 */
	public Mp3File getMp3(int i) {
		return passivePlaylist.getMp3files().getFiles().get(i);
	}
	
	public void removeMp3FromPassiveList(Mp3File m) {
		passivePlaylist.getMp3files().getFiles().remove(m);
		// set new indexes
		for (int i = 0; i < passivePlaylist.getMp3files().getFiles().size(); i++) {
			passivePlaylist.getMp3files().getFiles().get(i).setIndex(i);
		}
		// save playlist (if not queue)
		if (!passivePlaylist.getName().equals("Queue")) {
			PlaylistDAO.getInstance().savePassivePlaylist();
		}
		
	}
	
}
