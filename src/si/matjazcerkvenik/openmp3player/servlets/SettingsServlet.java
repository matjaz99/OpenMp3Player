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

public class SettingsServlet extends HttpServlet {
	
	private static final long serialVersionUID = -74070043962764121L;
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
		logger.info("SettingsServlet:doPost(): button pressed: " + buttonPressed);
		
		PrintWriter out = resp.getWriter();
		
		if (buttonPressed == null) {

			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/settings.jsp");
			rd.forward(req, resp);
			
		} else if (buttonPressed.equals("modify")) {
			
			String i = req.getParameter("index");
			if (i.equals("port")) {
				
			} else {
				
			}
			
		} else if (buttonPressed.equals("prev")) {
			
			out.print(mng.prev());
			
		} else {
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/settings.jsp");
			rd.forward(req, resp);
			
		}
		
		
	}
	
}
