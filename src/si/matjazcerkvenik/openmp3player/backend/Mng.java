package si.matjazcerkvenik.openmp3player.backend;

import si.matjazcerkvenik.openmp3player.cli.CommandLine;
import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.openmp3player.player.jlayer.JLayerPlayer;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class Mng {
	
	// TODO change volume on os x
	// osascript -e "set Volume 1"
	
	private static IPlayer player = null;
	private static PlistMng plistMng = null;
	public static Mp3File currentlyPlaying = null;
	private static Watchdog watchdog = null;
	private static CommandLine cli = null;
	
	public static String HOME_DIR = null;
	public static String version = "0.0";
	
	private static SimpleLogger logger = null;
	
	public Mng() {
		initialize();
	}
	public static int count = 0;
	private void initialize() {
		System.out.println("Mng initializing... " + count++);
		if (plistMng == null) {
			
			Utils.getProperties();
			
			initializeLogger();
			
			plistMng = new PlistMng();
			plistMng.loadMp3Files();
			
			player = new JLayerPlayer();
			
			watchdog = new Watchdog(this);
			watchdog.start();
			
			Utils.readVersion();
			
			if (Utils.TELNET_ENABLED) {
				cli = new CommandLine();
				cli.start();
			} else {
				logger.info("CLI disabled");
			}
			
			logger.info("Mng initialized");
		}
		
	}
	
	private void initializeLogger() {
		logger = new SimpleLogger();
		logger.setFilename(HOME_DIR + "log/" + Utils.LOGGER_FILENAME);
		logger.setAppend(Utils.LOGGER_APPEND);
		logger.setVerbose(true);
		logger.info("");
		logger.info("\t+---------------------------------+");
		logger.info("\t|       Start OpenMp3Player       |");
		logger.info("\t+---------------------------------+");
		logger.info("");
		logger.info("HOME_DIR=" + HOME_DIR);
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
	public static IPlayer getPlayer() {
		return player;
	}
	
	public static SimpleLogger getLogger() {
		return logger;
	}
	
	
	/**
	 * Return title of currently playing song.
	 * @return title
	 */
	public String getCurrentlyPlaying() {
		if (currentlyPlaying == null) {
			return "null";
		}
		return currentlyPlaying.getTitle();
	}
	
	
	/**
	 * Start playing song with index i
	 * @param i
	 * @return title
	 */
	public String play(int i) {
		
		if (currentlyPlaying != null) {
			stop();
		}
		
		currentlyPlaying = plistMng.getActivePlaylist().getMp3Files().get(i);
		logger.info("play: playlist: " + plistMng.getActivePlaylist().getName() 
				+ ", MP3: [" + currentlyPlaying.getIndex() + "] " + currentlyPlaying.getFile());
		player.play(currentlyPlaying.getPath());
		
		return currentlyPlaying.getTitle();
		
	}
	
	/**
	 * Stop playing song
	 * @return 'null'
	 */
	public String stop() {
		if (currentlyPlaying == null) {
			return "null";
		}
		
		logger.info("stop: playlist: " + plistMng.getActivePlaylist().getName() 
				+ ", MP3: [" + currentlyPlaying.getIndex() + "] " + currentlyPlaying.getFile());
		player.stop();
		currentlyPlaying = null;
		
		return "null";
	}
	
	/**
	 * Play next song
	 * @return title
	 */
	public String next() {
		if (currentlyPlaying == null) {
			return "null";
		}
		
		if (currentlyPlaying.getIndex() 
				== plistMng.getActivePlaylist().getMp3Files().size() - 1) {
			play(0);
		} else {
			play(currentlyPlaying.getIndex() + 1);
		}
		return currentlyPlaying.getTitle();
	}
	
	/**
	 * Play previous song
	 * @return title
	 */
	public String prev() {
		if (currentlyPlaying == null) {
			return "null";
		}
		
		if (currentlyPlaying.getIndex() == 0) {
			play(plistMng.getActivePlaylist().getMp3Files().size() - 1);
		} else {
			play(currentlyPlaying.getIndex() - 1);
		}
		return currentlyPlaying.getTitle();
	}
	
	/**
	 * Return true if song is currently playing
	 * @return true if playing
	 */
	public boolean isPlaying() {
		return currentlyPlaying != null;
	}
	
	
}
