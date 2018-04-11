package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "playlist")
public class Playlist {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
	private int id;
	
	@Column(name = "playlist_name", unique = false, nullable = false, length = 64)
	private String name;
	
	@Column(name = "playlist_source_dir", unique = false, nullable = false)
	private String sourceDirectory;
	
//	@OneToMany(cascade=CascadeType.ALL, targetEntity=Mp3File.class, fetch=FetchType.EAGER)
//	@JoinColumn(name="playlist_id")
//	@ManyToMany(cascade=CascadeType.ALL, targetEntity=Mp3File.class, fetch=FetchType.EAGER)
//	@JoinTable(name = "playlist_mp3file",
//			joinColumns = { @JoinColumn(name = "playlist_id") },
//			inverseJoinColumns = { @JoinColumn(name = "file_id") })
	
	
	
	@ManyToOne
    @JoinColumn(name = "playlist_id")
    private Playlist parentNode;

    @OneToMany(mappedBy = "parentNode", cascade = CascadeType.REMOVE)
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
		return "PLAYLIST: " + name;
	}

}