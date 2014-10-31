package si.matjazcerkvenik.openmp3player.web;

import si.matjazcerkvenik.openmp3player.backend.OContext;
import si.matjazcerkvenik.openmp3player.backend.Utils;
import si.matjazcerkvenik.openmp3player.player.SoundControl;

public class SettingsBean {
	
	/**
	 * Get version of OpenMp3Player
	 * @return version
	 */
	public String getVersion() {
		return OContext.version;
	}
	
	public String getLocalIp() {
		return Utils.getLocalIp();
	}
	
	public String getCurrentSoundLevel() {
		return "" + SoundControl.CURRENT_VOLUME_LEVEL;
	}
	
	public String getTelnetEnabled() {
		return "" + Utils.TELNET_ENABLED;
	}
	
	public String getTelnetPort() {
		return "" + Utils.TELNET_PORT;
	}
	
}
