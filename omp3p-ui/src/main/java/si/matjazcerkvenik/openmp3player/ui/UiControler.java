package si.matjazcerkvenik.openmp3player.ui;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/OpenMp3Player")
public class UiControler {
	
	@Autowired
    private RestServiceImpl service;
	
	
	@RequestMapping(value="/playlists", method = RequestMethod.GET)
    public String findAllPlaylists(Model model) {
        model.addAttribute("playlists", service.getAllPlaylists());
        model.addAttribute("newPlist", new Playlist());
        return "playlists";
    }
	
	@RequestMapping(value="/playlist/{id}", method=RequestMethod.GET)
	public String getPlaylist(@PathVariable Integer id, Model model) {
		Playlist p = service.getSinglePlaylists(id);
		model.addAttribute("playlist", p);
		return "playlist";
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newMp3File") Mp3File task) {
        service.create(task);
        return "redirect:/";
    }
	
	@RequestMapping(value="/rest/player/play", method=RequestMethod.GET, params="action=save")
	public void play() {
		
	}
	
}
