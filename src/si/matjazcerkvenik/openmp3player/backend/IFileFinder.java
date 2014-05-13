package si.matjazcerkvenik.openmp3player.backend;

import java.util.List;

public interface IFileFinder {
	
	public List<Mp3File> getMp3Files(String source);
	
}
