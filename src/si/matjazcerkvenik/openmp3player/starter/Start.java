package si.matjazcerkvenik.openmp3player.starter;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Start extends JFrame {

	private static final long serialVersionUID = -94309834027528490L;

	private JLabel lblTitle = new JLabel("OpenMp3Player");
	private JButton btnStartStop = new JButton("Start server");

	private JPanel pnlControls = new JPanel();

	private String[] startServerCommand = { "./server/apache-tomcat-7.0.57/bin/startup" };
	private String[] stopServerCommand = { "./server/apache-tomcat-7.0.57/bin/shutdown" };
	
	
	
	public Start() {
		
		super("Starter");
		pnlControls.setLayout(new GridLayout(2, 1));
		pnlControls.add(lblTitle);
		if (runningFileExist()) btnStartStop.setText("Stop server");
		pnlControls.add(btnStartStop);

		add(pnlControls);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		btnStartStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				startStopTomcat();
			}
		});
		
		
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
	
	

	private void startStopTomcat() {
		if (runningFileExist()) {
			runConsoleCommand(stopServerCommand);
			removeRunningFile();
			btnStartStop.setText("Start server");
		} else {
			runConsoleCommand(startServerCommand);
			createRunningFile();
			btnStartStop.setText("Stop server");
		}
	}
	
	
	
	private void createRunningFile() {
		File f = new File("config/running.tmp");
		try {
			f.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean runningFileExist() {
		File f = new File("config/running.tmp");
		return f.exists();
	}
	
	private void removeRunningFile() {
		File f = new File("config/running.tmp");
		f.delete();
	}
	
	

	private void runConsoleCommand(String[] command) {

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
	
	
	
	public static void main(String[] args) {
		
		Start gui = new Start();
		gui.pack();
		gui.setVisible(true);
		
	}

}
