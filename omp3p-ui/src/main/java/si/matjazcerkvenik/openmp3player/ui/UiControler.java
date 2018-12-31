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
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/OpenMp3Player")
public class UiControler {
	
	@Autowired
    private RestServiceImpl service;
	
	
	@RequestMapping(value="/playlists", method = {RequestMethod.GET, RequestMethod.DELETE})
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
	
	@RequestMapping(value="/playlist/{id}/{fileId}", method=RequestMethod.GET)
	public String getMp3File(@PathVariable Integer id, @PathVariable Integer fileId, Model model) {
		Playlist p = service.getSinglePlaylists(id);
		Mp3File m = null;
		for (Mp3File mp : p.getMp3files()) {
			if (mp.getId() == fileId) {
				m = mp;
				break;
			}
		}
		model.addAttribute("playlist", p);
		model.addAttribute("mp3file", m);
		return "mp3file";
	}
	
	@RequestMapping(value="/playlist/add", method=RequestMethod.GET)
	public String gotoAddPlaylist(Model model) {
		model.addAttribute("playlist", new Playlist());
		return "addPlaylist";
	}
	
	@RequestMapping(value="/playlist/add/submit", method=RequestMethod.POST)
	public String addPlaylistSubmit(@ModelAttribute Playlist playlist) {
		service.createPlaylist(playlist);
		return "redirect:/OpenMp3Player/playlists";
	}
	
	@RequestMapping(value="/playlist/delete/submit/{id}", method=RequestMethod.DELETE)
	public String deletePlaylistSubmit(@PathVariable Integer id) {
		service.deletePlaylist(id);
		return "redirect:/OpenMp3Player/playlists";
	}
	
	@RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newMp3File") Mp3File task) {
        service.create(task);
        return "redirect:/";
    }
	
	@RequestMapping(value="/player", method=RequestMethod.POST)
	public void play() {
		System.out.println("PLAY");
		// https://www.mkyong.com/spring-boot/spring-boot-ajax-example/
		// https://stackoverflow.com/questions/31401669/thymeleaf-multiple-submit-button-in-one-form
	}
	
	@RequestMapping(value="/player", method=RequestMethod.POST, params="action=Stop")
	public void stop() {
		System.out.println("STOP");
	}
	
}
