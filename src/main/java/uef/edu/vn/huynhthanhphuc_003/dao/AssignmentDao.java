package uef.edu.vn.huynhthanhphuc_003.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uef.edu.vn.huynhthanhphuc_003.model.AssignmentView;

@Repository
public class AssignmentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<AssignmentView> mapper = (rs, rowNum) -> {
        AssignmentView a = new AssignmentView();
        a.setAssignmentID(rs.getInt("AssignmentID"));
        a.setTopicID(rs.getInt("TopicID"));
        a.setLecturerID(rs.getInt("LecturerID"));
        a.setTopicCode(rs.getString("TopicCode"));
        a.setTopicName(rs.getString("TopicName"));
        a.setLecturerCode(rs.getString("LecturerCode"));
        a.setLecturerName(rs.getString("LecturerName"));
        Date date = rs.getDate("AssignmentDate");
        a.setAssignmentDate(date == null ? null : date.toLocalDate());
        return a;
    };

    public List<AssignmentView> findAll() {
        String sql = "SELECT la.AssignmentID, la.TopicID, la.LecturerID, t.TopicCode, t.TopicName, "
                + "l.LecturerCode, l.FullName AS LecturerName, la.AssignmentDate "
                + "FROM LecturerAssignments la "
                + "JOIN Topics t ON la.TopicID = t.TopicID "
                + "JOIN Lecturers l ON la.LecturerID = l.LecturerID "
                + "ORDER BY la.AssignmentDate DESC, la.AssignmentID DESC";
        return jdbcTemplate.query(sql, mapper);
    }

    public AssignmentView findById(int assignmentID) {
        try {
            String sql = "SELECT la.AssignmentID, la.TopicID, la.LecturerID, t.TopicCode, t.TopicName, "
                    + "l.LecturerCode, l.FullName AS LecturerName, la.AssignmentDate "
                    + "FROM LecturerAssignments la "
                    + "JOIN Topics t ON la.TopicID = t.TopicID "
                    + "JOIN Lecturers l ON la.LecturerID = l.LecturerID "
                    + "WHERE la.AssignmentID = ?";
            return jdbcTemplate.queryForObject(sql, mapper, assignmentID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Integer findAssignmentIdByTopic(int topicID) {
        try {
            return jdbcTemplate.queryForObject("SELECT AssignmentID FROM LecturerAssignments WHERE TopicID = ? LIMIT 1", Integer.class, topicID);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void saveOrUpdateByTopic(int topicID, int lecturerID, LocalDate assignmentDate) {
        Integer assignmentID = findAssignmentIdByTopic(topicID);
        if (assignmentID == null) {
            jdbcTemplate.update("INSERT INTO LecturerAssignments(TopicID, LecturerID, AssignmentDate) VALUES(?,?,?)",
                    topicID, lecturerID, Date.valueOf(assignmentDate));
        } else {
            update(assignmentID, topicID, lecturerID, assignmentDate);
        }
    }

    public void update(int assignmentID, int topicID, int lecturerID, LocalDate assignmentDate) {
        jdbcTemplate.update("UPDATE LecturerAssignments SET TopicID=?, LecturerID=?, AssignmentDate=? WHERE AssignmentID=?",
                topicID, lecturerID, Date.valueOf(assignmentDate), assignmentID);
    }
}
