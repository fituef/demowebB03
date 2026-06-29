package uef.edu.vn.huynhthanhphuc_003.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uef.edu.vn.huynhthanhphuc_003.model.Topic;

@Repository
public class TopicDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Topic> mapper = (rs, rowNum) -> {
        Topic t = new Topic();
        t.setTopicID(rs.getInt("TopicID"));
        t.setTopicCode(rs.getString("TopicCode"));
        t.setTopicName(rs.getString("TopicName"));
        t.setResearchField(rs.getString("ResearchField"));
        t.setDescription(rs.getString("Description"));
        t.setStartYear(rs.getInt("StartYear"));
        t.setEndYear(rs.getInt("EndYear"));
        t.setMaxStudents(rs.getInt("MaxStudents"));
        t.setStatus(rs.getString("Status"));
        try {
            t.setRegisteredCount(rs.getInt("RegisteredCount"));
        } catch (Exception ignored) {
            t.setRegisteredCount(0);
        }
        return t;
    };

    public List<Topic> findAll(String keyword) {
        String base = "SELECT t.*, COALESCE(rc.RegisteredCount, 0) AS RegisteredCount "
                + "FROM Topics t "
                + "LEFT JOIN (SELECT TopicID, COUNT(*) RegisteredCount FROM TopicRegistrations WHERE RegistrationStatus <> 'Rejected' GROUP BY TopicID) rc "
                + "ON t.TopicID = rc.TopicID ";
        if (keyword != null && !keyword.trim().isEmpty()) {
            String like = "%" + keyword.trim() + "%";
            return jdbcTemplate.query(base + "WHERE t.TopicCode LIKE ? OR t.TopicName LIKE ? ORDER BY t.TopicID DESC", mapper, like, like);
        }
        return jdbcTemplate.query(base + "ORDER BY t.TopicID DESC", mapper);
    }

    public List<Topic> findAvailableTopics() {
        String sql = "SELECT t.*, COALESCE(rc.RegisteredCount, 0) AS RegisteredCount "
                + "FROM Topics t "
                + "LEFT JOIN (SELECT TopicID, COUNT(*) RegisteredCount FROM TopicRegistrations WHERE RegistrationStatus <> 'Rejected' GROUP BY TopicID) rc "
                + "ON t.TopicID = rc.TopicID "
                + "WHERE t.Status = 'Open' AND COALESCE(rc.RegisteredCount, 0) < t.MaxStudents "
                + "ORDER BY t.TopicName";
        return jdbcTemplate.query(sql, mapper);
    }

    public Topic findById(int id) {
        try {
            String sql = "SELECT t.*, COALESCE(rc.RegisteredCount, 0) AS RegisteredCount "
                    + "FROM Topics t "
                    + "LEFT JOIN (SELECT TopicID, COUNT(*) RegisteredCount FROM TopicRegistrations WHERE RegistrationStatus <> 'Rejected' GROUP BY TopicID) rc "
                    + "ON t.TopicID = rc.TopicID WHERE t.TopicID = ?";
            return jdbcTemplate.queryForObject(sql, mapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean exists(int topicID) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Topics WHERE TopicID = ?", Integer.class, topicID);
        return count != null && count > 0;
    }

    public int countRegisteredStudents(int topicID) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM TopicRegistrations WHERE TopicID = ? AND RegistrationStatus <> 'Rejected'",
                Integer.class, topicID);
        return count == null ? 0 : count;
    }

    public void insert(Topic topic) {
        String sql = "INSERT INTO Topics(TopicCode, TopicName, ResearchField, Description, StartYear, EndYear, MaxStudents, Status) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, topic.getTopicCode(), topic.getTopicName(), topic.getResearchField(), topic.getDescription(),
                topic.getStartYear(), topic.getEndYear(), topic.getMaxStudents(), topic.getStatus());
    }

    public void update(Topic topic) {
        String sql = "UPDATE Topics SET TopicCode=?, TopicName=?, ResearchField=?, Description=?, StartYear=?, EndYear=?, MaxStudents=?, Status=? "
                + "WHERE TopicID=?";
        jdbcTemplate.update(sql, topic.getTopicCode(), topic.getTopicName(), topic.getResearchField(), topic.getDescription(),
                topic.getStartYear(), topic.getEndYear(), topic.getMaxStudents(), topic.getStatus(), topic.getTopicID());
    }
}
