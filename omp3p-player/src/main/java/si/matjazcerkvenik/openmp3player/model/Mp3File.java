package si.matjazcerkvenik.openmp3player.model;

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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "mp3file", schema="OMP3P")
@ApiModel(description = "This is model of mp3 file with ID3 tags")
public class Mp3File implements Cloneable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	@ApiModelProperty(notes = "Unique identifier", example = "0", required = false, position = 0)
	private int id;
	
	@Column(name = "hash")
	@ApiModelProperty(notes = "Unique hashcode", example = "0", required = false, position = 1)
	private String hash;
	
	@Column(name = "path")
	@ApiModelProperty(notes = "Absolute path to file", example = "/music/MySong.mp3", required = true, position = 2)
	private String path;
	
	@Column(name = "filename")
	@ApiModelProperty(notes = "File name", example = "MySong.mp3", required = false, position = 3)
	private String filename;
	
	@Column(name = "title")
	@ApiModelProperty(notes = "Song title", example = "Lucy in the sky with diamonds", required = false, position = 4)
	private String title;
	
	@Column(name = "artist")
	@ApiModelProperty(notes = "Artist or band name", example = "The beatles", required = false, position = 5)
	private String artist;
	
	@Column(name = "album")
	@ApiModelProperty(notes = "Album name", example = "/music/MySong.mp3", required = false, position = 6)
	private String album;
	
	@Column(name = "genre")
	@ApiModelProperty(notes = "Genre", example = "Rock", required = false, position = 7)
	private String genre;
	
	@Column(name = "yearReleased")
	@ApiModelProperty(notes = "Year released", example = "1967", required = false, position = 8)
	private String yearReleased;
	
	@Column(name = "size")
	@ApiModelProperty(notes = "Size", required = false, position = 9)
	private int size = 0;
	
//	@OneToMany(cascade=CascadeType.ALL, targetEntity=Tag.class, fetch=FetchType.EAGER)
//	private List<Tag> tags = null;
	
//	@ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name = "playlist_id"/*, nullable = false*/)
//    private Playlist playlist;
	
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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

//	public Playlist getPlaylist() {
//		return playlist;
//	}
//
//	public void setPlaylist(Playlist playlist) {
//		this.playlist = playlist;
//	}

	@Override
	public String toString() {
		return "Mp3File [id=" + id + ", hash=" + hash + ", path=" + path + ", title=" + title + ", artist=" + artist
				+ ", album=" + album + ", genre=" + genre + ", yearReleased=" + yearReleased + ", size=" + size
				+ ", playlist=" + "playlist" + "]";
	}

	

}
