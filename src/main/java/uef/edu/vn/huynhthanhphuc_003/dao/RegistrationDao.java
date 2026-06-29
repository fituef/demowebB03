package uef.edu.vn.huynhthanhphuc_003.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uef.edu.vn.huynhthanhphuc_003.model.RegistrationView;

@Repository
public class RegistrationDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<RegistrationView> mapper = (rs, rowNum) -> {
        RegistrationView r = new RegistrationView();
        r.setRegistrationID(rs.getInt("RegistrationID"));
        r.setTopicID(rs.getInt("TopicID"));
        r.setStudentID(rs.getInt("StudentID"));
        r.setTopicCode(rs.getString("TopicCode"));
        r.setTopicName(rs.getString("TopicName"));
        r.setStudentCode(rs.getString("StudentCode"));
        r.setStudentName(rs.getString("StudentName"));
        r.setParticipationRole(rs.getString("ParticipationRole"));
        r.setRegistrationStatus(rs.getString("RegistrationStatus"));
        Date date = rs.getDate("RegistrationDate");
        r.setRegistrationDate(date == null ? null : date.toLocalDate());
        return r;
    };

    private String baseQuery() {
        return "SELECT tr.RegistrationID, tr.TopicID, tr.StudentID, tr.ParticipationRole, tr.RegistrationStatus, tr.RegistrationDate, "
                + "t.TopicCode, t.TopicName, s.StudentCode, s.FullName AS StudentName "
                + "FROM TopicRegistrations tr "
                + "JOIN Topics t ON tr.TopicID = t.TopicID "
                + "JOIN Students s ON tr.StudentID = s.StudentID ";
    }

    public List<RegistrationView> findAll(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            String like = "%" + keyword.trim() + "%";
            String sql = baseQuery()
                    + "WHERE s.StudentCode LIKE ? OR s.FullName LIKE ? OR t.TopicCode LIKE ? OR t.TopicName LIKE ? "
                    + "ORDER BY tr.RegistrationDate DESC, tr.RegistrationID DESC";
            return jdbcTemplate.query(sql, mapper, like, like, like, like);
        }
        return jdbcTemplate.query(baseQuery() + "ORDER BY tr.RegistrationDate DESC, tr.RegistrationID DESC", mapper);
    }

    public List<RegistrationView> findByStudent(int studentID) {
        return jdbcTemplate.query(baseQuery() + "WHERE tr.StudentID = ? ORDER BY tr.RegistrationDate DESC", mapper, studentID);
    }

    public boolean hasActiveRegistration(int studentID) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM TopicRegistrations WHERE StudentID = ?",
                Integer.class, studentID);
        return count != null && count > 0;
    }

    public void register(int topicID, int studentID, String participationRole) {
        jdbcTemplate.update("INSERT INTO TopicRegistrations(TopicID, StudentID, ParticipationRole, RegistrationStatus, RegistrationDate) "
                        + "VALUES(?,?,?,?,?)",
                topicID, studentID, participationRole, "Pending", Date.valueOf(LocalDate.now()));
    }

    public void updateStatus(int registrationID, String status) {
        jdbcTemplate.update("UPDATE TopicRegistrations SET RegistrationStatus = ? WHERE RegistrationID = ?", status, registrationID);
    }
}
