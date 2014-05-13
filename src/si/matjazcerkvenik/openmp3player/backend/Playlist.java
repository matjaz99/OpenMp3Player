package si.matjazcerkvenik.openmp3player.backend;

import java.util.List;

public class Playlist {
	
	private String name;
	private String source;
	private List<Mp3File> mp3files;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Mp3File> getMp3Files() {
		return mp3files;
	}

	public void setMp3files(List<Mp3File> mp3files) {
		this.mp3files = mp3files;
	}
	
	// TODO sortByTitle, sortByArtist...
	
	@Override
	public String toString() {
		return "PLAYLIST: " + name + ", SOURCE: " + source;
	}
	
	
}
