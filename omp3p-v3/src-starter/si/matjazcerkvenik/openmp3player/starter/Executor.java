package si.matjazcerkvenik.openmp3player.starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is used to execute system command line commands.
 * 
 * @author matjaz
 *
 */
public class Executor extends Thread {

	private String[] command = null;

	/**
	 * Execute command on the system. If the command blocks it will never 
	 * return from the method, then set runInThread=true and the command will be executed 
	 * in new thread. For example: starting Tomcat on Windows blocks this method.
	 * @param cmd
	 * @param runInThread
	 */
	public void execute(String[] cmd, boolean runInThread) {

		command = cmd;

		String cmdStr = "";
		for (int i = 0; i < command.length; i++) {
			cmdStr += command[i] + " ";
		}

		if (runInThread) {
			System.out.println("execute(t): " + cmdStr.trim());
			start();
		} else {
			System.out.println("execute(): " + cmdStr.trim());
			runConsoleCommand();
		}

	}

	@Override
	public void run() {

		runConsoleCommand();

	}

	/**
	 * Execute the command.
	 */
	private void runConsoleCommand() {

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

}
