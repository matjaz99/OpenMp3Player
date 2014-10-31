package si.matjazcerkvenik.openmp3player.player;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

//@XmlType(propOrder = { "path", "artist", "title", "album"})
public class Mp3File implements Cloneable {
	
	private int index = 0;
//	private File file = null;
	private String hash = null;
	private String path = null;
	private String title = "null";
	private String artist = "null";
	private String album = "null";
	private String genre = null;
	private String year = null;
	private int size = 0;
	private String backgroundColor = null;
	private Tags tags = null;

	@XmlTransient
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
//	@XmlTransient public String getFile() {
//		return file.getAbsolutePath();
//	}
//
//	public void setFile(String filepath) {
//		this.file = new File(filepath);
//	}
//
//	public boolean fileExists() {
//		return file.exists();
//	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Get song title
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
		File file = new File(path);
		this.title = file.getName();
	}
	

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Tags getTags() {
		return tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag t) {
		if (tags == null) {
			tags = new Tags();
		}
		tags.addTag(t);
	}

	@Override
	public String toString() {
		return "[" + index + "] Path: " + path;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
