package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.backend.OContext;
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
	private Playlist activePlaylist = null;
	private Playlist passivePlaylist = null;
	private Playlist queue = null;
	
	private Mp3Player() {
		
		logger = OContext.getInstance().getLogger();
		
		player = new JLayerPlayer();
		
		playlists = PlaylistFactory.getInstance().getPlaylists();
		activePlaylist = PlaylistFactory.getInstance().getPlaylist(playlists.getPlist().get(0).getName());
		passivePlaylist = activePlaylist;
		
//		queue = new Playlist();
//		queue.setName("Queue");
//		queue.setSource("queue");
//		playlists.add(queue);
		addPlaylist("Queue", "queue");
		
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
	public Mp3File getCurrentlyPlaying() {
		return currentlyPlaying;
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
		
		currentlyPlaying = activePlaylist.getMp3files().getFiles().get(i);
		logger.info("Mp3Player:play(): playlist: " + activePlaylist.getName() 
				+ ", MP3: " + currentlyPlaying.toString());
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
				+ ", MP3: [" + currentlyPlaying.toString());
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
			activePlaylist = PlaylistFactory.getInstance().getPlaylist(name);
		}
		
	}
	
	/**
	 * Return passive playlist
	 * @return size
	 */
	public Playlist getPassivePlaylist() {
		logger.debug("Mp3Player:getPassivePlaylist(): " + passivePlaylist.getName());
		return passivePlaylist;
	}
	
	public void setPassivePlaylist(String name) {
		logger.debug("Mp3Player:setPassivePlaylist(): " + name);
		if (name.equals("Queue")) {
			passivePlaylist = queue;
		} else {
			passivePlaylist = PlaylistFactory.getInstance().getPlaylist(name);
		}
		
	}
	
	public void setPassiveToActive() {
		logger.debug("Mp3Player:setPassiveToActive(): passive: " + passivePlaylist.getName()
				+ " to active: " + activePlaylist.getName());
		activePlaylist = passivePlaylist;
	}

//	public Playlist getQueue() {
//		return queue;
//	}
//
//	public void setQueue(Playlist queue) {
//		this.queue = queue;
//	}

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
	
	public void putToQueue(Mp3File mp3) {
		
		try {
			
			Mp3File clone = (Mp3File) mp3.clone();
			clone.setIndex(queue.getMp3files().getFiles().size());
			queue.addMp3File(clone);
			
		} catch (CloneNotSupportedException e) {
			logger.error("Mp3Player:putToQueue(): CloneNotSupportedException", e);
		}
		
	}
	
	/**
	 * Create new playlist with given name and source file and save to playlists.xml
	 * @param name
	 * @param source
	 */
	public void addPlaylist(String name, String source) {
		
		Playlist p = new Playlist();
		p.setName(name);
		p.setSource(source);
//		p.setMp3files(new Mp3Files());
		
		playlists.add(p);
		
		logger.info("Mp3Player:addPlaylist(): name: " + name + " source: " + source);
		
		if (source.equals("queue")) {
			queue = p;
			return;
		}
		
		PlaylistFactory.getInstance().savePlaylists();
		p.setMp3files(PlaylistFactory.getInstance().getPlaylist(name).getMp3files());
		
	}
	
	/**
	 * Delete playlist with given name and source file and save playlists.xml
	 * @param p
	 */
	public void deletePlaylist(Playlist p) {
		
		PlaylistFactory.getInstance().removePlaylist(p.getName());
		
		playlists.getPlist().remove(p);
		
		PlaylistFactory.getInstance().savePlaylists();
		
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
		
		playlists.add(p);
		
		PlaylistFactory.getInstance().savePlaylist(p);
		PlaylistFactory.getInstance().savePlaylists();
	}
	
	/**
	 * Remove all songs from queue.
	 */
	public void emptyQueue() {
		queue.getMp3files().getFiles().clear();
	}
	
}
