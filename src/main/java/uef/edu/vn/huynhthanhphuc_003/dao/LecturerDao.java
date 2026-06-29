package uef.edu.vn.huynhthanhphuc_003.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uef.edu.vn.huynhthanhphuc_003.model.Lecturer;

@Repository
public class LecturerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Lecturer> mapper = (rs, rowNum) -> {
        Lecturer l = new Lecturer();
        l.setLecturerID(rs.getInt("LecturerID"));
        l.setLecturerCode(rs.getString("LecturerCode"));
        l.setFullName(rs.getString("FullName"));
        l.setFaculty(rs.getString("Faculty"));
        l.setEmail(rs.getString("Email"));
        l.setPhoneNumber(rs.getString("PhoneNumber"));
        return l;
    };

    public List<Lecturer> findAll() {
        return jdbcTemplate.query("SELECT * FROM Lecturers ORDER BY LecturerID DESC", mapper);
    }

    public Lecturer findById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Lecturers WHERE LecturerID = ?", mapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean exists(int lecturerID) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Lecturers WHERE LecturerID = ?", Integer.class, lecturerID);
        return count != null && count > 0;
    }

    public void insert(Lecturer lecturer) {
        String sql = "INSERT INTO Lecturers(LecturerCode, FullName, Faculty, Email, PhoneNumber) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, lecturer.getLecturerCode(), lecturer.getFullName(), lecturer.getFaculty(), lecturer.getEmail(), lecturer.getPhoneNumber());
    }

    public void update(Lecturer lecturer) {
        String sql = "UPDATE Lecturers SET LecturerCode=?, FullName=?, Faculty=?, Email=?, PhoneNumber=? WHERE LecturerID=?";
        jdbcTemplate.update(sql, lecturer.getLecturerCode(), lecturer.getFullName(), lecturer.getFaculty(), lecturer.getEmail(), lecturer.getPhoneNumber(), lecturer.getLecturerID());
    }
}
