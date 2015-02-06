package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.PlaylistDAO;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

@ManagedBean
@RequestScoped
public class PlaylistsBean {
	
	private SimpleLogger logger = null;
	
	private HtmlDataTable dataTable = null;
	
	@ManagedProperty(value="#{playerBean}")
	private PlayerBean playerBean;
	
	
	public PlaylistsBean() {
		logger = OContext.getInstance().getLogger();
	}
	
	
	public void setPlayerBean(PlayerBean playerBean) {
		this.playerBean = playerBean;
	}
	
	public List<Playlist> getPlaylistsList() {
		return PlaylistDAO.getInstance().getPlaylists().getPlist();
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
		playerBean.getMp3Player().setPassivePlaylist(p.getName());
		return "home";
		
	}
	
	
	
	/**
	 * Action: delete playlist when delete button is pressed
	 * @return home page
	 */
	public String deletePlaylist() {
		
		Playlist p = (Playlist) dataTable.getRowData();
		logger.info("PlaylistsBean:deletePlaylist(): " + p.getName());
		
		PlaylistDAO.getInstance().removePlaylist(p);
		
		return "playlists";
		
	}
	
}
