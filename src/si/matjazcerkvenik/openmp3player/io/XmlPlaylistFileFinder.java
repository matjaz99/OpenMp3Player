package si.matjazcerkvenik.openmp3player.io;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.Mp3Files;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.backend.Playlists;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class XmlPlaylistFileFinder implements IFileFinder {
	
	private SimpleLogger logger = null;
	
	public XmlPlaylistFileFinder() {
		logger = OContext.getInstance().getLogger();
	}
	
	@Override
	public Playlist getPlaylist(String source) {
		
		List<Mp3File> files = unmarshall(source).getFiles();
		for (int i = 0; i < files.size(); i++) {
			files.get(i).setIndex(i);
		}
		
		Playlist playlist = new Playlist();
		playlist.setMp3files(files);
		
		return playlist;
	}
	
	
	
	@Override
	public List<Mp3File> getMp3Files(String source) {
		
		XmlPlaylistFileFinder x = new XmlPlaylistFileFinder();
		
		List<Mp3File> list = x.unmarshall(source).getFiles();
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIndex(i);
		}
		
		return list;
	}
	
	public void saveToXml(String source) {
		
		// temporarily commented
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
		
	}
	
	private static void marshall(Mp3Files m, String source) {

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
				logger.warn("playlist not found: " + OContext.CFG_DIR + "playlists/" + source);
				return new Mp3Files();
			}
			JAXBContext jaxbContext = JAXBContext.newInstance(Mp3Files.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			mp3Files = (Mp3Files) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return mp3Files;
	}

	@Override
	public Playlists getPlaylists() {
		return null;
	}

	
	
	
	
}
