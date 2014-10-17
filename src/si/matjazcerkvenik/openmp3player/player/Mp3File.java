package si.matjazcerkvenik.openmp3player.player;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;

public class Mp3File implements Cloneable {

	private int index = 0;
	private File file = null;
	private String path = null;
	private String title = "null";
	private String artist = "null";
	private String album = "null";

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Get absolute path of this file
	 * @return pathname
	 */
	public String getFile() {
		return file.getAbsolutePath();
	}

	public void setFile(String filepath) {
		this.file = new File(filepath);
	}

	public boolean fileExists() {
		return file.exists();
	}

	/**
	 * Get song title.<br>
	 * Remark: currently title returns name of file!
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set song title
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Get song artist
	 * @return artist
	 */
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	/**
	 * Get path of file.
	 * @return
	 */
	public String getPath() {
		return path;
	}

	@XmlElement
	public void setPath(String path) {
		this.path = path;
		file = new File(path);
		this.title = file.getName();
	}

	@Override
	public String toString() {
		return "[" + index + "] Path: " + path + ", Title: " + title
				+ ", Artist: " + artist + ", Album: " + album;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
