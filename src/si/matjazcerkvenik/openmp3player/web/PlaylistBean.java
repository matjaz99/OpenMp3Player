package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import javax.faces.component.html.HtmlDataTable;

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
		return Mp3Player.getInstance().getActivePlaylist().getMp3files().getFiles();
	}
	
	public int getPlaylistSize() {
		return Mp3Player.getInstance().getActivePlaylist().getMp3files().getFiles().size();
	}
	
	public void play() {
		Mp3File mp3 = (Mp3File) dataTable.getRowData();
		logger.debug("PlaylistBean:play(): " + mp3.getIndex());
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
	
	
}
