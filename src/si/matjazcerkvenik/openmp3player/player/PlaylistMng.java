package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.PlaylistDAO;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistMng {
	
	private SimpleLogger logger = null;
	
	public static final String QUEUE_NAME = "Queue";
	
	
	public PlaylistMng() {
		logger = OContext.getInstance().getLogger();
	}
	
	
	
	/**
	 * Return list of all playlists (including queue)
	 * @return
	 */
	public List<Playlist> getAllPlaylists() {
		List<Playlist> pList = PlaylistDAO.getInstance().getPlaylists().getPlist();
		return pList;
	}
	
	
	
	/**
	 * Return playlist by name
	 * @param name
	 * @return playlist
	 */
	public Playlist getPlaylist(String name) {
		return PlaylistDAO.getInstance().getPlaylist(name);
	}
	
	

	/**
	 * Remove mp3 <code>m</code> from playlist <code>p</code> and save to file.
	 * Also mp3 indexes are recalculated.
	 * @param m
	 * @param p
	 * @return playlist
	 */
	public Playlist removeMp3FromPlaylist(Mp3File m, Playlist p) {
		
		logger.info("PlaylistMng:removeMp3FromTheList(): " + m.getIndex());
		
		p.getMp3files().getFiles().remove(m);
		
		// set new indexes
		for (int i = 0; i < p.getMp3files().getFiles().size(); i++) {
			p.getMp3files().getFiles().get(i).setIndex(i);
		}
		// save playlist
		PlaylistDAO.getInstance().savePlaylist(p);
		
		return p;
	}
	
	
	/**
	 * Put mp3 file to queue
	 * @param mp3
	 */
	public void putToQueue(Mp3File mp3) {
		
		logger.info("PlaylistMng:putToQueue(): " + mp3.getIndex());
		
		try {
			
			Mp3File clone = (Mp3File) mp3.clone();
			PlaylistDAO.getInstance().addMp3ToPlaylist(clone, getPlaylist(QUEUE_NAME));
			
		} catch (CloneNotSupportedException e) {
			logger.error("Mp3Player:putToQueue(): CloneNotSupportedException", e);
		}
		
	}
	
	
	
	/**
	 * Save queue as playlist with name <code>name</code>
	 * @param name
	 */
	public void saveQueue(String name) {
		
		if (name == null || name.trim().length() == 0) {
			return;
		}
		
		Playlist p = new Playlist();
		p.setName(name);
		p.setSource(name + ".xml");
		
		Playlist queue = PlaylistDAO.getInstance().getPlaylist(QUEUE_NAME);
		
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
	 * Clear all songs from queue.
	 */
	public void emptyQueue() {
		PlaylistDAO.getInstance().getPlaylist(QUEUE_NAME).getMp3files().getFiles().clear();
	}
	
}
