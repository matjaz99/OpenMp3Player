package si.matjazcerkvenik.openmp3player.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import si.matjazcerkvenik.openmp3player.backend.OContext;

public class ShutdownListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		OContext.getInstance();
		System.out.println("ShutdownListener:contextInitialized()");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ShutdownListener:contextDestroyed()");
//		Mp3Player.getInstance().stop();
		OContext.getInstance().getWatchdog().interrupt();
		OContext.getInstance().getLogger().close();
	}
	
}
