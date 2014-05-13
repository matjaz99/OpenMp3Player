package si.matjazcerkvenik.openmp3player.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import si.matjazcerkvenik.openmp3player.backend.Mng;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlaylistEditorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 8129113818186588525L;
	
	private Mng mng = null;
	private SimpleLogger logger = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		if (mng == null) {
			mng = new Mng();
		}
		logger = Mng.getLogger();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String buttonPressed = req.getParameter("button");
		if (buttonPressed != null) buttonPressed = buttonPressed.trim();
		logger.info("PlaylistEditorServlet:Button pressed: " + buttonPressed);
		
		if (buttonPressed == null) {

			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/playlistEditor.jsp");
			rd.forward(req, resp);
			
		} else if (buttonPressed.equals("add")) {
			
			String name = req.getParameter("name");
			String source = req.getParameter("source");
			
			mng.getPlistMng().addPlaylist(name, source);
			
		} else if (buttonPressed.equals("remove")) {
			
			String pname = req.getParameter("name");
			if (mng.getPlistMng().getActivePlaylist().getName().equals(pname)) {
				mng.stop();
			}
			mng.getPlistMng().removePlaylist(pname);
			
		} else if (buttonPressed.equals("savequeue")) {
			
			String pname = req.getParameter("name");
			mng.getPlistMng().saveQueue(pname);
			
			if (mng.getPlistMng().getActivePlaylist().getName().equals("Queue")) {
				mng.stop();
			}
			mng.getPlistMng().emptyQueue();
			
		} else if (buttonPressed.equals("emptyqueue")) {
			
			if (mng.getPlistMng().getActivePlaylist().getName().equals("Queue")) {
				mng.stop();
			}
			mng.getPlistMng().emptyQueue();
			
		} else {
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/playlistEditor.jsp");
			rd.forward(req, resp);
			
		}
		
	}
	
}
