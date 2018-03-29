package si.matjazcerkvenik.openmp3player.ui;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Omp3pControler {
	
	@Autowired
    private RestServiceImpl service;
	
	@RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("files", service.findAll());
        model.addAttribute("newMp3File", new Mp3File());
        return "tasks";
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("newMp3File") Mp3File task) {
        service.create(task);
        return "redirect:/";
    }
	
}
