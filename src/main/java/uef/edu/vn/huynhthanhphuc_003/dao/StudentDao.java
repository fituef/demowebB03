package uef.edu.vn.huynhthanhphuc_003.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uef.edu.vn.huynhthanhphuc_003.model.Student;

@Repository
public class StudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> mapper = (rs, rowNum) -> {
        Student s = new Student();
        s.setStudentID(rs.getInt("StudentID"));
        s.setStudentCode(rs.getString("StudentCode"));
        s.setFullName(rs.getString("FullName"));
        s.setMajor(rs.getString("Major"));
        s.setEmail(rs.getString("Email"));
        s.setPhoneNumber(rs.getString("PhoneNumber"));
        return s;
    };

    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM Students ORDER BY StudentID DESC", mapper);
    }

    public Student findById(int id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Students WHERE StudentID = ?", mapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean exists(int studentID) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Students WHERE StudentID = ?", Integer.class, studentID);
        return count != null && count > 0;
    }

    public void insert(Student student) {
        String sql = "INSERT INTO Students(StudentCode, FullName, Major, Email, PhoneNumber) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, student.getStudentCode(), student.getFullName(), student.getMajor(), student.getEmail(), student.getPhoneNumber());
    }

    public void update(Student student) {
        String sql = "UPDATE Students SET StudentCode=?, FullName=?, Major=?, Email=?, PhoneNumber=? WHERE StudentID=?";
        jdbcTemplate.update(sql, student.getStudentCode(), student.getFullName(), student.getMajor(), student.getEmail(), student.getPhoneNumber(), student.getStudentID());
    }
}
