package si.matjazcerkvenik.openmp3player.starter;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Start {
	
	public static String[] startServerCommand = { "./server/apache-tomcat-7.0.57/bin/startup" };
	public static String[] stopServerCommand = { "./server/apache-tomcat-7.0.57/bin/shutdown" };
	
	
	public Start() {
		
		if (getOsType().endsWith("X")) {
			String[] cmd1 = {"find", ".", "-name", "*.sh", "-exec", "chmod", "755", "{}", "+"};
			runConsoleCommand(cmd1);
			startServerCommand[0] += ".sh";
			stopServerCommand[0] += ".sh";
		} else {
			startServerCommand[0] += ".bat";
			stopServerCommand[0] += ".bat";
		}

	}
	
	
	
	public static void main(String[] args) {
		
		// cli mode: start/stop/status as args, no gui
		if (args.length > 0) {
			
			if (args[0].equalsIgnoreCase("start")) {
				
				if (!runningFileExist()) {
					runConsoleCommand(startServerCommand);
					createRunningFile();
				}
				System.out.println("Started");
				
			} else if (args[0].equalsIgnoreCase("stop")) {
				
				if (runningFileExist()) {
					runConsoleCommand(stopServerCommand);
					removeRunningFile();
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
	
	

	public static void runConsoleCommand(String[] command) {

		String cmdStr = "";
		for (int i = 0; i < command.length; i++) {
			cmdStr += command[i] + " ";
		}
		System.out.println("runConsoleCommand(): " + cmdStr.trim());

		Runtime rt = Runtime.getRuntime();
		try {
			Process p = rt.exec(command);

			String s;

			BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			BufferedReader errbr = new BufferedReader(new InputStreamReader(
					p.getErrorStream()));
			while ((s = br.readLine()) != null) {
				System.out.println("runConsoleCommand(): response : " + s);
			}
			while ((s = errbr.readLine()) != null) {
				System.out.println("runConsoleCommand(): errResponse : " + s);
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
