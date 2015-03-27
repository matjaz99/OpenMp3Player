package si.matjazcerkvenik.openmp3player.web.components;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import si.matjazcerkvenik.openmp3player.player.Tag;
import si.matjazcerkvenik.openmp3player.resources.Colors;

@FacesComponent(value="SingleTag")
public class SingleTagComponent extends UIComponentBase {
	
	@Override
	public void encodeBegin(FacesContext ctx) throws IOException {
		super.encodeBegin(ctx);
				
		ResponseWriter rw = ctx.getResponseWriter();
		
		Tag t = (Tag) getAttributes().get("value");
		
//		// use color from tags.xml if exists
//		Tag tag = TagsDAO.getInstance().getTag(t.getName());
//		String tagColor = "Black";
//		if (tag != null) {
//			String c = tag.getColor();
//			tagColor = Colors.getTagColors().get(c);
//		}
//		String txtColor = Colors.getTagTextColor(tagColor);
		
		
		String tagColor = Colors.getTagColors().get(t.getColor());
		if (tagColor == null) tagColor = "Black";
		String txtColor = Colors.getTagTextColor(tagColor);
		
		rw.startElement("div", this);
		rw.writeAttribute("class", "tagBorder", null);
		rw.writeAttribute("style", "background-color: " + tagColor + "; color: " + txtColor + ";", null);
		rw.write(t.getName());
		rw.endElement("div");
		
	}
	
	@Override
	public String getFamily() {
		return "omp3p.singleTag";
	}

}
