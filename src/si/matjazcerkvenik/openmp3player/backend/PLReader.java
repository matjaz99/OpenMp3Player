package si.matjazcerkvenik.openmp3player.backend;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class PLReader {
	
	public static List<Playlist> getPlaylists() {
		
		List<Playlist> list = new ArrayList<Playlist>();
		
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
//			doc = docBuilder.parse(new File("/Users/matjaz/Dropbox/MyWorkspace/MyMp3PlayerTomcat/WebContent/playlists.xml"));
			doc = docBuilder.parse(new File(Mng.HOME_DIR + "/playlists/playlists.xml"));
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return list;
		} catch (SAXException e) {
			e.printStackTrace();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return list;
		}

		NodeList nl = doc.getElementsByTagName("list");

		for (int i = 0; i < nl.getLength(); i++) {
			Playlist p = new Playlist();

			Element plist = (Element) nl.item(i);
			Element name = (Element) plist.getFirstChild().getNextSibling();
			Element source = (Element) name.getNextSibling().getNextSibling();

			p.setName(name.getFirstChild().getNodeValue());
			p.setSource(source.getFirstChild().getNodeValue());

			list.add(p);
		}
		
	    return list;
		
	}
	
	public static void main(String[] args) {
		
		List<Playlist> playlists = getPlaylists();
		for (int i = 0; i < playlists.size(); i++) {
			System.out.println(playlists.get(i).toString());
		}
		
	}
	
}
