package si.matjazcerkvenik.openmp3player.player;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Omp3pServiceImpl {
	
	@Autowired
	private Mp3FilesRepository repository;
	
	@Autowired
	private PlaylistsRepository plalyistsRepository;
	
	public List<Playlist> listAllPlaylists() {
		List<Playlist> list = new ArrayList<Playlist>();
        plalyistsRepository.findAll().forEach(list::add);
        return list;
    }
	
	@Transactional
	public Playlist create(Playlist p) {
		List<Mp3File> list = listAllMp3Files();
		p.setMp3files(list);
		plalyistsRepository.save(p);
		return p;
	}
	
	public List<Mp3File> listAllMp3Files() {
		List<Mp3File> list = new ArrayList<Mp3File>();
        repository.findAll().forEach(list::add);
        return list;
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

}
