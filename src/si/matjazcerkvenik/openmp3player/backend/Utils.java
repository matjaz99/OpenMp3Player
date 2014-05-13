package si.matjazcerkvenik.openmp3player.backend;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class Utils {

	private static Properties props = null;
	
	public static String LOGGER_FILENAME = null;
	public static String LOGGER_LEVEL = null;
	public static boolean LOGGER_APPEND = true;
	public static boolean LOGGER_VERBOSE = false;
	public static int LOGGER_MAX_FILE_SIZE = 1;
	public static int LOGGER_MAX_BACKUP_FILES = 2;
	public static String LOGGER_DATE_FORMAT = null;
	public static int PLAYER_DELAY = 5;
	public static boolean TELNET_ENABLED = true;
	public static int TELNET_PORT = 4444;


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
	 * Read openmp3player.properties and load parameters.
	 * @return properties
	 */
	public static Properties getProperties() {
		
		if (props == null) {
			
			props = new Properties();
			
			try {

				props.load(new FileInputStream(Mng.HOME_DIR
						+ "config/openmp3player.properties"));

				PLAYER_DELAY = parseInt(props.getProperty("player.delay"), 5);
				LOGGER_FILENAME = props.getProperty("simplelogger.filename", "openmp3player.log");
				LOGGER_LEVEL = props.getProperty("simplelogger.level", "INFO");
				LOGGER_APPEND = parseBool(props.getProperty("simplelogger.append"), true);
				LOGGER_VERBOSE = parseBool(props.getProperty("simplelogger.verbose"), false);
				LOGGER_MAX_FILE_SIZE = parseInt(props.getProperty("simplelogger.maxFileSize"), 1);
				LOGGER_MAX_BACKUP_FILES = parseInt(props.getProperty("simplelogger.maxBackupFiles"), 2);
				LOGGER_DATE_FORMAT = props.getProperty("simplelogger.dateFormat", "yyyy.MM.dd hh:mm:ss:SSS");
				TELNET_ENABLED = parseBool(props.getProperty("telnet.enabled"), true);
				TELNET_PORT = parseInt(props.getProperty("telnet.port"), 4444);

			}

			catch (IOException e) {
				e.printStackTrace();
			}

		}
		return props;
	}
	
	private static int parseInt(String val, int defaultValue) {
		
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		
	}
	
	private static boolean parseBool(String val, boolean defaultValue) {
		
//		if (val.equalsIgnoreCase("true")) {
//			return true;
//		} else if (val.equalsIgnoreCase("false")) {
//			return false;
//		}
//		return defaultValue;
		
		try {
			return Boolean.parseBoolean(val);
		} catch (Exception e) {
			return defaultValue;
		}
		
	}

}
