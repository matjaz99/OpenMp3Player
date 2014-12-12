package si.matjazcerkvenik.openmp3player.web.comp;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

public class SingleTagTag extends UIComponentELTag {
	
	private ValueExpression value = null;
	
	public void setValue(ValueExpression value) {
		this.value = value;
	}
	

	@Override
	public String getComponentType() {
		return "omp3p.singleTagComponent";
	}
	
	@Override
	public String getRendererType() {
		return null;
	}
	
	@Override
	protected void setProperties(UIComponent comp) {
		super.setProperties(comp);
		processProperty(comp, value, "value");
	}
	
	private void processProperty(UIComponent comp, ValueExpression prop, String propName) {
		if (prop != null) {
			if (prop.isLiteralText()) {
				comp.getAttributes().put(propName, prop.getExpressionString());
			} else {
				comp.setValueExpression(propName, prop);
			}
		}
	}
	
	@Override
	public void release() {
		super.release();
		value = null;
	}
	
}