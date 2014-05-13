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

public class PlayerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7994198694638279881L;
	private Mng mng = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Mng.HOME_DIR = getServletContext().getRealPath("/");
		if (mng == null) {
			mng = new Mng();
		}
		System.out.println("INIT(): HOME_DIR=" + Mng.HOME_DIR);
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
		System.out.println("Button pressed: " + buttonPressed);
		
		PrintWriter out = response.getWriter();
		
		if (buttonPressed == null) {

			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
			rd.forward(request, response);
			
		} else if (buttonPressed.equals("prev")) {
			
			out.print(mng.prev());
			
//			ServletContext sc = getServletContext();
//			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
//			rd.forward(request, response);
			
		} else if (buttonPressed.equals("play")) {
			
			String i = request.getParameter("index");
			if (i == null) {
				out.print(mng.play(0));
			} else {
				out.print(mng.play(Integer.parseInt(i)));
			}
			
			
//			ServletContext sc = getServletContext();
//			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
//			rd.forward(request, response);
			
		} else if (buttonPressed.equals("next")) {
			
			out.print(mng.next());
			
//			ServletContext sc = getServletContext();
//			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
//			rd.forward(request, response);
			
		} else if (buttonPressed.equals("stop")) {
			
			out.print(mng.stop());
			
//			ServletContext sc = getServletContext();
//			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
//			rd.forward(request, response);
			
		} else if (buttonPressed.equals("dropdownmenu")) {
			
			String seldir = request.getParameter("selDir");
			System.out.println("SELECT_DIR: " + seldir);
			mng.setActivePlaylist(seldir);
			mng.loadMp3Files();
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
			rd.forward(request, response);
			
		} else if (buttonPressed.equals("refresh")) {
			
			out.print(mng.getCurrentlyPlaying());
			
		} else {
			
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/WEB-INF/player.jsp");
			rd.forward(request, response);
			
		}
		
	}
	
}
