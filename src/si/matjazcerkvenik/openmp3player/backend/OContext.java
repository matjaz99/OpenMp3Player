package si.matjazcerkvenik.openmp3player.backend;

import si.matjazcerkvenik.openmp3player.cli.CommandLine;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;
import si.matjazcerkvenik.openmp3player.player.Watchdog;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class OContext {
	
	private static OContext ctx = null;
	
	
	private CommandLine cli = null;
	public static boolean repeatSong = false;
	
	public static String HOME_DIR = null;
	public static String CFG_DIR = null;
	public static String LOG_DIR = null;
	public static String PLAYLISTS_DIR = null;
	public static String version = "0.0";
	
	private SimpleLogger logger = null;
	private static boolean isInitialized = false;
	
	private OContext() {
		initialize();
	}
	
	/**
	 * Get instance of OContext (singleton).
	 * @return ctx
	 */
	public static synchronized OContext getInstance() {
		if (!isInitialized) {
			isInitialized = true;
			if (ctx == null) {
				ctx = new OContext();
			}
		}
		
		return ctx;
	}
	
	/**
	 * Initialize application - load properties, init logger, 
	 * set volume...<br>
	 * Set VM arg: -Domp3p.home="/Users/matjaz/Documents/git/OpenMp3Player"
	 */
	private void initialize() {
		
		HOME_DIR = getHomeDir();
		CFG_DIR = HOME_DIR + "/config";
		LOG_DIR = HOME_DIR + "/log";
		PLAYLISTS_DIR = HOME_DIR + "/playlists";
		
		Utils.init();
		
		logger = new SimpleLogger();
		logger.setFilename(LOG_DIR + "/" + Utils.LOGGER_FILENAME);
		logger.setLogLevel(Utils.LOGGER_LEVEL);
		logger.setAppend(Utils.LOGGER_APPEND);
		logger.setVerbose(true);
		logger.info("");
		logger.info("\t+---------------------------------+");
		logger.info("\t|       Start OpenMp3Player       |");
		logger.info("\t+---------------------------------+");
		logger.info("");
		logger.info("HOME_DIR=" + HOME_DIR);
		logger.info("LOGGER_FILE=" + LOG_DIR + "/" + Utils.LOGGER_FILENAME);
		logger.info("LOGGER_VERBOSE=" + Utils.LOGGER_VERBOSE);
		logger.info("VERSION=" + version);
		logger.info("OS=" + Utils.getOsType());
		logger.info("VOLUME_CUSTOM_SCRIPT=" + Utils.VOLUME_CUSTOM_SCRIPT);
//		logger.debug("Request Context Path: " + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
//		logger.debug("Request Path Info: " + FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
//		logger.debug("Request Sevlet Path: " + FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath());
		
//		Utils.changePermissions();
//		Utils.setVolume(Utils.CURRENT_VOLUME_LEVEL);
		
		logger.info("OContext initialized");
		
	}
	
	public SimpleLogger getLogger() {
		return logger;
	}
	
	public Watchdog getWatchdog() {
//		return watchdog;
		return null;
	}
	
	private static String getHomeDir() {
		
		String homeDir = System.getProperty("omp3p.home");
		
		// if -Domp3p.home VM arg is not set, use ../server/ directory as default
		if (homeDir == null || homeDir.length() == 0) {
			String[] temp = System.getProperty("catalina.home").split("server");
			homeDir = temp[0];
		}
		if (homeDir.endsWith("/") || homeDir.endsWith("\\")) {
			homeDir = homeDir.substring(0, homeDir.length()-1);
		}
		
		return homeDir;
	}
	
	/**
	 * Start command line interface
	 */
	public void startCli(Mp3Player player) {
		if (Utils.TELNET_ENABLED) {
			logger.info("TELNET=enabled on port " + Utils.TELNET_PORT);
			cli = new CommandLine(player);
			cli.start();
		} else {
			logger.info("TELNET=disabled");
		}
	}
	
	
}
