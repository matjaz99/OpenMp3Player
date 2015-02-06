package si.matjazcerkvenik.openmp3player.web.components;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

import si.matjazcerkvenik.openmp3player.backend.TagsDAO;
import si.matjazcerkvenik.openmp3player.player.Tag;
import si.matjazcerkvenik.openmp3player.player.Tags;
import si.matjazcerkvenik.openmp3player.resources.Colors;

@FacesRenderer(componentFamily = "omp3p.multiTag", rendererType = "omp3p.MultiTagRenderer")
public class MultiTagRenderer extends Renderer {
	
	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		
		super.encodeBegin(context, component);
		
		ResponseWriter rw = context.getResponseWriter();
		
		Tags tags = (Tags) component.getAttributes().get("value");
				
		rw.startElement("div", component);
		rw.writeAttribute("style", "float: right;", null);
		
		for (int i = 0; i < tags.getTagList().size(); i++) {
			
			Tag tag = tags.getTagList().get(i);
			
			// use color from tags.xml if exists
			Tag origTag = TagsDAO.getInstance().getTag(tag.getName());
			String tagColor = "Black";
			if (origTag != null) {
				tagColor = TagsDAO.getInstance().getTag(tag.getName()).getColor();
			}
			String txtColor = Colors.getTagTextColor(tagColor);
			
			rw.startElement("div", component);
			rw.writeAttribute("class", "tagBorder", null);
			rw.writeAttribute("style", "background-color: " + tagColor + "; 	color: " + txtColor + "; float: right;", null);
			rw.write(tag.getName());
			rw.endElement("div");
		}
		
		rw.endElement("div");
		
		rw.startElement("div", component);
		rw.writeAttribute("style", "clear: both;", null);
		rw.endElement("div");
		
	}
	
	
}
