package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mp3file")
public class Mp3File implements Cloneable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
	private int id;
	
	@Column(name = "hash")
	private String hash;
	
	@Column(name = "path")
	private String path;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=Tag.class, fetch=FetchType.EAGER)
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
