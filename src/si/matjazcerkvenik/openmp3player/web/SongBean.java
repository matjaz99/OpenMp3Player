package si.matjazcerkvenik.openmp3player.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.ID3Tag;
import si.matjazcerkvenik.openmp3player.io.TagFactory;
import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.openmp3player.player.Tag;
import si.matjazcerkvenik.openmp3player.player.Tags;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class SongBean {
	
	private SimpleLogger logger = null;
	
	private Mp3File mp3File = null;
	
	private String selectedTag = null;
	
	public SongBean() {
		
		logger = OContext.getInstance().getLogger();
		
//		Map<String, String> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		String s = (String) requestParameterMap.get("id");
		
//		Map<String, Object> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//		Mp3File m = (Mp3File) requestParameterMap.get("id");
//		
//		int id = m.getIndex(); //Integer.parseInt(s);
//		
//		logger.info("SongBean: id=" + id);
//		
//		mp3File = Mp3Player.getInstance().getMp3(id);
//		
//		mp3File = ID3Tag.getMetadata(mp3File);
	}
	

	public Mp3File getMp3File() {
		Map<String, Object> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Mp3File m = (Mp3File) requestParameterMap.get("id");
		
		int id = m.getIndex(); //Integer.parseInt(s);
		
		logger.info("SongBean:getMp3File: id=" + id);
		
		mp3File = Mp3Player.getInstance().getMp3(id);
		
		mp3File = ID3Tag.getMetadata(mp3File);
		return mp3File;
	}

	public void setMp3File(Mp3File mp3File) {
		this.mp3File = mp3File;
	}
	
	public List<SelectItem> getTags() {
		
		Tags tags = TagFactory.getInstance().getTags();
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		if (tags.getTags() == null) {
			return list;
		}
		
		for (int i = 0; i < tags.getTags().size(); i++) {
			String name = tags.getTags().get(i).getName();
			list.add(new SelectItem(name, name));
		}
		
		return list;
		
	}
	
	public void tagSelected(ValueChangeEvent e) {
		
		selectedTag = e.getNewValue().toString();
		logger.info("SongBean:tagSelected(): event - selected tag: " + selectedTag);
		Tag t = TagFactory.getInstance().getTag(selectedTag);
		mp3File = getMp3File();
		mp3File.addTag(t);
		
	}
	
	
	public String getSelectedTag() {
		if (selectedTag == null) {
			selectedTag = "";
		}
		return selectedTag;
	}


	public void setSelectedTag(String selectedTag) {
		this.selectedTag = selectedTag;
	}


	public String getTagsAsString() {
		String s = "";
		if (mp3File.getTags() == null) {
			return s;
		}
		for (int i = 0; i < mp3File.getTags().getTags().size(); i++) {
			s += mp3File.getTags().getTags().get(i);
			if (i < mp3File.getTags().getTags().size()) {
				s += ", ";
			}
		}
		return s;
	}
	
}
