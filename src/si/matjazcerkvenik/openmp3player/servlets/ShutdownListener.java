package si.matjazcerkvenik.openmp3player.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ShutdownListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ShutdownListener:contextInitialized()");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ShutdownListener:contextDestroyed()");
	}
	
}
