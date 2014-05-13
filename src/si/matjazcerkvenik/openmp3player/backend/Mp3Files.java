package si.matjazcerkvenik.openmp3player.backend;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="files")
public class Mp3Files {
	
	private List<Mp3File> file = new ArrayList<Mp3File>();

	public List<Mp3File> getFile() {
		return file;
	}

	@XmlElement
	public void setFile(List<Mp3File> file) {
		this.file = file;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < file.size(); i++) {
			System.out.println("File: " + file.get(i));
		}
		return s;
	}
	
}
