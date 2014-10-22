package si.matjazcerkvenik.openmp3player.web;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class AddPlaylistBean {
	
	private SimpleLogger logger = OContext.getInstance().getLogger();
	
	private String name = null;
	private String source = null;
	
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSource() {
		return source;
	}



	public void setSource(String source) {
		this.source = source;
	}



	/**
	 * Action: go to playlist when goto button is pressed
	 * 
	 * @return home page
	 */
	public String addPlaylist() {
		
		logger.info("AddPlaylistBean:addPlaylist(): " + source);
		Mp3Player.getInstance().addPlaylist(name, source);
		return "playlists";
		
	}
}
