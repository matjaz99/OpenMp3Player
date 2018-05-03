package si.matjazcerkvenik.openmp3player.ui;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

	private int id;
	
	private String name;
	
	private String sourceDirectory;
	
    private List<Mp3File> mp3files;
	
	

	public Playlist() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public void setName(String name) {
		this.name = name;
	}

	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public List<Mp3File> getMp3files() {
		return mp3files;
	}

	public void setMp3files(List<Mp3File> mp3files) {
		this.mp3files = mp3files;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("PLAYLIST: " + name + "\n");
		for (Mp3File mp3File : mp3files) {
			sb.append("\t" + mp3File.toString() + "\n");
		}
		return sb.toString();
	}

}
