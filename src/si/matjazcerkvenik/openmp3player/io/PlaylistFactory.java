package si.matjazcerkvenik.openmp3player.io;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.Mp3Files;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.backend.Playlists;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistFactory {
	
	private SimpleLogger logger = null;
	
	public PlaylistFactory() {
		logger = OContext.getInstance().getLogger();
	}
	
	/**
	 * Read playlists.xml and return list of playlists
	 * @return playlists
	 */
	public Playlists getPlaylists() {
		
		Playlists playlists = null;
		
		try {

			File file = new File(OContext.CFG_DIR + "playlists/playlists.xml");
			if (file.exists()) {
				logger.info("PlaylistFactory:unmarshall(): playlists.xml");
			} else {
				logger.warn("PlaylistFactory:unmarshall(): playlists.xml not found on path: " + OContext.CFG_DIR + "playlists/playlists.xml");
				// TODO create new
			}
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
		
		List<Mp3File> files = unmarshall(source).getFiles();
		for (int i = 0; i < files.size(); i++) {
			files.get(i).setIndex(i);
		}
		
		Playlist playlist = new Playlist();
		playlist.setMp3files(files);
		
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

}
