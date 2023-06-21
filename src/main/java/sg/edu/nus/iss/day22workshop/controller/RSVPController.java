package sg.edu.nus.iss.day22workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.day22workshop.model.RSVP;
import sg.edu.nus.iss.day22workshop.repository.RSVPRepository;

@RestController
@RequestMapping("/api/rsvps")
public class RSVPController {

    @Autowired
    RSVPRepository repository;

    @GetMapping
    public ResponseEntity<List<RSVP>> getAll() {
        List<RSVP> rsvps = new ArrayList<>();
        rsvps = repository.findAll();

        if (rsvps.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<RSVP>>(rsvps, HttpStatus.OK);
    }

    @GetMapping("/{rsvp-id}")
    public ResponseEntity<RSVP> getByID(@PathVariable("rsvp-id") int id) {
        RSVP rsvp = repository.findByID(id);

        if (rsvp != null) {
            return new ResponseEntity<RSVP>(rsvp, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping
    public ResponseEntity<Boolean> saveRSVP(@RequestBody RSVP rsvp) {
        Boolean saved = false;
        saved = repository.insert(rsvp);

        if (saved) {
            return ResponseEntity.ok().body(saved);
        }

        return new ResponseEntity<Boolean>(saved, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{rsvp-id}")
    public ResponseEntity<Boolean> updateRSVP(@PathVariable("rsvp-id") int id, @RequestBody RSVP rsvp) {
        RSVP r = repository.findByID(id);
        Boolean updated = false;

        if (r != null) {
            updated = repository.update(rsvp);
        }

        if (updated) {
            return ResponseEntity.ok().body(updated);
        }

        return ResponseEntity.ofNullable(updated);
    }

}
