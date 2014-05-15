package si.matjazcerkvenik.openmp3player.backend;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Properties;

import si.matjazcerkvenik.simplelogger.LEVEL;
import si.matjazcerkvenik.simplelogger.PROPS;

public class Utils {
	
	private static Properties props = null;
	
	public static final String PROPERTY_PLAYER_DELAY = "player.delay";
	public static final String PROPERTY_VOLUME_DEFAULT = "player.volume.default";
	public static final String PROPERTY_VOLUME_CUSTOM_SCRIPT = "player.volume.custom.script";
	public static final String PROPERTY_TELNET_ENABLED = "telnet.enabled";
	public static final String PROPERTY_TELNET_PORT = "telnet.port";
	
	public static String LOGGER_FILENAME = null;
	public static int LOGGER_LEVEL = LEVEL.INFO;
	public static boolean LOGGER_APPEND = true;
	public static boolean LOGGER_VERBOSE = false;
	public static int LOGGER_MAX_FILE_SIZE = 1;
	public static int LOGGER_MAX_BACKUP_FILES = 2;
	public static String LOGGER_DATE_FORMAT = null;
	public static int PLAYER_DELAY = 5;
	public static String VOLUME_CUSTOM_SCRIPT = null;
	public static boolean TELNET_ENABLED = true;
	public static int TELNET_PORT = 4444;

	public static int CURRENT_VOLUME_LEVEL = 2;

	/**
	 * Read version.txt.
	 */
	public static void readVersion() {

		try {

			FileInputStream fis = new FileInputStream(Mng.HOME_DIR
					+ "config/version.txt");

			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));

			Mng.version = br.readLine();

			dis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Return type of operating system: WINDOWS, OSX, LINUX
	 * @return
	 */
	public static String getOsType() {
		String os = System.getProperty("os.name");
		
		if (os.equalsIgnoreCase("Mac os X")) {
			return "OSX";
		} else if (os.equalsIgnoreCase("Linux")) {
			return "LINUX";
		} else if (os.contains("Windows")) {
			return "WINDOWS";
		}
		
		return os;
	}

	/**
	 * Read openmp3player.properties and load parameters.
	 * @return properties
	 */
	public static Properties loadProperties() {
		
		if (props == null) {
			
			props = new Properties();
			
			try {

				props.load(new FileInputStream(Mng.HOME_DIR
						+ "config/openmp3player.properties"));

				PLAYER_DELAY = parseInt(props.getProperty(PROPERTY_PLAYER_DELAY), 5);
				CURRENT_VOLUME_LEVEL = parseInt(props.getProperty(PROPERTY_VOLUME_DEFAULT), 1);
				System.out.println("==== VOL: " + CURRENT_VOLUME_LEVEL);
				if (props.getProperty(PROPERTY_VOLUME_CUSTOM_SCRIPT) != null
						&& props.getProperty(PROPERTY_VOLUME_CUSTOM_SCRIPT).length() > 0) {
					VOLUME_CUSTOM_SCRIPT = props.getProperty(PROPERTY_VOLUME_CUSTOM_SCRIPT);
				}
				System.out.println("==== VOLUME_CUSTOM_SCRIPT: " + VOLUME_CUSTOM_SCRIPT);
				LOGGER_FILENAME = props.getProperty(PROPS.FILENAME, "openmp3player.log");
				LOGGER_LEVEL = getLogLevel(props.getProperty(PROPS.LEVEL, "INFO"));
				LOGGER_APPEND = parseBool(props.getProperty(PROPS.APPEND), true);
				LOGGER_VERBOSE = parseBool(props.getProperty(PROPS.VERBOSE), false);
				LOGGER_MAX_FILE_SIZE = parseInt(props.getProperty(PROPS.MAX_FILE_SIZE), 1);
				LOGGER_MAX_BACKUP_FILES = parseInt(props.getProperty(PROPS.MAX_BACKUP_FILES), 2);
				LOGGER_DATE_FORMAT = props.getProperty(PROPS.DATE_FORMAT, "yyyy.MM.dd hh:mm:ss:SSS");
				TELNET_ENABLED = parseBool(props.getProperty(PROPERTY_TELNET_ENABLED), true);
				TELNET_PORT = parseInt(props.getProperty(PROPERTY_TELNET_PORT), 4444);

			}

			catch (IOException e) {
				e.printStackTrace();
			}

		}
		return props;
	}
	
	public static void writeProperties() {
		try {
		    props.store(new FileOutputStream("src/my/project/properties/test.properties"), null);
		} catch (IOException e) {
			Mng.getLogger().error("Utils:writeProperties(): error writing properties", e);
		}
	}
	
