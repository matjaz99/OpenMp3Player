package si.matjazcerkvenik.openmp3player.ui;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceImpl {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Value("${omp3p.resource.url}")
	private String resourceUrl;
	
	public List<Playlist> getAllPlaylists() {
	    return Arrays.stream(restTemplate.getForObject(resourceUrl + "/playlist/all", Playlist[].class)).collect(Collectors.toList());
	}
	
	public Playlist getSinglePlaylists(int id) {
		Playlist p = restTemplate.getForObject(resourceUrl + "/playlist/" + id, Playlist.class);
		return p;
	}
	
	public Playlist createPlaylist(Playlist p) {
		return restTemplate.postForObject(resourceUrl + "/playlist/", p, Playlist.class);
	}
	
	public void deletePlaylist(int id) {
		restTemplate.delete(resourceUrl + "/playlist/" + id);
	}
	
	public List<Mp3File> findAll() {
	    return Arrays.stream(restTemplate.getForObject(resourceUrl, Mp3File[].class)).collect(Collectors.toList());
	}
	
	public Mp3File create(Mp3File m) {
	    return restTemplate.postForObject(resourceUrl, m, Mp3File.class);
	}
	
	public Mp3File play(int playlistId, int fileId) {
		return restTemplate.getForObject(resourceUrl + "/play/" + playlistId + "/" + fileId, Mp3File.class);
	}
	
	
	
}
