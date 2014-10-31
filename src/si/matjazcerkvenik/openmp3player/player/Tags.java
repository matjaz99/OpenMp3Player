package si.matjazcerkvenik.openmp3player.player;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tags {
	
	private List<Tag> tags = null;
	
	public void addTag(Tag t) {
		if (tags == null) {
			tags = new ArrayList<Tag>();
		}
		tags.add(t);
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	
}
