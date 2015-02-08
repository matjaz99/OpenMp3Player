package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.PlaylistDAO;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

@ManagedBean
@SessionScoped
public class PlaylistsBean {
	
	private SimpleLogger logger = null;
	
//	private HtmlDataTable dataTable = null;
	
	@ManagedProperty(value="#{playerBean}")
	private PlayerBean playerBean;
	
	@ManagedProperty(value="#{playlistBean}")
	private PlaylistBean playlistBean;
	
	
	public PlaylistsBean() {
		logger = OContext.getInstance().getLogger();
	}
	
	
	public void setPlayerBean(PlayerBean playerBean) {
		this.playerBean = playerBean;
	}
	
	public void setPlaylistBean(PlaylistBean playlistBean) {
		this.playlistBean = playlistBean;
	}


	public List<Playlist> getPlaylistsList() {
		return PlaylistDAO.getInstance().getPlaylists().getPlist();
	}
	
//	public HtmlDataTable getDataTable() {
//		return dataTable;
//	}

//	public void setDataTable(HtmlDataTable dataTable) {
//		this.dataTable = dataTable;
//	}
	
	/**
	 * Action: go to playlist when goto button is pressed
	 * @return home page
	 */
	public String gotoPlaylist(Playlist p) {
//		Playlist p = (Playlist) dataTable.getRowData();
		logger.info("PlaylistsBean:gotoPlaylist(): " + p.getName());
		playlistBean.setPassivePlaylist(p);
		return "home";
	}
	
	
	
	/**
	 * Action: delete playlist when delete button is pressed
	 * @return home page
	 */
	public String deletePlaylist(Playlist p) {
//		Playlist p = (Playlist) dataTable.getRowData();
		logger.info("PlaylistsBean:deletePlaylist(): " + p.getName());
		PlaylistDAO.getInstance().removePlaylist(p);
		return "playlists";
	}
	
}
