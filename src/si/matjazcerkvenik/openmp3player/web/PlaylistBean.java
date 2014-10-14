package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistBean {
	
	private SimpleLogger logger = null;
	
	private HtmlDataTable dataTable = null;
	
	public PlaylistBean() {
		
		logger = OContext.getInstance().getLogger();
		
	}
	
	public List<Mp3File> getMp3List() {
		return Mp3Player.getInstance().getPlaylist().getMp3Files();
	}
	
	public int getPlaylistSize() {
		return Mp3Player.getInstance().getPlaylist().getMp3Files().size();
	}
	
	public void play() {
		Mp3File mp3 = (Mp3File) dataTable.getRowData();
		logger.debug("PlaylistBean:play(): " + mp3.getIndex());
		Mp3Player.getInstance().play(mp3.getIndex());
	}
	
	public void putToQueue() {
		Mp3File mp3 = (Mp3File) dataTable.getRowData();
		logger.debug("PlaylistBean:putToQueue(): " + mp3.getIndex());
		// TODO
	}

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	
	
}
