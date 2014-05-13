package si.matjazcerkvenik.openmp3player.backend;

import java.io.File;
import java.io.FileNotFoundException;
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

public class XmlPlaylistFileFinder implements IFileFinder {
	
	@Override
	public List<Mp3File> getMp3Files(String source) {
		
		List<Mp3File> list = new ArrayList<Mp3File>();
		
		Document doc = null;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			doc = docBuilder.parse(new File(source));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			return list;
		} catch (SAXException e) {
			e.printStackTrace();
			return list;
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException: " + source);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return list;
		}

		NodeList nl = doc.getElementsByTagName("file");

		for (int i = 0; i < nl.getLength(); i++) {

			Element mp3 = (Element) nl.item(i);
			Element name = (Element) mp3.getFirstChild().getNextSibling();
			String filepath = name.getFirstChild().getNodeValue();
			File file = new File(filepath);
			Mp3File p = new Mp3File();
			p.setIndex(i);
			p.setTitle(file.getName());
			p.setFile(file);
			list.add(p);
			
		}
		
	    return list;
	}
	
	public static void main(String[] args) {
		
		XmlPlaylistFileFinder x = new XmlPlaylistFileFinder();
		List<Mp3File> mp3Files = x.getMp3Files("D:\\My Dropbox\\MyWorkspace\\MyMp3PlayerTomcat\\WebContent\\salsa.xml");
		for (int i = 0; i < mp3Files.size(); i++) {
			System.out.println(mp3Files.get(i));
		}
		
	}
	
}
