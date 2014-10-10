package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;

public class PlaylistBean {
	
	public PlaylistBean() {
	}
	
	public List<Mp3File> getMp3List() {
		return Mp3Player.getInstance().getPlaylist().getMp3Files();
	}
	
	public int getPlaylistSize() {
		return Mp3Player.getInstance().getPlaylist().getMp3Files().size();
	}
	
	public Playlist getActivePlaylist() {
		return Mp3Player.getInstance().getPlaylist();
	}
	
}
