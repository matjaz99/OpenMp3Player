package si.matjazcerkvenik.openmp3player.backend;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Playlists {

	private List<Playlist> plist;

	public List<Playlist> getPlist() {
		return plist;
	}

	@XmlElement
	public void setPlist(List<Playlist> list) {
		this.plist = list;
	}
	
	public void add(Playlist p) {
		
		if (plist == null) {
			plist = new ArrayList<Playlist>();
		}
		
		plist.add(p);
		
	}
	
	@Override
	public String toString() {
		String s = "Playlists:";
		if (plist == null) {
			return s += " [empty]";
		}
		for (int i = 0; i < plist.size(); i++) {
			s += plist.get(i).getName() + ", ";
		}
		return s;
	}

}
