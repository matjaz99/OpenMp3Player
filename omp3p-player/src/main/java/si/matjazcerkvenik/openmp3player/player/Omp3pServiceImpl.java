package si.matjazcerkvenik.openmp3player.player;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Omp3pServiceImpl {
	
	@Autowired
	private Mp3FilesRepository repository;
	
	public List<Mp3File> listAll() {
		List<Mp3File> list = new ArrayList<Mp3File>();
        repository.findAll().forEach(list::add);
        return list;
    }

}
