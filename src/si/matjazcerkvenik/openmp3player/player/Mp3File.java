package si.matjazcerkvenik.openmp3player.player;

import java.io.File;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

public class Mp3File implements Cloneable {
	
	private int index = 0;
	private String hash = "0";
	private String path = null;
	private String title = "";
	private String artist = "";
	private String album = "";
	private String genre = "";
	private String year = "";
	private int size = 0;
	private String backgroundColor = null;
	private Tags tags = new Tags();
	private int stars = 0;

	@XmlTransient
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Get SHA1 or MD5 hash
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Set SHA1 or MD5 hash
	 * @param hash
	 */
	@XmlElement
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
	@XmlElement
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

	@XmlElement
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	@XmlElement
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

	@XmlElement
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYear() {
		return year;
	}

	@XmlElement
	public void setYear(String year) {
		this.year = year;
	}

	public int getSize() {
		return size;
	}

	@XmlElement
	public void setSize(int size) {
		this.size = size;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	@XmlElement
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Tags getTags() {
		return tags;
	}

	@XmlElement
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	
	public void addTag(Tag t) {
		tags.addTag(t);
	}

	public int getStars() {
		return stars;
	}

	@XmlElement
	public void setStars(int stars) {
		this.stars = stars;
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
