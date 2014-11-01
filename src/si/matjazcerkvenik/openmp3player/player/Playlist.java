package si.matjazcerkvenik.openmp3player.player;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Playlist {

	private String name;
	private String source;
	private String origin;
	private Mp3Files mp3files = new Mp3Files();

	public String getSource() {
		return source;
	}

	@XmlElement
	public void setSource(String source) {
		this.source = source;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	public String getOrigin() {
		return origin;
	}

	@XmlElement
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Add mp3 to playlist
	 */
	public void addMp3File(Mp3File mp3) {
		if (mp3files == null) {
			mp3files = new Mp3Files();
		}
		mp3files.add(mp3);
	}
	
	@XmlTransient
	public Mp3Files getMp3files() {
		return mp3files;
	}

	public void setMp3files(Mp3Files mp3files) {
		this.mp3files = mp3files;
	}

	public boolean isQueue() {
		if (source.equals("queue")) {
			return true;
		}
		return false;
	}
	

	@Override
	public String toString() {
		return "PLAYLIST: " + name + ", SOURCE: " + source;
	}

}
