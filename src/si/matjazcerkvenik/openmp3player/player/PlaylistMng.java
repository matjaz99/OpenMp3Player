package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.PlaylistDAO;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistMng {
	
	private SimpleLogger logger = null;
		
	private Playlist queue = null;
	
	public PlaylistMng() {
		
		logger = OContext.getInstance().getLogger();
				
		queue = new Playlist();
		queue.setName("Queue");
		queue.setSource("queue");
		
	}
	
	/**
	 * Return list of all playlists, including queue
	 * @return
	 */
	public List<Playlist> getAllPlaylists() {
		
		List<Playlist> pList = PlaylistDAO.getInstance().getPlaylists().getPlist();
		
//		pList.add(queue);
		
		return pList;
		
	}
	
	public Playlist getPlaylist(String name) {
		
		return PlaylistDAO.getInstance().getPlaylist(name);
		
	}
	
	
	public Playlist removeMp3FromPlaylist(Mp3File m, Playlist p) {
		
		p.getMp3files().getFiles().remove(m);
		
		// set new indexes
		for (int i = 0; i < p.getMp3files().getFiles().size(); i++) {
			p.getMp3files().getFiles().get(i).setIndex(i);
		}
		// save playlist (if not queue)
		if (!p.getName().equals("Queue")) {
			PlaylistDAO.getInstance().savePlaylist(p);
		}
		
		return p;
	}
	
	
}
