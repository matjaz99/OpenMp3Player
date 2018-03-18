package si.matjazcerkvenik.openmp3player.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/files")
@SpringBootApplication
public class OMP3PStart {
	
	@Autowired
	private Omp3pServiceImpl service;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Mp3File> findAll() {
		return service.findAll();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(OMP3PStart.class, args);
    }
	
}
