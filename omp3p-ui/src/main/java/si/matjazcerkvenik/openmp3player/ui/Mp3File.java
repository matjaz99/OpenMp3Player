package si.matjazcerkvenik.openmp3player.ui;

public class Mp3File implements Cloneable {
	
	private int id;
	private String hash;
	private String path;
	private String title;
	private String artist;
	private String album;
	private String genre;
	private String yearReleased;
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
