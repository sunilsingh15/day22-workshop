package sg.edu.nus.iss.day22workshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.day22workshop.RestTemplate.RSVPRestTemplate;
import sg.edu.nus.iss.day22workshop.model.RSVP;

@Controller
@RequestMapping("/rsvps")
public class RSVPUIController {

    @Autowired
    RSVPRestTemplate template;

    @GetMapping("/add")
    public String RSVPAdd(Model model) {
        RSVP newRSVP = new RSVP();
        model.addAttribute("rsvp", newRSVP);
        return "rsvpadd";
    }

    @PostMapping("/saveRSVP")
    public String postRSVP(@ModelAttribute RSVP rsvp) {
        Boolean result = template.createRSVP(rsvp);

        return "home";
    }

    @GetMapping("/list")
    public String listRSVP(Model model) {
        List<RSVP> rsvps = template.getRSVPs();
        model.addAttribute("rsvps", rsvps);
        return "rsvplist";
    }
    
}
