package si.matjazcerkvenik.openmp3player.backend;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.openmp3player.player.Playlists;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

/**
 * PlistMng manages playlists, loads playlists, writes playlists to disc and 
 * loads the mp3 files (into playlists).
 * @author matjaz
 *
 */
@Deprecated
public class PlistMng {
	
	private List<Playlist> playlists = null;
	private String activePlaylist = null;
	private String showPlaylist = null;
	private Playlist queue = null;
	
	private SimpleLogger logger = null;
	
	public PlistMng() {

		activePlaylist = playlists.get(0).getName();
		showPlaylist = activePlaylist;

	}

	

	
	


	/**
	 * Return active playlist.
	 * @return plist
	 */
	public Playlist getActivePlaylist() {
		Playlist p = null;
		for (Playlist plist : playlists) {
			if (plist.getName().equals(activePlaylist)) {
				return plist;
			}
		}
		if (p == null) {
			if (queue.getName().equals(activePlaylist)) {
				p = queue;
			}
		}
		return p;
	}

	
	/**
	 * Set name of active playlist.
	 * @param name
	 */
	public void setActivePlaylist(String name) {
		logger.debug("PlistMng:setActivePlaylist(): " + name);
		this.activePlaylist = name;
	}
	
	
	/**
	 * Return show playlist.
	 * @return plist
	 */
	public Playlist getShowPlaylist() {
		Playlist p = null;
		for (Playlist plist : playlists) {
			if (plist.getName().equals(showPlaylist)) {
				return plist;
			}
		}
		if (p == null) {
			if (queue.getName().equals(showPlaylist)) {
				p = queue;
			}
		}
		return p;
	}

	
	/**
	 * Set name of 'show playlist'.
	 * @param name
	 */
	public void setShowPlaylist(String name) {
		logger.debug("PlistMng:setShowPlaylist(): " + name);
		this.showPlaylist = name;
	}
	
	/**
	 * Set show playlist as active playlist.
	 */
	public void resetActivePlaylist() {
		activePlaylist = showPlaylist;
		logger.debug("PlistMng:resetActivePlaylist(): active: " + activePlaylist);
	}
	

	
	
	/**
	 * Remove the playlist from playlists.xml. If this playlist is xml, then delete also xml file.
	 * @param name
	 */
	public void removePlaylist(String name) {
		
//		deletePlistFile(name);
		
		
//		List<Playlist> list = new ArrayList<Playlist>();
//		
//		for (int i = 0; i < playlists.size(); i++) {
//			if (!playlists.get(i).getName().equals(name)) {
//				list.add(playlists.get(i));
//			}
//		}
//		
//		playlists = list;
		
		// if deleted playlist was actove, set another playlist as active
		if (activePlaylist.equals(name) || showPlaylist.equals(name)) {
			setActivePlaylist(playlists.get(0).getName());
			setShowPlaylist(playlists.get(0).getName());
		}
		
//		marshall();
		
	}

	

	

}
