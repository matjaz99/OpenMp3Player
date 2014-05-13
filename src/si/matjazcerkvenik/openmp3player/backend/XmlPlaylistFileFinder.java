package si.matjazcerkvenik.openmp3player.backend;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlPlaylistFileFinder implements IFileFinder {
	
	@Override
	public List<Mp3File> getMp3Files(String source) {
		
		XmlPlaylistFileFinder x = new XmlPlaylistFileFinder();
		
		List<Mp3File> list = x.unmarshall(source).getFile();
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setIndex(i);
		}
		
		return list;
	}
	
	public void saveToXml(String source) {
		
		Mng mng = new Mng();

		List<Playlist> plists = mng.getPlistMng().getPlaylists();

		Playlist p = null;
		for (int i = 0; i < plists.size(); i++) {
			if (plists.get(i).getSource().equals(source)) {
				p = plists.get(i);
			}
		}
		
		Mp3Files m = new Mp3Files();
		m.setFile(p.getMp3Files());
		
		marshall(m, p.getSource());
		
	}
	
	private static void marshall(Mp3Files m, String source) {

		try {

			File file = new File(Mng.HOME_DIR + "playlists/" + source);
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

			File file = new File(Mng.HOME_DIR + "playlists/" + source);
			if (!file.exists()) {
				Mng.getLogger().warn("playlist not found: " + Mng.HOME_DIR + "playlists/" + source);
				return new Mp3Files();
			}
//			 File file = new File("WebContent/playlists/salsa.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Mp3Files.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			mp3Files = (Mp3Files) jaxbUnmarshaller.unmarshal(file);
//			System.out.println(mp3Files);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return mp3Files;
	}
	
	
	
}