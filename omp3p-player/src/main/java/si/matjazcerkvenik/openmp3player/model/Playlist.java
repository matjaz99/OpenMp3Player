package si.matjazcerkvenik.openmp3player.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Table(name = "playlist", schema="OMP3P")
public class Playlist {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "name", unique = false, nullable = false, length = 64)
	private String name;
	
	@Column(name = "source_dir", unique = false, nullable = false)
	private String sourceDirectory;
	
	

    @OneToMany(/*mappedBy = "playlist", */cascade = CascadeType.ALL, orphanRemoval = true/*, targetEntity=Mp3File.class, fetch=FetchType.LAZY*/)
	@JoinColumn(name = "playlist_id")
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
	
	public void addMp3File(Mp3File mp3File) {
		if (mp3files == null) {
//			mp3files = new HashSet<Mp3File>();
			mp3files = new ArrayList<Mp3File>();
		}
		mp3files.add(mp3File);
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