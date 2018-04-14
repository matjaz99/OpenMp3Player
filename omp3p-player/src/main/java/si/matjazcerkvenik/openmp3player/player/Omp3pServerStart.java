package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openmp3player/rest")
@SpringBootApplication
public class Omp3pServerStart {
	
	@Autowired
	private Omp3pServiceImpl service;
	
	
	@RequestMapping(value = "/playlists", method = RequestMethod.GET)
	public List<Playlist> findAllPlaylists() {
		return service.getAllPlaylists();
	}
	
	@RequestMapping(value = "/playlists", method = RequestMethod.POST)
	public Playlist create(@Valid @RequestBody Playlist p) {
		return service.createPlaylist(p);
	}
	
	@RequestMapping(value = "/playlists/{id}", method = RequestMethod.DELETE)
	public void deletePlaylist(@PathVariable Integer id) {
		service.deletePlaylist(id);
	}
	
	@RequestMapping(value = "/files", method = RequestMethod.GET)
	public List<Mp3File> findAllMp3Files() {
		return service.listAllMp3Files();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Mp3File create(@Valid @RequestBody Mp3File mp3file) {
		return service.create(mp3file);
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public Mp3File update(@PathVariable Long id, @Valid @RequestBody Mp3File mp3file) {
//		return service.update(id, mp3file);
//	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	
	
	public static void main(String[] args) {
        SpringApplication.run(Omp3pServerStart.class, args);
    }
	
}
