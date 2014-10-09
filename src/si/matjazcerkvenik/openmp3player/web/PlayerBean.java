package si.matjazcerkvenik.openmp3player.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Playlists;
import si.matjazcerkvenik.openmp3player.io.PlaylistFactory;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlayerBean {
	
	private String selectedPlaylist = null;
	
	private HtmlCommandLink playButton = null;
	private HtmlGraphicImage playButtonImg = null;
	
	private SimpleLogger logger = null;
	
	public PlayerBean() {
		logger = OContext.getInstance().getLogger();
	}
	
	private PlaylistBean getPlaylistBean() {
		return (PlaylistBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("playlistBean");
	}
	
	/**
	 * Get playlists for dropdown menu
	 * @return map
	 */
	public Map<String, String> getPlaylists() {
		
		Playlists playlists = Mp3Player.getInstance().getPlaylists();
		
		Map<String, String> map = new LinkedHashMap<String, String>();
		
		if (playlists.getPlist() == null) {
			return map;
		}
		
		for (int i = 0; i < playlists.getPlist().size(); i++) {
			String s = playlists.getPlist().get(i).getName();
			String ss = playlists.getPlist().get(i).getSource();
			map.put(s, ss);
		}
		
		return map;
	}
	
	/**
	 * This method is fired when another playlist is selected from dropdown menu.
	 * @param e
	 */
	public void playlistChanged(ValueChangeEvent e) {
		selectedPlaylist = e.getNewValue().toString();
		logger.info("PlayerBean:playlistChanged(): event - selected playlist: " + selectedPlaylist);
		Mp3Player.getInstance().setPlaylist(selectedPlaylist);
	}

	
	/**
	 * Get default value for playlists dropdown menu.
	 * @return
	 */
//	public String getInitialPlaylistSource() {
//		return getPlaylistBean().getActivePlaylist().getSource();
//	}
	
	


	
	/**
	 * Return title of currently playing song.
	 * @return title
	 */
	public String getCurrentlyPlaying() {
		return Mp3Player.getInstance().getCurrentlyPlaying();
	}
	
	
	
//	public HtmlGraphicImage getPlayButtonImg() {
//		
//		if (playButtonImg == null) {
//			playButtonImg = new HtmlGraphicImage();
//		}
//		
//		if (isPlaying()) {
//			playButtonImg.setUrl("img/stop.png");
//		} else {
//			playButtonImg.setUrl("img/play.png");
//		}
//		
//		return playButtonImg;
//	}

//	public void setPlayButtonImg(HtmlGraphicImage playButtonImg) {
//		this.playButtonImg = playButtonImg;
//	}
//
//	public HtmlCommandLink getPlayButton() {
//		if (isPlaying()) {
//			
//			HtmlGraphicImage image = (HtmlGraphicImage) playButton.getChildren().get(0);
//			image.setUrl("img/stop.png");
//			
//		} else {
//			
//			HtmlGraphicImage image = (HtmlGraphicImage) playButton.getChildren().get(0);
//			image.setUrl("img/play.png");
//			
//		}
//		return playButton;
//	}
//
//	public void setPlayButton(HtmlCommandLink playButton) {
//		this.playButton = playButton;
//	}
	
	
	
	
	
	/**
	 * Start playing
	 * @return title of the song
	 */
	public String play() {
		return Mp3Player.getInstance().play(0);
	}
	
	/**
	 * Stop playing
	 * @return 'null'
	 */
	public String stop() {
		Mp3Player.getInstance().stop();
		return "null";
	}
	
	/**
	 * Play next
	 * @return title
	 */
	public String next() {
		return Mp3Player.getInstance().next();
	}
	
	/**
	 * Play previous
	 * @return title
	 */
	public String prev() {
		return Mp3Player.getInstance().prev();
	}
	
	/**
	 * Return true if song is currently playing
	 * @return true if playing
	 */
	public boolean isPlaying() {
		return Mp3Player.getInstance().isPlaying();
	}
	
	
	public String getVersion() {
		return OContext.version;
	}
	
}
