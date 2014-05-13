package si.matjazcerkvenik.openmp3player.backend;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Utils {

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

}
