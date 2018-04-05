package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Playlist {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
	private int id = 0;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, targetEntity=Tag.class, fetch=FetchType.EAGER)
	private List<Mp3File> mp3files;

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

	public List<Mp3File> getMp3files() {
		return mp3files;
	}

	public void setMp3files(List<Mp3File> mp3files) {
		this.mp3files = mp3files;
	}

	@Override
	public String toString() {
		return "PLAYLIST: " + name;
	}

}
