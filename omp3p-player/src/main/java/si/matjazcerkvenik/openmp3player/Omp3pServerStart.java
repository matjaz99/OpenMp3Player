package si.matjazcerkvenik.openmp3player;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;
import si.matjazcerkvenik.openmp3player.model.Mp3File;
import si.matjazcerkvenik.openmp3player.model.Omp3pServiceImpl;
import si.matjazcerkvenik.openmp3player.model.Playlist;
import si.matjazcerkvenik.openmp3player.player.Mp3Player;

@RestController
@RequestMapping("/openmp3player/rest")
@SpringBootApplication
@Api(value = "dvvse", description = "Set of endpoints for creating, retrieving, updating and deleting database of airplanes.")
public class Omp3pServerStart {
	
	@Autowired
	private Omp3pServiceImpl service;
	
	@Autowired
	private Mp3Player mp3Player;
	
	@Autowired
	private LogUtil util;
	
	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Omp3pServerStart.class, args);
    }
	
	@PostConstruct
	public void init() {
		System.out.println("Initializing OMP3P");
		
	}
	
	@ApiOperation(value = "Get all playlists", notes = "Returns list of all playlists in DB.", responseContainer = "List", response = Playlist.class)
	@RequestMapping(value = "/playlists", method = RequestMethod.GET)
	public List<Playlist> findAllPlaylists() {
		return service.getAllPlaylists();
	}
	
	@ApiOperation(value = "Get size", notes = "Returns the number of playlists.")
	@RequestMapping(value = "/playlists/size", method = RequestMethod.GET)
	public long getPlaylistsSize() {
		return service.getPlaylistsSize();
	}
	
	@ApiOperation(value = "Get single airplane", notes = "Returns one airplane by its id.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Boolean.class)),
            @ApiResponse(code = 404, message = "Airplane not found") })
	@RequestMapping(value = "/playlists/{id}", method = RequestMethod.GET)
	public Playlist getPlaylist(@ApiParam(value = "ID of the airplane to be obtained. Cannot be empty.", required = true, defaultValue = "0") @PathVariable Integer id) {
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
		return service.getAllMp3Files();
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
	
	
	@RequestMapping(value = "/player/{action}/{playlist_id}/{mp3File_id}", method = RequestMethod.GET)
	public Mp3File playerGet(@PathVariable String action, @PathVariable Integer playlist_id, @PathVariable Integer mp3File_id) {
		
		System.out.println("Player #" + mp3Player.hashCode() + ", action=" + action + ", playlist_id=" + playlist_id + ", mp3File_id=" + mp3File_id);
		
		if (action.equalsIgnoreCase("play")) {
			return mp3Player.play(playlist_id, mp3File_id);
		} else if (action.equalsIgnoreCase("pause")) {
		} else if (action.equalsIgnoreCase("stop")) {
			mp3Player.stop();
		} else if (action.equalsIgnoreCase("next")) {
		} else if (action.equalsIgnoreCase("prev")) {
		} else {
			System.out.println("WARN: unknown action : " + action);
		}
		
		return null;
	}
	
	
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	public Mp3File playerPost(@Valid @RequestBody String action) {
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
