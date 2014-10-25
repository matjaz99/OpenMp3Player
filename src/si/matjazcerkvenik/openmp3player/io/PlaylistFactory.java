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
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.openmp3player.player.Playlists;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistFactory {
	
	private SimpleLogger logger = null;
	
	private static PlaylistFactory factory = null;
	
	
	private PlaylistFactory() {
		logger = OContext.getInstance().getLogger();
	}
	
	public static PlaylistFactory getInstance() {
		
		if (factory == null) {
			factory = new PlaylistFactory();
		}
		
		return factory;
		
	}
	
	/**
	 * Read playlists.xml. If playlists.xml does not exist, create new one.
	 * @return playlists
	 */
	public Playlists getPlaylists() {
		
		Playlists playlists = null;
		
		try {

			File file = new File(OContext.HOME_DIR + "playlists/playlists.xml");
			if (!file.exists()) {
				logger.warn("PlaylistFactory:getPlaylists(): playlists.xml not found; creating new");
				playlists = new Playlists();
				JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(playlists, file);
				
			}
			logger.info("PlaylistFactory:getPlaylists(): playlists.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			playlists = (Playlists) jaxbUnmarshaller.unmarshal(file);
			logger.debug("PlaylistFactory:getPlaylists(): " + playlists.toString());

		} catch (JAXBException e) {
			logger.error("JAXBException", e);
		}
		
		return playlists;
	}
	
	/**
	 * Save playlists.xml
	 */
	public void savePlaylists() {
		logger.info("PlaylistFactory:savePlaylists(): saving...");
		try {

			File file = new File(OContext.HOME_DIR + "playlists/playlists.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			// temporarily remove queue before saving
			Playlists plists = Mp3Player.getInstance().getPlaylists();
			Playlist queueTemp = null;
			for (int i = 0; i < plists.getPlist().size(); i++) {
				if (plists.getPlist().get(i).getSource().equals("queue")) {
					queueTemp = plists.getPlist().remove(i);
				}
			}
			
			 jaxbMarshaller.marshal(plists, file);
//			jaxbMarshaller.marshal(p, System.out);
			 
			// put queue back to list
			plists.add(queueTemp);
			

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
		Playlists p = getPlaylists();
		Playlist playlist = null;
		for (int i = 0; i < p.getPlist().size(); i++) {
			if (p.getPlist().get(i).getName().equals(name)) {
				playlist = p.getPlist().get(i);
			}
		}
		
		// load mp3 files
		List<Mp3File> files = null;
		
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
		
//		Playlist playlist = new Playlist();
		Mp3Files mp3files = new Mp3Files();
		mp3files.setFiles(files);
//		playlist.setMp3files(files);
		playlist.setMp3files(mp3files);
//		playlist.setName(name);
//		playlist.setSource(source);
		
		return playlist;
	}
	
	
	
	public void removePlaylist(String name) {
		
		// delete xml file
		for (Playlist plist : Mp3Player.getInstance().getPlaylists().getPlist()) {
			
			if (plist.getName().equals(name)) {
				
				if (plist.getSource().endsWith(".xml")) {
					
					File f = new File(OContext.HOME_DIR + "playlists/" + plist.getSource());
					logger.debug("PlaylistFactory:deletePlaylist(): delete: " + f.getAbsolutePath());
					f.delete();
					
				}
				
			}
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Read &lt;source&gt;.xml 
	 * @param source
	 * @return mp3Files
	 */
	private Mp3Files loadFromXml(String source) {
		Mp3Files mp3Files = null;
		try {

			File file = new File(OContext.HOME_DIR + "playlists/" + source);
			if (!file.exists()) {
				logger.warn("PlaylistFactory:unmarshall(): playlist not found: " + OContext.HOME_DIR + "playlists/" + source);
				return new Mp3Files();
			}
			logger.debug("PlaylistFactory:unmarshall(): playlist found: " + OContext.HOME_DIR + "playlists/" + source);
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
	public void savePlaylist(Playlist p) {
		logger.info("PlaylistFactory:savePlaylist(): saving...");
		try {

			File file = new File(OContext.HOME_DIR + "playlists/" + p.getSource());
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
	
	
	
	
	/**
	 * Load mp3 files from directory
	 * @param directory
	 * @return
	 */
	private List<Mp3File> loadFromDirectory(String directory) {
		
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
			Mp3File t = new Mp3File();
			t.setIndex(i);
			t.setPath(files[i].getAbsolutePath());
			list.add(t);
		}
				
		return list;
	}

}
