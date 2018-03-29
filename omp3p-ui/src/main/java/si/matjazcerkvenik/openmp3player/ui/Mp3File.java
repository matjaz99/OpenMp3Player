package si.matjazcerkvenik.openmp3player.ui;

import java.util.List;

public class Mp3File implements Cloneable {
	
	private int id = 0;
	
	private String hash = "0";
	
	private String path = null;
	
	private List<Tag> tags = null;
	
	public Mp3File() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	

}
