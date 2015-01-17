package si.matjazcerkvenik.openmp3player.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.io.TagFactory;
import si.matjazcerkvenik.openmp3player.player.Tag;

public class AddTagBean {
	
	private String name = null;
	private String color = null;
	
	private HtmlDataTable availableTagsTable = null;
	
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
	
	public void deleteTag() {
		Tag t = (Tag) availableTagsTable.getRowData();
		OContext.getInstance().getLogger().info("AddTagBean:deleteTag(): delete " + t.getName());
		TagFactory.getInstance().deleteTag(t);
	}
	
	public List<Tag> getAllTags() {
		return TagFactory.getInstance().getTags().getTagList();
	}
	
	
	public HtmlDataTable getAvailableTagsTable() {
		return availableTagsTable;
	}
	public void setAvailableTagsTable(HtmlDataTable availableTagsTable) {
		this.availableTagsTable = availableTagsTable;
	}
	
	
	public List<SelectItem> getAllColors() {
		
		List<String> cList = Colors.getAvailableColors();
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		
		for (int i = 0; i < cList.size(); i++) {
			String s = cList.get(i);
			list.add(new SelectItem(s, s));
		}
		
		return list;
		
	}
	
	
	
}
