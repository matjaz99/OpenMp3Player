package si.matjazcerkvenik.openmp3player.backend;

import javax.faces.context.FacesContext;

import si.matjazcerkvenik.openmp3player.cli.CommandLine;
import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class OContext {
	
	private static OContext ctx = null;
	
	private Watchdog watchdog = null;
	private CommandLine cli = null;
	public static boolean repeatSong = false;
	
	public static String CFG_DIR = "/Users/matjaz/OpenMp3Player/";
	public static String version = "0.0";
	
	private SimpleLogger logger = null;
	
	private OContext() {
		initialize();
	}
	
	/**
	 * Get instance of OContext (singleton).
	 * @return ctx
	 */
	public static OContext getInstance() {
		if (ctx == null) {
			ctx = new OContext();
		}
		return ctx;
	}
	
	/**
	 * Initialize application - load properties, init logger, read playlists, 
	 * set volume, start CLI server...
	 */
	private void initialize() {
		
//		System.out.println("Request Context Path: " + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
//		System.out.println("Request Path Info: " + FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
//		System.out.println("Request Sevlet Path: " + FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath());
		
		Utils.init();
		
		logger = new SimpleLogger();
		logger.setFilename(OContext.CFG_DIR + "log/" + Utils.LOGGER_FILENAME);
		logger.setLogLevel(Utils.LOGGER_LEVEL);
		logger.setAppend(Utils.LOGGER_APPEND);
		logger.setVerbose(true);
		logger.info("");
		logger.info("\t+---------------------------------+");
		logger.info("\t|       Start OpenMp3Player       |");
		logger.info("\t+---------------------------------+");
		logger.info("");
		logger.info("CFG_DIR=" + OContext.CFG_DIR);
		logger.info("Version=" + version);
		logger.info("OS=" + System.getProperty("os.name"));
		
		
		watchdog = new Watchdog(this);
		watchdog.start();
		
//		
//		Utils.changePermissions();
//		Utils.setVolume(Utils.CURRENT_VOLUME_LEVEL);
		
//		if (Utils.TELNET_ENABLED) {
//			cli = new CommandLine();
//			cli.start();
//		} else {
//			logger.info("CLI disabled");
//		}
		
		logger.info("OContext initialized");
		
	}
	
	public SimpleLogger getLogger() {
		return logger;
	}
	
	
}