	public static int getLogLevel(String s) {
		if (s.equalsIgnoreCase("trace")) {
			return LEVEL.TRACE;
		} else if (s.equalsIgnoreCase("debug")) {
			return LEVEL.DEBUG;
		} else if (s.equalsIgnoreCase("info")) {
			return LEVEL.INFO;
		} else if (s.equalsIgnoreCase("warn")) {
			return LEVEL.WARN;
		} else if (s.equalsIgnoreCase("error")) {
			return LEVEL.ERROR;
		} else if (s.equalsIgnoreCase("fatal")) {
			return LEVEL.FATAL;
		} else {
			return LEVEL.INFO;
		}
	}
	
	/**
	 * Parse String value to integer. If fails, return default value.
	 * @param val
	 * @param defaultValue
	 * @return int
	 */
	private static int parseInt(String val, int defaultValue) {
		
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		
	}
	
	/**
	 * Parse String value to boolean. If fails, return default value.
	 * @param val
	 * @param defaultValue
	 * @return boolean
	 */
	private static boolean parseBool(String val, boolean defaultValue) {
		
		try {
			return Boolean.parseBoolean(val);
		} catch (Exception e) {
			return defaultValue;
		}
		
	}
	
	/**
	 * Increase volume level
	 */
	public static void volumeUp() {
		
		// to change volume on os x:
		// osascript -e "set Volume 1"
		
		// on linux:
		// amixer set Master 10%
		
		if (CURRENT_VOLUME_LEVEL >= 10) {
			return;
		}
		CURRENT_VOLUME_LEVEL++;
		Mng.getLogger().info("Utils:volumeUp(): " + CURRENT_VOLUME_LEVEL);	
		
		setVolume(CURRENT_VOLUME_LEVEL);
		
	}
	
	/**
	 * Decrease volume level
	 */
	public static void volumeDown() {
		
		if (CURRENT_VOLUME_LEVEL <= 0) {
			return;
		}
		CURRENT_VOLUME_LEVEL--;
		Mng.getLogger().info("Utils:volumeDown(): " + CURRENT_VOLUME_LEVEL);	
		
		setVolume(CURRENT_VOLUME_LEVEL);
		
	}
	
	/**
	 * Set volume level in range from 0 to 10.
	 * @param level
	 */
	public static void setVolume(int level) {
		Mng.getLogger().info("Utils:setVolume(): " + level);	
		String[] vol = { "" + level };
		if (VOLUME_CUSTOM_SCRIPT != null) {
			runScript(VOLUME_CUSTOM_SCRIPT, vol);
		} else if (getOsType().equalsIgnoreCase("OSX")) {
			runScript("setVolumeOSX.sh", vol);
		} else if (getOsType().equalsIgnoreCase("LINUX")) {
			runScript("setVolumeLinux.sh", vol);
		} else if (getOsType().equalsIgnoreCase("WINDOWS")) {
			runScript("setVolume.bat", vol);
		}
		
		
	}
	
	/**
	 * Change permissions of .sh scripts in config directory to a+x.
	 */
	public static void changePermissions() {
		
		File dir = new File(Mng.HOME_DIR + "config");
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getAbsolutePath().endsWith(".sh");
			}
		});
		
		for (int i = 0; i < files.length; i++) {
			Mng.getLogger().info("Utils:changePermissions(): " + files[i].getAbsolutePath());	
			String[] cmd = {"chmod", "a+x", files[i].getAbsolutePath()};
			runConsoleCommand(cmd);
		}
		
	}
	
	public static void runScript(String script, String[] args) {
		
		String[] command = new String[args.length + 1];
		command[0] = Mng.HOME_DIR + "config/" + script;
		
		for (int i = 0; i < args.length; i++) {
			command[i+1] = args[i];
		}
		
		runConsoleCommand(command);
		
	}
	
	public static void runConsoleCommand(String[] command) {
		
		String cmdStr = "";
		for (int i = 0; i < command.length; i++) {
			cmdStr += command[i] + " ";
		}
		Mng.getLogger().info("Utils:runConsoleCommand(): " + cmdStr.trim());

	    Runtime rt = Runtime.getRuntime();
	    try {
	      Process p = rt.exec(command);

	      String s;

	      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	      BufferedReader errbr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	      while ((s = br.readLine()) != null) {
	    	  Mng.getLogger().info("Utils:runConsoleCommand(): response : " + s);
	      }
	      while ((s = errbr.readLine()) != null) {
	    	  Mng.getLogger().warn("Utils:runConsoleCommand(): errResponse : " + s);
	      }

	      // wait for ending command
	      try {
	        p.waitFor();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    
	  }
	
	/**
	 * Return IP address of the server, where OpenMp3Player is running.
	 * @return ip
	 */
	public static String getLocalIp() {
		try {
			return Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			Mng.getLogger().error("Utils:getLocalIp(): ", e);
		}
		return "unknown host";
	}

}
