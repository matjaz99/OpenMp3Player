package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.PlaylistFactory;
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
		return PlaylistFactory.getInstance().getPlaylists().getPlist();
	}
	
	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	
	/**
	 * Action: go to playlist when goto button is pressed
	 * @return home page
	 */
	public String gotoPlaylist() {
		
		Playlist p = (Playlist) dataTable.getRowData();
		logger.info("PlaylistsBean:gotoPlaylist(): " + p.getName());
		Mp3Player.getInstance().setPassivePlaylist(p.getName());
		return "home";
		
	}
	
	
	
	/**
	 * Action: delete playlist when delete button is pressed
	 * @return home page
	 */
	public String deletePlaylist() {
		
		Playlist p = (Playlist) dataTable.getRowData();
		logger.info("PlaylistsBean:deletePlaylist(): " + p.getName());
		
		if (p.getName().equals("Queue")) {
			Mp3Player.getInstance().emptyQueue();
		} else {
			Mp3Player.getInstance().deletePlaylist(p);
		}
		
		return "playlists";
		
	}
	
}
