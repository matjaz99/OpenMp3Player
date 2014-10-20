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
	
	public String showPlaylist() {
		
		Playlist p = (Playlist) dataTable.getRowData();
		logger.info("PlaylistsBean:showPlaylist(): " + p.getSource());
		Mp3Player.getInstance().setActivePlaylist(p.getSource());
		return "home";
		
	}
	
	public String deletePlaylist() {
		
		Playlist p = (Playlist) dataTable.getRowData();
		logger.info("PlaylistsBean:deletePlaylist(): " + p.getName());
		Mp3Player.getInstance().deletePlaylist(p);
		return "home";
		
	}
	
}
