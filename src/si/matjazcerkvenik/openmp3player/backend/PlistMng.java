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
		
//		logger = OContext.getInstance().getLogger();
		
//		loadPlaylists();
		activePlaylist = playlists.get(0).getName();
		showPlaylist = activePlaylist;
//		
//		queue = new Playlist();
//		queue.setName("Queue");
//		queue.setSource("queue");
	}

	
	/**
	 * Load all playlists from playlists.xml.
	 */
//	public void loadPlaylists() {
//
//		Playlists plists = unmarshall();
//		playlists = plists.getPlist();
//		setActivePlaylist(playlists.get(0).getName());
//
//	}
	
	
	/**
	 * Return list of all loaded playlists
	 * @return
	 */
//	public List<Playlist> getPlaylists() {
//		return playlists;
//	}

//	public void setPlaylists(List<Playlist> playlists) {
//		this.playlists = playlists;
//	}

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
	
	
//	/**
//	 * Create new playlist with given name and source and save to playlists.xml
//	 * @param name
//	 * @param source
//	 */
//	public void addPlaylist(String name, String source) {
//		
//		Playlist p = new Playlist();
//		p.setName(name);
//		p.setSource(source);
//		
//		playlists.add(p);
//		
//		marshall();
//		
//	}
	
	
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
	
//	/**
//	 * Add current song to queue
//	 * @param i
//	 */
//	public void addToQueue(int i) {
//		Mp3File mp3 = getShowPlaylist().getMp3Files().get(i);
//		try {
//			
//			Mp3File clone = (Mp3File) mp3.clone();
//			clone.setIndex(queue.getMp3Files().size());
//			queue.addMp3File(clone);
//			
//		} catch (CloneNotSupportedException e) {
//			logger.error("PlistMng:addToQueue(): CloneNotSupportedException", e);
//		}
//		
//		logger.info("PlistMng:addToQueue(): added " + mp3.getFile());
//	}
	
	public void saveQueue(String name) {
		Playlist p = new Playlist();
		p.setName(name);
		p.setSource(name + ".xml");
//		for (int i = 0; i < queue.getMp3Files().size(); i++) {
//			try {
//				Mp3File mp3 = (Mp3File) queue.getMp3Files().get(i).clone();
//				mp3.setIndex(i);
//				p.addMp3File(mp3);
//			} catch (CloneNotSupportedException e) {
//				logger.error("PlistMng:saveQueue(): CloneNotSupportedException", e);
//			}
//		}
		playlists.add(p);
		
		marshall();
		
//		XmlPlaylistFileFinder x = new XmlPlaylistFileFinder();
//		x.saveToXml(p.getSource());
	}
	
//	/**
//	 * Remove all songs from queue.
//	 */
//	public void emptyQueue() {
//		queue.getMp3Files().clear();
//	}
	
	

	/**
	 * Save to playlists.xml
	 */
	public void marshall() {

//		Playlists p = new Playlists();
//		for (int i = 0; i < playlists.size(); i++) {
//			p.add(playlists.get(i));
//		}
//
//		try {
//
//			File file = new File(OContext.CFG_DIR + "playlists/playlists.xml");
//			JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//
//			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//			 jaxbMarshaller.marshal(p, file);
////			jaxbMarshaller.marshal(p, System.out);
//
//		} catch (JAXBException e) {
//			logger.error("JAXBException", e);
//		}

	}
	

	

}
