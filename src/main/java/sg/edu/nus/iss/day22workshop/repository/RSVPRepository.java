package sg.edu.nus.iss.day22workshop.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day22workshop.model.RSVP;

@Repository
public class RSVPRepository {

    @Autowired
    JdbcTemplate template;

    private final String countSQL = "select count(*) from rsvp";
    private final String findAllSQL = "select * from rsvp";
    private final String findByIDSQL = "select * from rsvp where id = ?";
    private final String insertSQL = "insert into rsvp (full_name, email, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";
    private final String updateSQL = "update rsvp set email = ?, phone = ? confirmation_date = ? where id = ?";

    public int count() {
        return template.queryForObject(countSQL, Integer.class);
    }

    public List<RSVP> findAll() {
        List<RSVP> rsvps = template.query(findAllSQL, BeanPropertyRowMapper.newInstance(RSVP.class));
        return rsvps;
    }

    public RSVP findByID(int id) {
        return template.queryForObject(findByIDSQL, BeanPropertyRowMapper.newInstance(RSVP.class), id);
    }

    public Boolean insert(RSVP rsvp) {
        int saved = 0;
        saved = template.update(insertSQL,
                rsvp.getFullName(),
                rsvp.getEmail(),
                rsvp.getPhone(),
                rsvp.getConfirmationDate(),
                rsvp.getComments());

        if (saved > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean update(RSVP rsvp) {
        int updated = 0;

        updated = template.update(updateSQL, rsvp.getEmail(), rsvp.getPhone(), rsvp.getConfirmationDate(),
                rsvp.getId());

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }

    // write a function for batch insert
    // refer to slide 17 of day 22

}
