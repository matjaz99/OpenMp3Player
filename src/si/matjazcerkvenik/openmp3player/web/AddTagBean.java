package si.matjazcerkvenik.openmp3player.web;

import java.util.List;

import si.matjazcerkvenik.openmp3player.io.TagFactory;
import si.matjazcerkvenik.openmp3player.player.Tag;

public class AddTagBean {
	
	private String name = null;
	private String color = null;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	public void addNew() {
		
		Tag t = new Tag();
		t.setName(name);
		t.setColor(color);
		
		TagFactory.getInstance().getTags().addTag(t);
		
		TagFactory.getInstance().saveTags();
		
		name = null;
		color = null;
		
	}
	
	public void removeTag() {
		// TODO
	}
	
	public List<Tag> getAllTags() {
		return TagFactory.getInstance().getTags().getTags();
	}
	
	
}
