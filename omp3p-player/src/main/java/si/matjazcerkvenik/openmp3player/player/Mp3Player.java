package si.matjazcerkvenik.openmp3player.player;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import si.matjazcerkvenik.openmp3player.LogUtil;
import si.matjazcerkvenik.openmp3player.model.Mp3File;
import si.matjazcerkvenik.openmp3player.model.Omp3pServiceImpl;
import si.matjazcerkvenik.openmp3player.model.Playlist;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

/**
 * This class represents the whole mp3 player. This means the player implementation 
 * itself and holds a current (active) playlist.
 * 
 * @author matjaz
 *
 */
@Service
//@Component
public class Mp3Player {
	
//	private SimpleLogger logger = null;
	
//	private IPlayer player = new PlayerImpl();
	private IPlayer player;
	private boolean repeatOn = false;
	private Mp3File currentlyPlaying = null;
	private Playlist activePlaylist = null;
	
	@Autowired
	private Omp3pServiceImpl service;
	
	
//	public Mp3Player() {
		
		// logger = OContext.getInstance().getLogger();
				
//		player = new PlayerImpl();
		
//		activePlaylist = service.getAllPlaylists().get(0);
//		if (activePlaylist == null) {
//			activePlaylist = new Playlist();
//		}
		
		// logger.info("Mp3Player: #" + this.hashCode());
		
//	}
	
	@PostConstruct
	public void init() {
		System.out.println("init()");
//		logger.getLogger().info("init player");
		player = new PlayerImpl();
		
	}
	
	
	/**
	 * Return currently playing mp3 file.
	 * @return mp3
	 */
	public Mp3File getCurrentlyPlaying() {
		return currentlyPlaying;
	}
	
	
	
	/**
	 * Start playing song with index i in active playlist
	 * @param i
	 * @return title of the song
	 */
	public void play(int i) {
		
		activePlaylist = service.getAllPlaylists().get(0);
		
		if (currentlyPlaying != null) {
			stop();
		}
		
		if (activePlaylist.getMp3files().isEmpty()) {
			return;
		}
		
		currentlyPlaying = activePlaylist.getMp3files().get(i);
//		logger.info("Mp3Player:play(): playlist: "
//				+ activePlaylist.getName() + ", MP3: "
//				+ currentlyPlaying.toString());
		player.play(currentlyPlaying.getPath());
				
	}
	
	
	public Mp3File play(int playlist_id, int mp3File_id) {
		
		activePlaylist = service.getPlaylist(playlist_id);
		
		if (activePlaylist.getMp3files().isEmpty()) {
			return null;
		}
		
		if (currentlyPlaying != null) {
			stop();
		}
		
		currentlyPlaying = service.getMp3File(mp3File_id);
		
//		currentlyPlaying = activePlaylist.getMp3files().get(mp3File_id);
//		logger.info("Mp3Player:play(): playlist: "
//				+ activePlaylist.getName() + ", MP3: "
//				+ currentlyPlaying.toString());
		player.play(currentlyPlaying.getPath());
		
		return currentlyPlaying;
				
	}
	
	
	
	/**
	 * Stop playing song
	 * @return 'null'
	 */
	public void stop() {
		if (currentlyPlaying == null) {
			return;
		}
		
//		logger.info("Mp3Player:stop(): playlist: " + activePlaylist.getName() 
//				+ ", MP3: " + currentlyPlaying.toString());
		player.stop();
		currentlyPlaying = null;
		
	}
	
	
	
	/**
	 * Play next song
	 * @return title
	 */
	public void next() {
		if (currentlyPlaying == null) {
			return;
		}
		
//		if (currentlyPlaying.getIndex()  // TODO
//				== activePlaylist.getMp3files().getFiles().size() - 1) {
//			play(0);
//		} else {
//			play(currentlyPlaying.getIndex() + 1);
//		}
	}
	
	
	
	/**
	 * Play previous song
	 * @return title
	 */
	public void prev() {
		if (currentlyPlaying == null) {
			return;
		}
		
//		if (currentlyPlaying.getIndex() == 0) { // TODO
//			play(activePlaylist.getMp3files().getFiles().size() - 1);
//		} else {
//			play(currentlyPlaying.getIndex() - 1);
//		}
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
//		logger.debug("Mp3Player:getActivePlaylist(): " + activePlaylist.getName());
		return activePlaylist;
	}
	
	
	/**
	 * Set active playlist
	 * @param p
	 */
	public void setActivePlaylist(Playlist p) {
//		logger.debug("Mp3Player:setActivePlaylist(): " + p.getName());
		activePlaylist = p;
	}
	
	
	/**
	 * Return true if repeat is on.
	 * @return
	 */
	public boolean isRepeatOn() {
		return repeatOn;
	}
	
	
	
	/**
	 * Set repeat on/off
	 * @param repeatOn
	 */
	public void setRepeatOn(boolean repeatOn) {
		this.repeatOn = repeatOn;
//		logger.info("Mp3Player:setRepeatOn(): repeat is now: " + this.repeatOn);
	}
	
	
	/**
	 * Increment counter of currently playing song and set current time for last played time.
	 */
	public void updateCurrentlyPlaying() {
		
		// TODO
//		currentlyPlaying.setLastPlayedTime(Utils.getNow());
//		currentlyPlaying.setCount(currentlyPlaying.getCount() + 1);
//		DAO.getInstance().savePlaylist(activePlaylist);
		
	}
	
}
