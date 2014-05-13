package si.matjazcerkvenik.openmp3player.cli;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import si.matjazcerkvenik.openmp3player.backend.Mng;
import si.matjazcerkvenik.openmp3player.backend.Utils;
import si.matjazcerkvenik.simplelogger.SimpleLogger;

public class CommandLine extends Thread {

	private ServerSocket s;
	private Socket incoming;
	private boolean running = true;
	private static final String prompt = "> ";
	private SimpleLogger logger = null;
	private Mng mng = null;

	public CommandLine() {
		mng = new Mng();
		logger = Mng.getLogger();
		setName("CommandLine");
	}

	@Override
	public void run() {

		while (running) {

			try {
				s = new ServerSocket(Utils.TELNET_PORT);
				logger.info("CommandLine: socket opened");
				incoming = s.accept();
				logger.info("CommandLine: socket accept");
			} catch (IOException e) {
				logger.debug(
						"CommandLine: IOException while establishing socket", e);
			}

			try {

				InputStream inps = incoming.getInputStream();
				OutputStream outs = incoming.getOutputStream();

				Scanner in = new Scanner(inps);
				PrintWriter out = new PrintWriter(outs, true);

				out.println("*** OpenMp3Player " + Mng.version + " ***");
				out.print(prompt);
				out.flush();

				boolean done = false;
				while (!done && in.hasNextLine()) {
					out.flush();
					String line = in.nextLine().trim();
					logger.info("CommandLine: cmd: " + line);
					if (line.equalsIgnoreCase("exit")
							|| line.equalsIgnoreCase("close")) {

						done = true;

					} else if (line.equalsIgnoreCase("version")) {

						out.println(Mng.version);

					} else if (line.startsWith("play")) {
						
						String[] args = line.split(" ");
						if (args.length == 0) {
							out.println("Playing: " + mng.play(0));
						} else if (args.length > 1) {
							
							for (int i = 1; i < args.length; i++) {
								
								if (args[i].equals("-i")) {
									int indx = 0;
									try {
										indx = Integer.parseInt(args[i+1].trim());
									} catch (NumberFormatException e) {
										out.println("Object not integer: " + args[i+1].trim());
									}
									out.println("Playing: " + mng.play(indx));
									
								}
								
							}
							
						} else {
							out.println("Playing: " + mng.play(0));
						}
						
						

					} else if (line.equalsIgnoreCase("stop")) {
						
						mng.stop();
						out.println("Stopped");

					} else if (line.equalsIgnoreCase("next")) {
						
						out.println("Playing: " + mng.next());

					} else if (line.equalsIgnoreCase("prev")) {
						
						out.println("Playing: " + mng.prev());

					} else if (line.equalsIgnoreCase("help")) {
						
						printHelp(out);

					} else if (line.startsWith("show")) {
						
						String[] args = line.split(" ");
						if (args.length == 0) {
							out.println("missing argument");
							out.println("  -p     playlist");
							out.println("  -s     song");
						} else if (args.length > 1) {
							
							if (args[1].equals("-p")) {
								out.println("Current playlist: " + mng.getPlistMng().getActivePlaylist());
							} else if (args[1].equals("-s")) {
								out.println("Currently playing: " + mng.getCurrentlyPlaying());
							} else {
								out.println("invalid argument: '" + args[1] + "'");
							}
							
							
							
						} else {
							// do nothing
						}

					} else if (line.length() == 0) {
						// do nothing
					} else {
						out.println("Unknown command! Use 'help'");
					}

					out.print(prompt);
					out.flush();

				}

			} catch (IOException e) {
				logger.error("CommandLine: IOException while listening", e);
			}

			try {
				incoming.close();
				s.close();
				logger.info("CommandLine: socket closed");
			} catch (IOException e) {
				logger.error("CommandLine: IOException while closing", e);
			}

		}

	}
	
	private void printHelp(PrintWriter out) {
		out.println("Usage:");
		out.println("  play      - start playing");
		out.println("    -i <index>    - play i-th song");
		out.println("  stop      - stop playing");
		out.println("  next      - play next");
		out.println("  prev      - play previous");
		out.println("  help      - you are looking it");
		out.println("  version   - show version");
	}

}