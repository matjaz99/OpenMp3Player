package si.matjazcerkvenik.openmp3player.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mp3file")
public class Mp3File implements Cloneable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id = 0;
	
	@Column(name = "hash")
	private String hash = "0";
	
	@Column(name = "path")
	private String path = null;
	
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

	

}
