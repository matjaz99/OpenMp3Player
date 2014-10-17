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

public class PlaylistFactory {
	
	private SimpleLogger logger = null;
	
	public PlaylistFactory() {
		logger = OContext.getInstance().getLogger();
	}
	
	/**
	 * Read playlists.xml and return list of playlists. If playlists.xml does not exist, 
	 * create new one.
	 * @return playlists
	 */
	public Playlists getPlaylists() {
		
		Playlists playlists = null;
		
		try {

			File file = new File(OContext.CFG_DIR + "playlists/playlists.xml");
			if (!file.exists()) {
				logger.warn("PlaylistFactory:unmarshall(): playlists.xml not found; creating new");
				playlists = new Playlists();
				JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller.marshal(playlists, file);
				
			}
			logger.info("PlaylistFactory:unmarshall(): playlists.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Playlists.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			playlists = (Playlists) jaxbUnmarshaller.unmarshal(file);
			logger.debug(playlists.toString());

		} catch (JAXBException e) {
			logger.error("JAXBException", e);
		}
		
		return playlists;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public Playlist getPlaylist(String source) {
		
		String name = "";
		Playlists p = getPlaylists();
		for (int i = 0; i < p.getPlist().size(); i++) {
			if (p.getPlist().get(i).getSource().equals(source)) {
				name = p.getPlist().get(i).getName();
			}
		}
		
		List<Mp3File> files = null;
		
		if (source.endsWith(".xml")) {
			files = unmarshall(source).getFiles();
		} else {
			files = loadMp3FilesFromDirectory(source);
		}
		
		logger.info("PlaylistFactory:getPlaylist(): loaded " + files.size() + " mp3s from playlist " + source);
		
		for (int i = 0; i < files.size(); i++) {
			files.get(i).setIndex(i);
		}
		
		Playlist playlist = new Playlist();
		playlist.setMp3files(files);
		playlist.setName(name);
		playlist.setSource(source);
		
		return playlist;
	}
	
	
	
	
	
	
	/**
	 * Read &lt;source&gt;.xml 
	 * @param source
	 * @return mp3Files
	 */
	private Mp3Files unmarshall(String source) {
		Mp3Files mp3Files = null;
		try {

			File file = new File(OContext.CFG_DIR + "playlists/" + source);
			if (!file.exists()) {
				logger.warn("PlaylistFactory:unmarshall(): playlist not found: " + OContext.CFG_DIR + "playlists/" + source);
				return new Mp3Files();
			}
			logger.debug("PlaylistFactory:unmarshall(): playlist found: " + OContext.CFG_DIR + "playlists/" + source);
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
	private static void marshall(Mp3Files m, String source) {
// TODO not used yet!!
		try {

			File file = new File(OContext.CFG_DIR + "playlists/" + source);
			JAXBContext jaxbContext = JAXBContext.newInstance(Mp3Files.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(m, file);
//			jaxbMarshaller.marshal(m, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}
	
//	public void saveToXml(String source) {
//		// TODO not used yet!!	
//		OContext mng = OContext.getInstance();
//
//		List<Playlist> plists = mng.getPlistMng().getPlaylists();
//
//		Playlist p = null;
//		for (int i = 0; i < plists.size(); i++) {
//			if (plists.get(i).getSource().equals(source)) {
//				p = plists.get(i);
//			}
//		}
//		
//		Mp3Files m = new Mp3Files();
//		m.setFile(p.getMp3Files());
//		
//		marshall(m, p.getSource());
//		
//	}
	
	
	
	/**
	 * Load mp3 files from directory
	 * @param directory
	 * @return
	 */
	private List<Mp3File> loadMp3FilesFromDirectory(String directory) {
		
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
