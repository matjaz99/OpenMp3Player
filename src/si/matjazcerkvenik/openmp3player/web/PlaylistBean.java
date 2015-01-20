package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistBean {
	
	private SimpleLogger logger = null;
	
	private HtmlDataTable dataTable = null;
	
	public PlaylistBean() {
		logger = OContext.getInstance().getLogger();
	}
	
	public List<Mp3File> getMp3List() {
		return Mp3Player.getInstance().getPassivePlaylist().getMp3files().getFiles();
	}
	
	public int getPlaylistSize() {
		return Mp3Player.getInstance().getPassivePlaylist().getMp3files().getFiles().size();
	}
	
	public boolean isQueue() {
		return Mp3Player.getInstance().getPassivePlaylist().isQueue();
	}
	
	public String getActivePlaylistName() {
		return Mp3Player.getInstance().getActivePlaylist().getName();
	}
	
	public void play() {
		Mp3File mp3 = (Mp3File) dataTable.getRowData();
		logger.debug("PlaylistBean:play(): " + mp3.getIndex());
		Mp3Player.getInstance().setPassiveToActive();
		Mp3Player.getInstance().play(mp3.getIndex());
	}
	
	public void putToQueue() {
		Mp3File mp3 = (Mp3File) dataTable.getRowData();
		logger.info("PlaylistBean:putToQueue(): " + mp3.getIndex());
		Mp3Player.getInstance().putToQueue(mp3);
	}

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	
	public String showSongDetails() {
		Mp3File mp3 = (Mp3File) dataTable.getRowData();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id", mp3);
		return "song";
	}
	
	public void removeMp3FromTheList() {
		Mp3File mp3 = (Mp3File) dataTable.getRowData();
		logger.info("PlaylistBean:removeMp3FromTheList(): " + mp3.getIndex());
		Mp3Player.getInstance().removeMp3FromPassiveList(mp3);
	}
	
	/**
	 * Return coma-separated list of row classes.
	 * @return
	 */
	public String getBackgroundColorsArray() {
		
		StringBuilder rowClasses = new StringBuilder();
		List<Mp3File> list = getMp3List();

	    for (int i = 0; i < list.size(); i++) {
	    	Mp3File m = list.get(i);
	    	if (m.getBackgroundColor() != null && m.getBackgroundColor().length() > 0) {
	    		rowClasses.append("bgColor-" + m.getBackgroundColor());
			} else {
				
				if (i % 2 == 0) {
					rowClasses.append("table-even-row");
				} else {
					rowClasses.append("table-odd-row");
				}
				
			}
	        if (i < list.size() - 1) rowClasses.append(",");
	    }

	    return rowClasses.toString();
		
	}
	
	
}
