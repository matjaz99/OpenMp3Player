package si.matjazcerkvenik.openmp3player.backend;

import java.io.File;

public class Mp3File {
	
	private int index = 0;
	private File file = null;
	private String title = "null";
	private String artist = "null";
	private String album = "null";
	
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public boolean fileExists() {
		return file.exists();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
	
	@Override
	public String toString() {
		return "[" + index + "] Title: " + title + ", Artist: " + artist + ", Album: " + album;
	}
	
}
