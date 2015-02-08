package si.matjazcerkvenik.openmp3player.player;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Playlist {

	private String name;
	private String source;
	private String origin;
	private Mp3Files mp3files = new Mp3Files();

	/**
	 * Get name of xml file
	 * @return source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * Set name of xml file
	 * @param source
	 */
	@XmlElement
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * Get name of playlist
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of playlist. Xml file with the same name is created.
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get origin of playlist. Used for directory type of playlist, which 
	 * can be reloaded.
	 * @return
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * Original source of playlist - directory.
	 * @param origin
	 */
	@XmlElement
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * Add mp3 to playlist
	 */
	public void addMp3File(Mp3File mp3) {
		mp3files.add(mp3);
	}
	
	@XmlTransient
	public Mp3Files getMp3files() {
		return mp3files;
	}

	public void setMp3files(Mp3Files mp3files) {
		this.mp3files = mp3files;
	}

	/**
	 * Return true if this playlist is queue
	 * @return true/false
	 */
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
