package si.matjazcerkvenik.openmp3player.player;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Utils;

public class SoundControl {
	
	public static int CURRENT_VOLUME_LEVEL = 2;
	
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
		OContext.getInstance().getLogger().info("Utils:volumeUp(): " + CURRENT_VOLUME_LEVEL);	
		
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
		OContext.getInstance().getLogger().info("Utils:volumeDown(): " + CURRENT_VOLUME_LEVEL);	
		
		setVolume(CURRENT_VOLUME_LEVEL);
		
	}
	
	/**
	 * Set volume level in range from 0 to 10.
	 * @param level
	 */
	public static void setVolume(int level) {
		OContext.getInstance().getLogger().info("Utils:setVolume(): " + level);	
		String[] vol = { "" + level };
		if (Utils.VOLUME_CUSTOM_SCRIPT != null) {
			Utils.runScript(Utils.VOLUME_CUSTOM_SCRIPT, vol);
		} else if (Utils.getOsType().equalsIgnoreCase("OSX")) {
			Utils.runScript("setVolumeOSX.sh", vol);
		} else if (Utils.getOsType().equalsIgnoreCase("LINUX")) {
			Utils.runScript("setVolumeLinux.sh", vol);
		} else if (Utils.getOsType().equalsIgnoreCase("WINDOWS")) {
			Utils.runScript("setVolume.bat", vol);
		}
		
		
	}
	
}
