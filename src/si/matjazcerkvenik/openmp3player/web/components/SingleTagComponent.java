package si.matjazcerkvenik.openmp3player.web.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import si.matjazcerkvenik.openmp3player.io.TagsDAO;
import si.matjazcerkvenik.openmp3player.player.Tag;
import si.matjazcerkvenik.openmp3player.resources.Colors;

@FacesComponent(value="SingleTag")
public class SingleTagComponent extends UIComponentBase {
	
	@Override
	public void encodeBegin(FacesContext ctx) throws IOException {
		super.encodeBegin(ctx);
				
		ResponseWriter rw = ctx.getResponseWriter();
		
		Tag tag = (Tag) getAttributes().get("value");
		
		// use color from tags.xml if exists
		Tag origTag = TagsDAO.getInstance().getTag(tag.getName());
		String tagColor = "Black";
		if (origTag != null) {
			tagColor = TagsDAO.getInstance().getTag(tag.getName()).getColor();
		}
		String txtColor = Colors.getTagTextColor(tagColor);
		
		rw.startElement("div", this);
		rw.writeAttribute("class", "tagBorder", null);
		rw.writeAttribute("style", "background-color: " + tagColor + "; color: " + txtColor + ";", null);
		rw.write(tag.getName());
		rw.endElement("div");
		
	}
	
	@Override
	public String getFamily() {
		return "omp3p.singleTag";
	}

}
