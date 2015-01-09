package si.matjazcerkvenik.openmp3player.web.comp;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class IconTag extends TagSupport {
	
	private static final long serialVersionUID = 6588586968873084535L;
	
	private String img;
	
	@Override
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
//			out.println("<h:graphicImage "
//					+ "url=\"img/" + img + ".png\" "
//					+ "styleClass=\"icon\" "
//					+ "onmouseover=\"onMouse(this, 'img/" + img + "-shadow.png')\" "
//					+ "onmouseout=\"onMouse(this, 'img/" + img + ".png')\" "
//					+ "onmousedown=\"onMouse(this, 'img/" + img + "-pressed.png')\" " 
//					+ "onmouseup=\"onMouse(this, 'img/" + img + "-shadow.png')\" />");
			out.println("<img "
					+ "src=\"img/" + img + ".png\" "
					+ "class=\"icon\" "
					+ "onmouseover=\"onMouse(this, 'img/" + img + "-shadow.png')\" "
					+ "onmouseout=\"onMouse(this, 'img/" + img + ".png')\" "
					+ "onmousedown=\"onMouse(this, 'img/" + img + "-pressed.png')\" " 
					+ "onmouseup=\"onMouse(this, 'img/" + img + "-shadow.png')\" />");
		} catch (IOException e) {
			throw new JspTagException(e.toString());
		}
		return SKIP_BODY;
	}
	
	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
