package si.matjazcerkvenik.openmp3player.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Mp3Files;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.openmp3player.player.Playlists;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistDAO {
	
	private SimpleLogger logger = null;
	
	private static PlaylistDAO dao = null;
	
	private Playlists playlists = null;
	
	
	private PlaylistDAO() {
		logger = OContext.getInstance().getLogger();
	}
	
	
	/**
	 * Return instance of <code>PlaylistDAO</code> (singleton)
	 * @return dao
	 */
	public static PlaylistDAO getInstance() {
		
		if (dao == null) {
			dao = new PlaylistDAO();
		}
		return dao;
		
	}
	
	
	/**
	 * Read playlists.xml. If playlists.xml does not exist, create new one.
	 * @return playlists
	 */
	public Playlists getPlaylists() {
		
		if (playlists == null) {
			loadPlaylists();
		}
		
		return playlists;
	}
	
	/**
	 * Read from playlists.xml file
	 */
	private void loadPlaylists() {
		try {

			File file = new File(OContext.PLAYLISTS_DIR + "/playlists.xml");
			if (!file.exists()) {
				logger.warn("PlaylistDAO:loadPlaylists(): playlists.xml not found; creating new");
				playlists = new Playlists();
				JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(playlists, file);
				
			}
			logger.info("PlaylistDAO:loadPlaylists(): playlists.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			playlists = (Playlists) jaxbUnmarshaller.unmarshal(file);
			if (playlists.getPlist() == null) {
				playlists.setPlist(new ArrayList<Playlist>());
			}
			logger.debug("PlaylistDAO:loadPlaylists(): " + playlists.toString());

		} catch (JAXBException e) {
			logger.error("JAXBException", e);
		}
	}
	
	
	/**
	 * Save to playlists.xml file
	 */
	private void savePlaylists() {
		logger.info("PlaylistDAO:savePlaylists(): saving...");
		try {

			File file = new File(OContext.PLAYLISTS_DIR + "/playlists.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			 jaxbMarshaller.marshal(playlists, file);
//			jaxbMarshaller.marshal(p, System.out);
			

		} catch (JAXBException e) {
			logger.error("JAXBException", e);
		}
	}
	
	
	
	
	
	
	
	
	
	/**
	 * Get playlist containing mp3 files
	 * @param name
	 * @return playlist
	 */
	public Playlist getPlaylist(String name) {
		
		// search for playlist
		Playlist playlist = null;
		for (int i = 0; i < playlists.getPlist().size(); i++) {
			if (playlists.getPlist().get(i).getName().equals(name)) {
				playlist = playlists.getPlist().get(i);
			}
		}
		
		// load mp3 files
		List<Mp3File> files = null;
		// FIXME always load from file, check hash, load id3...
		if (playlist.getSource().endsWith(".xml")) {
			files = loadFromXml(playlist.getSource()).getFiles();
		} else {
			files = loadFromDirectory(playlist.getSource());
		}
		
		logger.info("PlaylistFactory:getPlaylist(): loaded " + files.size() + " mp3s from playlist " + name);
		
		// set indexes
		for (int i = 0; i < files.size(); i++) {
			files.get(i).setIndex(i);
		}
		
		Mp3Files mp3files = new Mp3Files();
		mp3files.setFiles(files);
		playlist.setMp3files(mp3files);
		
		return playlist;
	}
	
	
	/**
	 * Add new playlist
	 * @param p
	 */
	public void addPlaylist(Playlist p) {
		playlists.add(p);
		savePlaylists();
		savePlaylist(p);
	}
	
	
	/**
	 * Remove playlist
	 * @param p
	 */
	public void removePlaylist(Playlist p) {
		
		// delete xml file
		for (Playlist plist : playlists.getPlist()) {
			
			if (plist.getName().equals(p.getName())) {
				if (plist.getSource().endsWith(".xml")) {
					File f = new File(OContext.PLAYLISTS_DIR + "/" + plist.getSource());
					logger.debug("PlaylistDAO:deletePlaylist(): delete: " + f.getAbsolutePath());
					f.delete();
				}
			}
			
		}
		
		playlists.getPlist().remove(p);
		savePlaylists();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Read &lt;source&gt;.xml 
	 * @param source
	 * @return mp3Files
	 */
	private Mp3Files loadFromXml(String source) {
		Mp3Files mp3Files = null;
		try {

			File file = new File(OContext.PLAYLISTS_DIR + "/" + source);
			if (!file.exists()) {
				logger.warn("PlaylistDAO:unmarshall(): playlist not found: " + OContext.PLAYLISTS_DIR + "/" + source);
				return new Mp3Files();
			}
			logger.debug("PlaylistDAO:unmarshall(): playlist found: " + OContext.PLAYLISTS_DIR + "/" + source);
			JAXBContext jaxbContext = JAXBContext.newInstance(Mp3Files.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			mp3Files = (Mp3Files) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return mp3Files;
	}
	
	/**
	 * save
	 * @param m
	 * @param source
	 */
	private void savePlaylist(Playlist p) {
		logger.info("PlaylistDAO:savePlaylist(): saving...");
		try {

			File file = new File(OContext.PLAYLISTS_DIR + "/" + p.getSource());
			JAXBContext jaxbContext = JAXBContext.newInstance(Mp3Files.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			Mp3Files m = p.getMp3files();

			jaxbMarshaller.marshal(m, file);
//			jaxbMarshaller.marshal(m, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
	
	public void savePassivePlaylist() {
		// FIXME provide playlist as arg (separate Mp3Player from the DAO)
//		Playlist p = Mp3Player.getInstance().getPassivePlaylist();
//		savePlaylist(p);
	}
	
	
	
	
	/**
	 * Load mp3 files from directory
	 * @param directory
	 * @return
	 */
	public List<Mp3File> loadFromDirectory(String directory) {
		
		File dir = new File(directory);
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getAbsolutePath().endsWith(".mp3");
			}
		});
		
		List<Mp3File> list = new ArrayList<Mp3File>();
		
		if (files == null) {
			return list;
		}
		
		for (int i = 0; i < files.length; i++) {
			Mp3File mp3 = new Mp3File();
			mp3.setIndex(i);
			mp3.setPath(files[i].getAbsolutePath());
			mp3.setHash(Digester.getSha1(files[i].getAbsolutePath()));
			mp3 = ID3Tag.getMetadata(mp3);
			list.add(mp3);
		}
				
		return list;
	}

}
