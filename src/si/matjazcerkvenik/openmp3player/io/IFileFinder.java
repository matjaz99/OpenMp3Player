package si.matjazcerkvenik.openmp3player.io;

import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;

public interface IFileFinder {
	
	public List<Mp3File> getMp3Files(String source);
	
}
