package si.matjazcerkvenik.openmp3player.web.comp;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import si.matjazcerkvenik.openmp3player.player.Tags;

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
			
			rw.startElement("div", component);
			rw.writeAttribute("class", "tagBorder", null);
			rw.writeAttribute("style", "background-color: " + tags.getTagList().get(i).getColor() + "; 	color: white; float: right;", null);
			rw.write(tags.getTagList().get(i).getName());
			rw.endElement("div");
		}
		
		rw.endElement("div");
		
		rw.startElement("div", component);
		rw.writeAttribute("style", "clear: both;", null);
		rw.endElement("div");
		
	}
	
	
}
