package si.matjazcerkvenik.openmp3player.io;

import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.backend.Playlists;

public interface IFileFinder {
	
	@Deprecated
	public List<Mp3File> getMp3Files(String source);
	
	public Playlists getPlaylists();
	
	public Playlist getPlaylist(String source);
	
}
