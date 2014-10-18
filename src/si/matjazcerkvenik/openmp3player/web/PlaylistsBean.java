package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistsBean {
	
	private SimpleLogger logger = null;
	
	private HtmlDataTable dataTable = null;
	
	public PlaylistsBean() {
		logger = OContext.getInstance().getLogger();
	}
	
	public List<Playlist> getPlaylistsList() {
		return Mp3Player.getInstance().getPlaylists().getPlist();
	}
	
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	
}
