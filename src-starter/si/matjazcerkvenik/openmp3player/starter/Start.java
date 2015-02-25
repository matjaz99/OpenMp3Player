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


	public static void main(String[] args) {

		// String[] cmd = { "cmd.exe", "/c", "echo", "hello" };
		// runConsoleCommand(cmd);

		init();

		// cli mode: start/stop/status as args, no gui
		if (args.length > 0) {

			if (args[0].equalsIgnoreCase("start")) {

				if (!runningFileExist()) {
					startServer();
				}
				System.out.println("Started");

			} else if (args[0].equalsIgnoreCase("stop")) {

				if (runningFileExist()) {
					stopServer();
				}
				System.out.println("Stopped");

			} else if (args[0].equalsIgnoreCase("status")) {

				if (runningFileExist()) {
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

		if (getOsType().endsWith("X")) {
			String[] cmd1 = { "find", ".", "-name", "*.sh", "-exec", "chmod",
					"755", "{}", "+" };
			Executor e = new Executor();
			e.execute(cmd1, false);
		}

	}

	public static void startServer() {

		Executor e = new Executor();

		if (getOsType().endsWith("X")) {
			e.execute(startServerCommandX, false);
		} else {
			e.execute(startServerCommandWin, true);
		}
		createRunningFile();

	}

	public static void stopServer() {

		Executor e = new Executor();

		if (getOsType().endsWith("X")) {
			e.execute(stopServerCommandX, false);
		} else {
			e.execute(stopServerCommandWin, true);
		}
		removeRunningFile();

	}

	public static void createRunningFile() {
		File f = new File("config/running.tmp");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean runningFileExist() {
		File f = new File("config/running.tmp");
		return f.exists();
	}

	public static void removeRunningFile() {
		File f = new File("config/running.tmp");
		f.delete();
	}

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

	public static String readVersion() {

		String ver = "0.0";

		try {

			FileInputStream fis = new FileInputStream("config/version.txt");

			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));

			ver = br.readLine();

			dis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ver;

	}

}
