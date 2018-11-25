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
@Api("API for viewing and managing playlists and controlling the player.")
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
	
	
	
	/*
	 * PLAYLISTS
	 */
	
	
	@ApiOperation(value = "Get all playlists", notes = "Returns list of all playlists in DB.", responseContainer = "List", response = Playlist.class)
	@RequestMapping(value = "/playlist/all", method = RequestMethod.GET)
	public List<Playlist> getAllPlaylists() {
		return service.getAllPlaylists();
	}
	
	@ApiOperation(value = "Get playlist count", notes = "Returns the number of playlists.")
	@RequestMapping(value = "/playlist/count", method = RequestMethod.GET)
	public long getPlaylistCount() {
		return service.getPlaylistCount();
	}
	
	@ApiOperation(value = "Get single playlist", notes = "Returns playlist by its ID.")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Invalid ID", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Boolean.class)),
            @ApiResponse(code = 404, message = "Playlist not found") })
	@RequestMapping(value = "/playlist/{id}", method = RequestMethod.GET)
	public Playlist getPlaylist(@ApiParam(value = "Playlist ID", required = true, defaultValue = "1") @PathVariable Integer id) {
		return service.getPlaylist(id);
	}
	
	@ApiOperation(value = "Create playlist", notes = "Create new playlist. ID and hash are not required, they will be set automatically.")
	@RequestMapping(value = "/playlist", method = RequestMethod.POST)
	public Playlist createNewPlaylist(@ApiParam(value = "Playlist object", required = true) @Valid @RequestBody Playlist playlist) {
		return service.createPlaylist(playlist);
	}
	
	@ApiOperation(value = "Delete playlist", notes = "Delete playlist. This action does not remove mp3 files in DB.")
	@RequestMapping(value = "/playlist/{id}", method = RequestMethod.DELETE)
	public void deletePlaylist(@ApiParam(value = "Playlist ID", required = true, defaultValue = "0") @PathVariable Integer id) {
		service.deletePlaylist(id);
	}
	
	
	
	
	/*
	 * MP3 FILES
	 */
	
	
	@ApiOperation(value = "Get all mp3 files", notes = "Returns list of all mp3 files in DB.", responseContainer = "List", response = Mp3File.class)
	@RequestMapping(value = "/mp3file/all", method = RequestMethod.GET)
	public List<Mp3File> getAllMp3Files() {
		return service.getAllMp3Files();
	}
	
	@ApiOperation(value = "Get mp3 files count", notes = "Returns the number of mp3 files.")
	@RequestMapping(value = "/mp3file/count", method = RequestMethod.GET)
	public long getMp3FileCount() {
		return service.getMp3FileCount();
	}
	
	@ApiOperation(value = "Create mp3 file", notes = "Create new mp3 file. ID and hash are not required, they will be set automatically.")
	@RequestMapping(value = "/mp3file", method = RequestMethod.POST)
	public Mp3File create(@ApiParam(value = "Mp3 file object", required = true) @Valid @RequestBody Mp3File mp3file) {
		return service.create(mp3file);
	}
	
	@ApiOperation(value = "Get single mp3 file", notes = "Returns mp3 file by its ID.")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Mp3 file not found") })
	@RequestMapping(value = "/mp3file/{id}", method = RequestMethod.GET)
	public Mp3File getMp3File(@ApiParam(value = "Mp3 file ID", required = true, defaultValue = "1") @PathVariable int id) {
		return service.getMp3File(id);
	}
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//	public Mp3File update(@PathVariable Long id, @Valid @RequestBody Mp3File mp3file) {
//		return service.update(id, mp3file);
//	}

	@ApiOperation(value = "Delete mp3 file", notes = "Delete mp3 file with given ID.")
	@RequestMapping(value = "/mp3file/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id) {
		service.delete(id);
	}
	
	
	
	
	
	/*
	 * PLAYER
	 */
	
	
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
	
	@RequestMapping(value = "/player/play/{playlist_id}/{mp3File_id}", method = RequestMethod.GET)
	public Mp3File play(@PathVariable Integer playlist_id, @PathVariable Integer mp3File_id) {
		
		System.out.println("Player #" + mp3Player.hashCode() + ", action=play, playlist_id=" + playlist_id + ", mp3File_id=" + mp3File_id);
		
		return mp3Player.play(playlist_id, mp3File_id);
	}
	
	@RequestMapping(value = "/player/stop", method = RequestMethod.GET)
	public void stop() {
		
		System.out.println("Player #" + mp3Player.hashCode() + ", action=stop");
		
		mp3Player.stop();
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
