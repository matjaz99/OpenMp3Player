package si.matjazcerkvenik.openmp3player.web;

import java.util.Map;

import javax.faces.context.FacesContext;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.ID3Tag;
import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class SongBean {
	
	private SimpleLogger logger = null;
	
	private Mp3File mp3File = null;
	
	public SongBean() {
		
		logger = OContext.getInstance().getLogger();
		
		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String s = requestParameterMap.get("id");
		int id = Integer.parseInt(s);
		
		logger.info("SongBean: id=" + id);
		
		mp3File = Mp3Player.getInstance().getMp3(id);
		
		mp3File = ID3Tag.getMetadata(mp3File);
	}
	

	public Mp3File getMp3File() {
		return mp3File;
	}

	public void setMp3File(Mp3File mp3File) {
		this.mp3File = mp3File;
	}
	
	
	
}
