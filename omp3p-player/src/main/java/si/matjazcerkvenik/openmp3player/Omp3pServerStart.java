package si.matjazcerkvenik.openmp3player;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import si.matjazcerkvenik.openmp3player.model.Mp3File;
import si.matjazcerkvenik.openmp3player.model.Omp3pServiceImpl;
import si.matjazcerkvenik.openmp3player.model.Playlist;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;

@RestController
@RequestMapping("/openmp3player/rest")
@SpringBootApplication
//@Configuration
//@ComponentScan("si.matjazcerkvenik.openmp3player")
public class Omp3pServerStart {
	
	@Autowired
	private Omp3pServiceImpl service;
	
	@Autowired
	private Mp3Player mp3Player;
	
	
	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Omp3pServerStart.class, args);
    }
	
	
	@RequestMapping(value = "/playlists", method = RequestMethod.GET)
	public List<Playlist> findAllPlaylists() {
		return service.getAllPlaylists();
	}
	
	@RequestMapping(value = "/playlists/size", method = RequestMethod.GET)
	public long getPlaylistsSize() {
		return service.getPlaylistsSize();
	}
	
	@RequestMapping(value = "/playlists/{id}", method = RequestMethod.GET)
	public Playlist getPlaylist(@PathVariable Integer id) {
		return service.getPlaylist(id);
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
	
	@RequestMapping(value = "/files/size", method = RequestMethod.GET)
	public long getMp3FilesSize() {
		return service.getMp3FilesSize();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Mp3File create(@Valid @RequestBody Mp3File mp3file) {
		return service.create(mp3file);
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public Mp3File update(@PathVariable Long id, @Valid @RequestBody Mp3File mp3file) {
//		return service.update(id, mp3file);
//	}

	@RequestMapping(value = "/files/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	
	
	
	
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public Mp3File player(@Valid @RequestBody String action) {
		if (action.equalsIgnoreCase("play")) {
			System.out.println("player: play");
			mp3Player.play(0);
		} else if (action.equalsIgnoreCase("pause")) {
			System.out.println("player: pause");
		} else if (action.equalsIgnoreCase("stop")) {
			System.out.println("player: stop");
			mp3Player.stop();
		} else if (action.equalsIgnoreCase("next")) {
			System.out.println("player: next");
		} else if (action.equalsIgnoreCase("prev")) {
			System.out.println("player: prev");
		} else {
			System.out.println("player: unknown command : " + action);
		}
		System.out.println("Player #" + mp3Player.hashCode());
		return null;
	}
	
}
