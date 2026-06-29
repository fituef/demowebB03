package uef.edu.vn.huynhthanhphuc_003.model;

public class Topic {
    private int topicID;
    private String topicCode;
    private String topicName;
    private String researchField;
    private String description;
    private int startYear;
    private int endYear;
    private int maxStudents;
    private String status;
    private int registeredCount;

    public int getTopicID() { return topicID; }
    public void setTopicID(int topicID) { this.topicID = topicID; }
    public String getTopicCode() { return topicCode; }
    public void setTopicCode(String topicCode) { this.topicCode = topicCode; }
    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
    public String getResearchField() { return researchField; }
    public void setResearchField(String researchField) { this.researchField = researchField; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getStartYear() { return startYear; }
    public void setStartYear(int startYear) { this.startYear = startYear; }
    public int getEndYear() { return endYear; }
    public void setEndYear(int endYear) { this.endYear = endYear; }
    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getRegisteredCount() { return registeredCount; }
    public void setRegisteredCount(int registeredCount) { this.registeredCount = registeredCount; }
}
