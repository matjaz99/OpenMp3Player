package si.matjazcerkvenik.openmp3player.model;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.matjazcerkvenik.openmp3player.id3.ID3Tag;
import si.matjazcerkvenik.utils4j.Utils4j;

@Service
public class Omp3pServiceImpl {
	
	@Autowired
	private Mp3FilesRepository mp3FilesRepository;
	
	@Autowired
	private PlaylistsRepository playlistsRepository;
	
	public List<Playlist> getAllPlaylists() {
		List<Playlist> list = playlistsRepository.findAll();
		list.forEach(System.out::println);
		return list;
    }
	
	public long getPlaylistCount() {
        return playlistsRepository.count();
    }
	
	public Playlist getPlaylist(int id) {
//        return playlistsRepository.findOne(id);
        return playlistsRepository.findById(id).orElse(null);
    }
	
	@Transactional
	public Playlist createPlaylist(Playlist p) {
		List<Mp3File> list = loadMp3FilesFromDirectory(p);
//		p.setMp3files(new HashSet<>(list));
		playlistsRepository.save(p);
		return p;
	}
	
	@Transactional
    public void deletePlaylist(Integer id) {
//    	Playlist p = playlistsRepository.findOne(id);
		Playlist p = playlistsRepository.findById(id).orElse(null);
        playlistsRepository.delete(p);
    }
	
	
	
	public List<Mp3File> getAllMp3Files() {
		List<Mp3File> list = new ArrayList<Mp3File>();
        mp3FilesRepository.findAll().forEach(list::add);
        return list;
    }
	
	public long getMp3FileCount() {
        return mp3FilesRepository.count();
    }
	
	public Mp3File getMp3File(int id) {
		Mp3File m = mp3FilesRepository.findById(id).orElse(null);
        return m;
    }
	
	@Transactional
	public Mp3File create(Mp3File m) {
		m.setHash("" + System.currentTimeMillis());
//		m.setPath("path");
		m = mp3FilesRepository.save(m);
		return m;
	}
	
//	@Transactional
//    public Mp3File update(Long id, Mp3File task) {
//        Task entity = findOneSafe(id);
//        entity.setDescription(task.getDescription());
//        entity.setCompleted(task.isCompleted());
//        return new TaskDTO(entity.getId(), entity.getDescription(), entity.isCompleted());
//    }

    @Transactional
    public void delete(Integer id) {
    	Mp3File m = findOneSafe(id);
        mp3FilesRepository.delete(m);
    }
    
    private Mp3File findOneSafe(Integer id) {
    	Mp3File task = mp3FilesRepository.findById(id).orElse(null);
        if (task == null) {
//            throw new TaskNotFoundException();
        	System.out.println("Mp3File not found");
        	return null;
        } else {
            return task;
        }
    }
    
    /**
	 * Load mp3 files from directory
	 * @param directory
	 * @return
	 */
	private List<Mp3File> loadMp3FilesFromDirectory(Playlist p) {
		
		File dir = new File(p.getSourceDirectory());
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && (pathname.getAbsolutePath().toLowerCase().endsWith(".mp3"));
			}
		});
		
		List<Mp3File> list = new ArrayList<Mp3File>();
		
		if (files == null) {
			return list;
		}
		
		for (int i = 0; i < files.length; i++) {
			Mp3File mp3 = new Mp3File();
			mp3.setPath(files[i].getAbsolutePath());
			mp3.setFilename(files[i].getName());
			mp3.setHash(Utils4j.getMd5Checksum(new File(files[i].getAbsolutePath())));
			mp3 = ID3Tag.getMetadata(mp3);
//			mp3.setPlaylist(p);
			list.add(mp3);
			p.addMp3File(mp3);
		}
				
		return list;
	}

}
