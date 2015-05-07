package si.matjazcerkvenik.openmp3player.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import si.matjazcerkvenik.openmp3player.backend.DAO;
import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.player.Mp3Files;
import si.matjazcerkvenik.openmp3player.player.Playlist;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

@ManagedBean
@SessionScoped
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



	
	public String addPlaylist() {
		
		logger.info("AddPlaylistBean:addPlaylist(): " + source);
		Playlist p = new Playlist();
		p.setName(name);
		p.setOrigin(source);
		p.setSource(name + ".xml");
		
		Mp3Files m = new Mp3Files();
		m.setFiles(DAO.getInstance().loadFromDirectory(source));
		p.setMp3files(m);
		
		DAO.getInstance().addPlaylist(p);
		return "playlists";
		
	}
}
