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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "mp3file")
public class Mp3File implements Cloneable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "hash")
	private String hash;
	
	@Column(name = "path")
	private String path;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "artist")
	private String artist;
	
	@Column(name = "album")
	private String album;
	
	@Column(name = "genre")
	private String genre;
	
	@Column(name = "yearReleased")
	private String yearReleased;
	
	@Column(name = "size")
	private int size = 0;
	
//	@OneToMany(cascade=CascadeType.ALL, targetEntity=Tag.class, fetch=FetchType.EAGER)
//	private List<Tag> tags = null;
	
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name = "playlist_id"/*, nullable = false*/)
    private Playlist playlist;
	
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

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getYearReleased() {
		return yearReleased;
	}

	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

//	public List<Tag> getTags() {
//		return tags;
//	}
//
//	public void setTags(List<Tag> tags) {
//		this.tags = tags;
//	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	@Override
	public String toString() {
		return "Mp3File [id=" + id + ", hash=" + hash + ", path=" + path + ", title=" + title + ", artist=" + artist
				+ ", album=" + album + ", genre=" + genre + ", yearReleased=" + yearReleased + ", size=" + size
				+ ", playlist=" + playlist + "]";
	}

	

}
