package si.matjazcerkvenik.openmp3player.backend;

public class OContext {
	
	private static OContext ctx = null;
	
	private OContext() {
		
	}
	
	public static OContext getInstance() {
		if (ctx == null) {
			ctx = new OContext();
		}
		return ctx;
	}
	
}
