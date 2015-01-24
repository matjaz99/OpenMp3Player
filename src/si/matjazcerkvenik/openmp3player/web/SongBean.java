package si.matjazcerkvenik.openmp3player.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.Digester;
import si.matjazcerkvenik.openmp3player.io.ID3Tag;
import si.matjazcerkvenik.openmp3player.io.PlaylistFactory;
import si.matjazcerkvenik.openmp3player.io.TagFactory;
import si.matjazcerkvenik.openmp3player.player.Mp3File;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.openmp3player.player.Tag;
import si.matjazcerkvenik.openmp3player.player.Tags;
import si.matjazcerkvenik.openmp3player.resources.Colors;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

@ManagedBean
@RequestScoped
public class SongBean {
	
	private SimpleLogger logger = null;
	
	private Mp3File mp3File = null;
	
	private String selectedTag = "- Select tag -";
	private String selectedBackgroundColor = null;
	
	private HtmlDataTable tagDataTable = null;
	
	public SongBean() {
		logger = OContext.getInstance().getLogger();
	}
	

	/**
	 * Get selected mp3file from the session
	 * @return mp3File
	 */
	public Mp3File getMp3File() {
		
		Map<String, Object> requestParameterMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		Mp3File m = (Mp3File) requestParameterMap.get("id");
		
		int id = m.getIndex();
		logger.info("SongBean:getMp3File: id=" + id);
		
		mp3File = Mp3Player.getInstance().getMp3(id);
		String hash = Digester.getSha1(mp3File.getPath());
		if (!mp3File.getHash().equals(hash)) {
			mp3File.setHash(hash);
			mp3File = ID3Tag.getMetadata(mp3File);
			PlaylistFactory.getInstance().savePassivePlaylist();
		}
		return mp3File;
		
	}

	public void setMp3File(Mp3File mp3File) {
		this.mp3File = mp3File;
	}
	
	/**
	 * Get tags for dropdown menu
	 * @return list
	 */
	public List<SelectItem> getTagItems() {
		
		Tags tags = TagFactory.getInstance().getTags();
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem("- Select tag -", "- Select tag -"));
		
		if (tags.getTagList() == null) {
			return list;
		}
		
		for (int i = 0; i < tags.getTagList().size(); i++) {
			String name = tags.getTagList().get(i).getName();
			list.add(new SelectItem(name, name));
		}
		
		return list;
		
	}
	
	/**
	 * Event: new tag selected in dropdown menu
	 * @param e
	 */
	public void tagSelected(ValueChangeEvent e) {
		
		selectedTag = e.getNewValue().toString();
		logger.info("SongBean:tagSelected(): event - selected tag: " + selectedTag);
		if (selectedTag.equals("- Select tag -")) {
			return;
		}
		Tag t = TagFactory.getInstance().getTag(selectedTag);
		mp3File = getMp3File();
		mp3File.addTag(t);
		PlaylistFactory.getInstance().savePassivePlaylist();
		
		selectedTag = "- Select tag -";
				
	}
	
	
	public String getSelectedTag() {
		if (selectedTag == null) {
			selectedTag = "- Select tag -";
		}
		return selectedTag;
	}


	public void setSelectedTag(String selectedTag) {
		this.selectedTag = selectedTag;
	}


	public String getTagsAsString() {
		mp3File = getMp3File();
		String s = "";
		if (mp3File.getTags() == null) {
			return s;
		}
		for (int i = 0; i < mp3File.getTags().getTagList().size(); i++) {
			s += mp3File.getTags().getTagList().get(i);
			if (i < mp3File.getTags().getTagList().size() - 1) {
				s += ", ";
			}
		}
		return s;
	}


	public HtmlDataTable getTagDataTable() {
		return tagDataTable;
	}


	public void setTagDataTable(HtmlDataTable tagDataTable) {
		this.tagDataTable = tagDataTable;
	}
	
	public List<Tag> getTagList() {
		return getMp3File().getTags().getTagList();
	}
	
	/**
	 * Remove tag from the song
	 */
	public void removeTag() {
		Tag t = (Tag) tagDataTable.getRowData();
		Mp3File m = getMp3File();
		m.getTags().removeTag(t);
		PlaylistFactory.getInstance().savePassivePlaylist();
	}


	public String getSelectedBackgroundColor() {
		if (selectedBackgroundColor == null) {
			selectedBackgroundColor = getMp3File().getBackgroundColor();
		}
		return selectedBackgroundColor;
	}


	public void setSelectedBackgroundColor(String selectedBackgroundColor) {
		this.selectedBackgroundColor = selectedBackgroundColor;
	}
	
	/**
	 * Get background colors for dropdown menu
	 * @return list
	 */
	public List<SelectItem> getBackgroundColorItems() {
		
		List<String> bgColorList = Colors.getAvailableBackgroundColors();
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem("- Select color -", "- Select color -"));
		
		for (int i = 0; i < bgColorList.size(); i++) {
			String name = bgColorList.get(i);
			list.add(new SelectItem(name, name));
		}
		
		return list;
		
	}
	
	/**
	 * Event: new tag selected in dropdown menu
	 * @param e
	 */
	public void backgroundColorSelected(ValueChangeEvent e) {
		
		selectedBackgroundColor = e.getNewValue().toString();
		logger.info("SongBean:backgroundColorSelected(): event - selected color: " + selectedBackgroundColor);
		if (selectedBackgroundColor.equals("- Select color -")) {
			return;
		}
		mp3File = getMp3File();
		mp3File.setBackgroundColor(selectedBackgroundColor);
		PlaylistFactory.getInstance().savePassivePlaylist();
				
	}
	
	public void removeBackgroundColor() {
		
		Mp3File m = getMp3File();
		m.setBackgroundColor(null);
		PlaylistFactory.getInstance().savePassivePlaylist();
		
	}
	
}
