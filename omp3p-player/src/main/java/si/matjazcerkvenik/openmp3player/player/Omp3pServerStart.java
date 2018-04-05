package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Mp3File> findAll() {
		return service.listAll();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Mp3File create(@Valid @RequestBody Mp3File mp3file) {
		return service.create(mp3file);
	}
	
	public static void main(String[] args) {
        SpringApplication.run(Omp3pServerStart.class, args);
    }
	
}
