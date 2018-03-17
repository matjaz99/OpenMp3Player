package si.matjazcerkvenik.openmp3player.starter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {

	public static String[] startServerCommandX = { "./server/apache-tomcat-7.0.57/bin/startup.sh" };
	public static String[] stopServerCommandX = { "./server/apache-tomcat-7.0.57/bin/shutdown.sh" };
	
	public static String[] startServerCommandWin = { "cmd.exe", "/c", /*"@echo", "off", "&",*/ "set", "\"CATALINA_HOME=%cd%/server/apache-tomcat-7.0.57\"", "&", "START", "/B", "call", "server/apache-tomcat-7.0.57/bin/startup.bat" };
	public static String[] stopServerCommandWin = { "cmd.exe", "/c", "set", "\"CATALINA_HOME=%cd%/server/apache-tomcat-7.0.57\"", "&", "START", "/B", "call", "server/apache-tomcat-7.0.57/bin/shutdown.bat" };
	
	public static String version = "0.0.0";


	public static void main(String[] args) {

		// String[] cmd = { "cmd.exe", "/c", "echo", "hello" };
		// runConsoleCommand(cmd);

		init();

		// cli mode: start/stop/status as args, no gui
		if (args.length > 0) {

			if (args[0].equalsIgnoreCase("start")) {

				if (!isServerRunning()) {
					startServer();
				}
				System.out.println("Started");

			} else if (args[0].equalsIgnoreCase("stop")) {

				if (isServerRunning()) {
					stopServer();
				}
				System.out.println("Stopped");

			} else if (args[0].equalsIgnoreCase("status")) {

				if (isServerRunning()) {
					System.out.println("Stopped");
				} else {
					System.out.println("Started");
				}

			}

		} else {
			Gui gui = new Gui();
			gui.pack();
			gui.setVisible(true);
		}

	}

	
	/**
	 * Change file permissions of .sh files to 755 (Linux and OS X only)
	 */
	public static void init() {
		
		readVersion();

		if (getOsType().endsWith("X")) {
			String[] cmd1 = { "find", ".", "-name", "*.sh", "-exec", "chmod",
					"755", "{}", "+" };
			Executor e = new Executor();
			e.execute(cmd1, false);
		}

	}

	
	/**
	 * Execute script to start the server. On X systems .sh script is run, 
	 * on windows .bat script is run.
	 */
	public static void startServer() {

		Executor e = new Executor();

		if (getOsType().endsWith("X")) {
			e.execute(startServerCommandX, false);
		} else {
			e.execute(startServerCommandWin, true);
		}
		createRunningFile();

	}

	
	/**
	 * Execute script to stop the server. On X systems .sh script is run, 
	 * on windows .bat script is run.
	 */
	public static void stopServer() {

		Executor e = new Executor();

		if (getOsType().endsWith("X")) {
			e.execute(stopServerCommandX, false);
		} else {
			e.execute(stopServerCommandWin, true);
		}
		removeRunningFile();

	}

	
	/**
	 * When server is started create dummy running.tmp file.
	 */
	public static void createRunningFile() {
		File f = new File("config/running.tmp");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Return true if running.tmp file exists (indicating that server is running).
	 */
	public static boolean isServerRunning() {
		File f = new File("config/running.tmp");
		return f.exists();
	}

	
	/**
	 * When server is stopped delete running.tmp file.
	 */
	public static void removeRunningFile() {
		File f = new File("config/running.tmp");
		f.delete();
	}

	
	/**
	 * Return type of operating system: OSX, LINUX or WINDOWS.
	 * @return osType
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

		return "X";
	}

	
	/**
	 * Read version.txt file
	 */
	private static void readVersion() {

		String ver = "0.0.0";

		try {

			FileInputStream fis = new FileInputStream("config/version.txt");

			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));

			ver = br.readLine();

			dis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		version = ver;

	}
	

}
