package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.io.IFileFinder;
import si.matjazcerkvenik.openmp3player.io.XmlPlaylistFileFinder;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistBean {
	
	private IFileFinder parser = null;
	private Playlist playlist = null;
	
	private SimpleLogger logger = null;
	
	public PlaylistBean() {
		logger = OContext.getInstance().getLogger();
		parser = new XmlPlaylistFileFinder();
		playlist = parser.getPlaylist("salsa.xml");
	}
	
	public List<Mp3File> getMp3List() {
		return playlist.getMp3Files();
	}
	
	public int getPlaylistSize() {
		return playlist.getMp3Files().size();
	}
	
}
