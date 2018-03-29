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
	
	@Value("${resource.tasks}")
	private String resource;
	
	public List<Mp3File> findAll() {
	    return Arrays.stream(restTemplate.getForObject(resource, Mp3File[].class)).collect(Collectors.toList());
	}
	
	public Mp3File create(Mp3File task) {
	    return restTemplate.postForObject(resource, task, Mp3File.class);
	}
	
}
