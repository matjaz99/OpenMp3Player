package si.matjazcerkvenik.openmp3player.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import si.matjazcerkvenik.openmp3player.backend.Mng;

public class AjaxServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2768554033041873518L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("email:" + req.getParameter("email")); 
		
		PrintWriter out = resp.getWriter();
		Mng mng = new Mng();
		out.println(mng.getCurrentlyPlaying());
		
	}
	
}
