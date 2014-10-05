package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.io.PlaylistFactory;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistBean {
	
	private PlaylistFactory pFactory = null;
	private Playlist playlist = null;
	
	private SimpleLogger logger = null;
	
	public PlaylistBean() {
		logger = OContext.getInstance().getLogger();
		pFactory = new PlaylistFactory();
		playlist = pFactory.getPlaylist("salsa.xml");
	}
	
	public List<Mp3File> getMp3List() {
		return playlist.getMp3Files();
	}
	
	public int getPlaylistSize() {
		return playlist.getMp3Files().size();
	}
	
	public void setActivePlaylist(String name) {
		playlist = pFactory.getPlaylist(name);
	}
	
	public Playlist getActivePlaylist() {
		logger.debug("PlaylistBean:getActivePlaylist(): " + playlist.getName());
		return playlist;
	}
	
}
