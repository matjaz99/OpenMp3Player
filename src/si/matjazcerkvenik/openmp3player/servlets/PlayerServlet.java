package si.matjazcerkvenik.openmp3player.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import si.matjazcerkvenik.openmp3player.backend.Mng;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class PlayerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7994198694638279881L;
	private Mng mng = null;
	private SimpleLogger logger = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Mng.HOME_DIR = getServletContext().getRealPath("/");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String buttonPressed = request.getParameter("button");
		if (buttonPressed != null) buttonPressed = buttonPressed.trim();
		logger.info("PlayerServlet:doPost(): button pressed: " + buttonPressed);
		
		PrintWriter out = response.getWriter();
		
		if (buttonPressed == null) {

			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
			rd.forward(request, response);
			
		} else if (buttonPressed.equals("prev")) {
			
			out.print(mng.prev());
			
		} else if (buttonPressed.equals("play")) {
			
			mng.getPlistMng().resetActivePlaylist();
			
			String i = request.getParameter("index");
			if (i == null) {
				out.print(mng.play(0));
			} else {
				out.print(mng.play(Integer.parseInt(i)));
			}
			
		} else if (buttonPressed.equals("next")) {
			
			out.print(mng.next());
			
		} else if (buttonPressed.equals("stop")) {
			
			mng.getPlistMng().resetActivePlaylist();
			out.print(mng.stop());
			
		} else if (buttonPressed.equals("dropdownmenu")) {
			
			String sel = request.getParameter("selPlist");
			logger.info("PlayerServlet:doPost(): selected: " + sel);
			mng.getPlistMng().setShowPlaylist(sel);
			mng.getPlistMng().loadMp3Files();
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
			rd.forward(request, response);
			
		} else if (buttonPressed.equals("refresh")) {
			
			out.print(mng.getCurrentlyPlaying());
			
		} else if (buttonPressed.equals("playlistEditor")) {
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/playlistEditor");
			rd.forward(request, response);
			
		} else if (buttonPressed.equals("queue")) {
			
			int i = Integer.parseInt(request.getParameter("index"));
			mng.getPlistMng().addToQueue(i);
			
		} else {
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
			rd.forward(request, response);
			
		}
		
	}
	
}
