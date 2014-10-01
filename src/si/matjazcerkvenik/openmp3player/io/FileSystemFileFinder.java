package si.matjazcerkvenik.openmp3player.io;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import si.matjazcerkvenik.openmp3player.backend.Mp3File;
import si.matjazcerkvenik.openmp3player.backend.Playlist;
import si.matjazcerkvenik.openmp3player.backend.Playlists;

public class FileSystemFileFinder implements IFileFinder {
	
	@Override
	public List<Mp3File> getMp3Files(String directory) {
		
		File dir = new File(directory);
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getAbsolutePath().endsWith(".mp3");
			}
		});
		
		List<Mp3File> list = new ArrayList<Mp3File>();
		
		if (files == null) {
			return list;
		}
		
		for (int i = 0; i < files.length; i++) {
			Mp3File t = new Mp3File();
			t.setIndex(i);
			t.setPath(files[i].getAbsolutePath());
			list.add(t);
		}
				
		return list;
		
	}

	@Override
	public Playlists getPlaylists() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Playlist getPlaylist(String source) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
