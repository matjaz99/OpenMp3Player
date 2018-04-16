package si.matjazcerkvenik.openmp3player.player;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import si.matjazcerkvenik.utils4j.Utils4j;

@Service
public class Omp3pServiceImpl {
	
	@Autowired
	private Mp3FilesRepository repository;
	
	@Autowired
	private PlaylistsRepository playistsRepository;
	
	public List<Playlist> getAllPlaylists() {
		List<Playlist> list = new ArrayList<Playlist>();
        playistsRepository.findAll().forEach(list::add);
        return list;
    }
	
	public Playlist getPlaylist(int id) {
        return playistsRepository.findOne(id);
    }
	
	@Transactional
	public Playlist createPlaylist(Playlist p) {
		List<Mp3File> list = loadMp3FilesFromDirectory(p);
		p.setMp3files(new HashSet<>(list));
		playistsRepository.save(p);
		return p;
	}
	
	@Transactional
    public void deletePlaylist(Integer id) {
    	Playlist p = playistsRepository.findOne(id);
//    	repository.delete(p.getMp3files());
        playistsRepository.delete(p);
        // TODO https://stackoverflow.com/questions/7197181/jpa-unidirectional-many-to-one-and-cascading-delete
    }
	
	
	
	public List<Mp3File> listAllMp3Files() {
		List<Mp3File> list = new ArrayList<Mp3File>();
        repository.findAll().forEach(list::add);
        return list;
    }
	
	public long getMp3FilesSize() {
        return repository.count();
    }
	
	@Transactional
	public Mp3File create(Mp3File m) {
		m.setHash("" + System.currentTimeMillis());
//		m.setPath("path");
		m = repository.save(m);
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
        repository.delete(m);
    }
    
    private Mp3File findOneSafe(Integer id) {
    	Mp3File task = repository.findOne(id);
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
			mp3.setHash(Utils4j.getMd5Checksum(new File(files[i].getAbsolutePath())));
			mp3 = ID3Tag.getMetadata(mp3);
//			mp3.setPlaylist(p);
			list.add(mp3);
		}
				
		return list;
	}

}
