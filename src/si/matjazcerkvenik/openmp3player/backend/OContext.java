package si.matjazcerkvenik.openmp3player.backend;

import javax.faces.context.FacesContext;

import si.matjazcerkvenik.openmp3player.cli.CommandLine;
import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class OContext {
	
	private static OContext ctx = null;
	
//	private IPlayer player = null;
	private PlistMng plistMng = null;
//	public Mp3File currentlyPlaying = null;
	private Watchdog watchdog = null;
	private CommandLine cli = null;
	public static boolean repeatSong = false;
	
//	public static String HOME_DIR = "~/OpenMp3Player/";
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
		
		System.out.println("Request Context Path: " + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
		System.out.println("Request Path Info: " + FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo());
		System.out.println("Request Sevlet Path: " + FacesContext.getCurrentInstance().getExternalContext().getRequestServletPath());
		
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
		
				
//		plistMng = new PlistMng();
//		plistMng.loadMp3Files();
//		
//		player = new JLayerPlayer();
		
//		watchdog = new Watchdog(this);
//		watchdog.start();
		
//		
//		Utils.changePermissions();
//		Utils.setVolume(Utils.CURRENT_VOLUME_LEVEL);
		
//		if (Utils.TELNET_ENABLED) {
//			cli = new CommandLine();
//			cli.start();
//		} else {
//			logger.info("CLI disabled");
//		}
		
//		logger.info("Mng initialized");
		
	}
	
	
	
	/**
	 * Return playlist manager.
	 * @return
	 */
	public PlistMng getPlistMng() {
		return plistMng;
	}
	
	/**
	 * Return player object
	 * @return player
	 */
//	public IPlayer getPlayer() {
//		return player;
//	}
	
	public SimpleLogger getLogger() {
		return logger;
	}
	
//	public static void setLogger(SimpleLogger logger) {
//		OContext.logger = logger;
//	}
	
//	/**
//	 * Return title of currently playing song.
//	 * @return title
//	 */
//	public String getCurrentlyPlaying() {
//		if (currentlyPlaying == null) {
//			return "null";
//		}
//		return currentlyPlaying.getTitle();
//	}
//	
//	
//	/**
//	 * Start playing song with index i
//	 * @param i
//	 * @return title of the song
//	 */
//	public String play(int i) {
//		
//		if (currentlyPlaying != null) {
//			stop();
//		}
//		
//		currentlyPlaying = plistMng.getActivePlaylist().getMp3Files().get(i);
//		logger.info("play: playlist: " + plistMng.getActivePlaylist().getName() 
//				+ ", MP3: [" + currentlyPlaying.getIndex() + "] " + currentlyPlaying.getFile());
//		player.play(currentlyPlaying.getPath());
//		
//		return currentlyPlaying.getTitle();
//		
//	}
//	
//	/**
//	 * Stop playing song
//	 * @return 'null'
//	 */
//	public String stop() {
//		if (currentlyPlaying == null) {
//			return "null";
//		}
//		
//		logger.info("stop: playlist: " + plistMng.getActivePlaylist().getName() 
//				+ ", MP3: [" + currentlyPlaying.getIndex() + "] " + currentlyPlaying.getFile());
//		player.stop();
//		currentlyPlaying = null;
//		
//		return "null";
//	}
//	
//	/**
//	 * Play next song
//	 * @return title
//	 */
//	public String next() {
//		if (currentlyPlaying == null) {
//			return "null";
//		}
//		
//		if (currentlyPlaying.getIndex() 
//				== plistMng.getActivePlaylist().getMp3Files().size() - 1) {
//			play(0);
//		} else {
//			play(currentlyPlaying.getIndex() + 1);
//		}
//		return currentlyPlaying.getTitle();
//	}
//	
//	/**
//	 * Play previous song
//	 * @return title
//	 */
//	public String prev() {
//		if (currentlyPlaying == null) {
//			return "null";
//		}
//		
//		if (currentlyPlaying.getIndex() == 0) {
//			play(plistMng.getActivePlaylist().getMp3Files().size() - 1);
//		} else {
//			play(currentlyPlaying.getIndex() - 1);
//		}
//		return currentlyPlaying.getTitle();
//	}
//	
//	/**
//	 * Return true if song is currently playing
//	 * @return true if playing
//	 */
//	public boolean isPlaying() {
//		return currentlyPlaying != null;
//	}
	
}
