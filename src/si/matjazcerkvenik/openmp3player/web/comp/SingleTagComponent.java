package si.matjazcerkvenik.openmp3player.web.comp;

import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import si.matjazcerkvenik.openmp3player.player.Tag;

public class SingleTagComponent extends UIComponentBase {
	
	@Override
	public void encodeBegin(FacesContext ctx) throws IOException {
		super.encodeBegin(ctx);
		
		ResponseWriter rw = ctx.getResponseWriter();
		
		Tag tag = (Tag) getAttributes().get("value");
		
		rw.startElement("div", this);
		rw.writeAttribute("class", "tagBorder", null);
		rw.writeAttribute("style", "background-color: " + tag.getColor() + "; color: white;", null);
		rw.write(tag.getName());
		rw.endElement("div");
		
	}
	
	@Override
	public String getFamily() {
		return "omp3p.singleTag";
	}

}
