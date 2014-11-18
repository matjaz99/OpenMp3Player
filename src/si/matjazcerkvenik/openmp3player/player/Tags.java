package si.matjazcerkvenik.openmp3player.player;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tags {
	
	private List<Tag> tagList = new ArrayList<Tag>();
	
	public void addTag(Tag t) {
		tagList.add(t);
	}

	public List<Tag> getTagList() {
		return tagList;
	}

	@XmlElement(name="tag")
	public void setTagList(List<Tag> tags) {
		this.tagList = tags;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < tagList.size(); i++) {
			s += tagList.get(i);
			if (i < tagList.size() - 1) {
				s += ", ";
			}
		}
		return s;
	}
	
	
}
