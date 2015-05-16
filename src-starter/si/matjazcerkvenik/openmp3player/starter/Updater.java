package si.matjazcerkvenik.openmp3player.starter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * This class performs update procedure.
 * 
 * @author matjaz
 * 
 */
public class Updater {

	private static String url = "http://www.matjazcerkvenik.si/projects/openmp3player/getLastVersion.php";

	public static String lastVersion = "0.0.0";
	
	/**
	 * When Updater is instanced, check for last version.
	 */
	public Updater() {
		getLastVersion();
	}
	
	
	
	/**
	 * Delete working directory and war, download new war and update version file.
	 */
	public void updateOmp3p() {
		
		deleteDirectory(new File("server/apache-tomcat-7.0.57/work/Catalina/localhost/OpenMp3Player"));
		deleteDirectory(new File("server/apache-tomcat-7.0.57/webapps/OpenMp3Player"));
		deleteFile(new File("server/apache-tomcat-7.0.57/webapps/OpenMp3Player.war"));
		downloadWar();
		updateVersion();
		
	}

	
	/**
	 * Send request to the mc.si server and read last version.
	 */
	private void getLastVersion() {

		try {
			InputStream iStream = new URL(url).openStream();

			BufferedReader in = new BufferedReader(new InputStreamReader(
					iStream));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			lastVersion = response.toString();

		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}

	}
	
	
	/**
	 * Parse the last version and compare it to installed version. Return true if 
	 * version on the mc.si server is higher than installed.
	 * @return true if update is required
	 */
	public boolean isUpdateRequired() {
		
		if (Start.version.contains("alpha") || Start.version.contains("beta")) {
			// cannot update developer version, maybe in future
			return false;
		}
		
		if (Start.version.equals(lastVersion)) {
			return false;
		}
		
		String[] tempVer = Start.version.split("\\.");
		String[] tempLastVer = lastVersion.split("\\.");
		int[] tempVerInt = new int[3];
		int[] tempLastVerInt = new int[3];
		tempVerInt[0] = Integer.parseInt(tempVer[0]);
		tempVerInt[1] = Integer.parseInt(tempVer[1]);
		tempVerInt[2] = Integer.parseInt(tempVer[2]);
		tempLastVerInt[0] = Integer.parseInt(tempLastVer[0]);
		tempLastVerInt[1] = Integer.parseInt(tempLastVer[1]);
		tempLastVerInt[2] = Integer.parseInt(tempLastVer[2]);
		
		if (tempVerInt[0] < tempLastVerInt[0]) {
			return true;
		}
		if (tempVerInt[1] < tempLastVerInt[1]) {
			return true;
		}
		if (tempVerInt[2] < tempLastVerInt[2]) {
			return true;
		}
		
		return false;
		
	}

	
	/**
	 * Delete given directory and all its contents
	 * 
	 * @param directory
	 * @return true on success
	 */
	private boolean deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
		return directory.delete();
	}
	
	
	/**
	 * Delete single file
	 * @param file
	 * @return true on success
	 */
	private boolean deleteFile(File file) {
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}
	
	
	/**
	 * Download last war from the mc.si server into /server/apache-tomcat-7.0.57/webapps directory.
	 * War will be automatically deployed when server starts.
	 */
	private void downloadWar() {
		
		try {
			ReadableByteChannel in = Channels.newChannel(new URL("http://www.matjazcerkvenik.si/projects/download/OpenMp3Player/" + lastVersion + "/OpenMp3Player.war").openStream());
			FileOutputStream fos = new FileOutputStream("server/apache-tomcat-7.0.57/webapps/OpenMp3Player.war");
			FileChannel channel = fos.getChannel();
			channel.transferFrom(in, 0, Long.MAX_VALUE);
			channel.close();
			fos.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			 
	}
	
	
	/**
	 * Write last version to version.txt file.
	 */
	private void updateVersion() {
		try {
			FileWriter fw = new FileWriter("config/version.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Updater.lastVersion);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
