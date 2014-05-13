package si.matjazcerkvenik.openmp3player.backend;

import java.util.List;

import si.matjazcerkvenik.openmp3player.player.IPlayer;
import si.matjazcerkvenik.openmp3player.player.jlayer.JLayerPlayer;

public class Mng {
	
	// TODO change volume on os x
	// osascript -e "set Volume 1"
	
	private static IPlayer player = null;
	private static List<Playlist> playlists = null;
	private static Playlist activePlaylist = null;
	public static Mp3File currentlyPlaying = null;
	private static Watchdog watchdog = null;
	
	public static String HOME_DIR = null;
	
	public Mng() {
		initialize();
	}
	
	public void initialize() {
		
		if (playlists == null) {
			player = new JLayerPlayer(this);
			playlists = PLReader.getPlaylists();
			activePlaylist = playlists.get(0);
			
			loadMp3Files();
			
			watchdog = new Watchdog(this);
			watchdog.start();
			
			System.out.println("Mng initialized");
		}
		
	}
	
	public Playlist getActivePlaylist() {
		return activePlaylist;
	}
	
	public String getCurrentlyPlaying() {
		if (currentlyPlaying == null) {
			return "null";
		}
		return currentlyPlaying.getTitle();
	}
	
	public void setActivePlaylist(String name) {
		for (Playlist p : playlists) {
			if (p.getName().equals(name)) {
				activePlaylist = p;
				System.out.println("setActivePlaylist: " + activePlaylist);
				return;
			}
		}
	}
	
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	
	public void loadMp3Files() {
		
		IFileFinder iff = null;
		String src = activePlaylist.getSource();
		System.out.println("loadMp3Files from playlist " + src);
		if (activePlaylist.getSource().endsWith(".xml")) {
			iff = new XmlPlaylistFileFinder();
			src = HOME_DIR + "/playlists/" + src;
		} else {
			iff = new FileSystemFileFinder();
		}
		
		activePlaylist.setMp3files(iff.getMp3Files(src));
		System.out.println("found " + getNumberOfMp3s() + " mp3 files in playlist " + activePlaylist.getName());
	}
	
	public int getNumberOfMp3s() {
		return activePlaylist.getMp3Files().size();
	}
	
	
	
	
	public static IPlayer getPlayer() {
		return player;
	}

	public void play(int index) {
		
		if (currentlyPlaying != null) {
			stop();
		}
		
		currentlyPlaying = activePlaylist.getMp3Files().get(index);
		System.out.println("play: " + currentlyPlaying);
		player.play(currentlyPlaying);
		
//		while (player.getStatus() != PlayerStatus.STOPPED) {
//			
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//		}
//		System.out.println("Loop ended");
		
	}
	
	public void stop() {
		if (currentlyPlaying == null) {
			return;
		}
		System.out.println("stop: " + currentlyPlaying);
		player.stop();
		currentlyPlaying = null;
	}
	
	public void next() {
		if (currentlyPlaying == null) {
			return;
		}
		if (currentlyPlaying.getIndex() 
				== activePlaylist.getMp3Files().size() - 1) {
			play(0);
		} else {
			play(currentlyPlaying.getIndex() + 1);
		}
		
	}
	
	public void prev() {
		if (currentlyPlaying == null) {
			return;
		}
		if (currentlyPlaying.getIndex() == 0) {
			play(activePlaylist.getMp3Files().size() - 1);
		} else {
			play(currentlyPlaying.getIndex() - 1);
		}
		
	}
	
//	@Override
//	public void playEnded() {
//		next();
//		play(currentlyPlaying.getIndex() + 1);
//	}
	
}
