package uef.edu.vn.huynhthanhphuc_003.model;

import java.time.LocalDate;

public class AssignmentView {
    private int assignmentID;
    private int topicID;
    private int lecturerID;
    private String topicCode;
    private String topicName;
    private String lecturerCode;
    private String lecturerName;
    private LocalDate assignmentDate;

    public int getAssignmentID() { return assignmentID; }
    public void setAssignmentID(int assignmentID) { this.assignmentID = assignmentID; }
    public int getTopicID() { return topicID; }
    public void setTopicID(int topicID) { this.topicID = topicID; }
    public int getLecturerID() { return lecturerID; }
    public void setLecturerID(int lecturerID) { this.lecturerID = lecturerID; }
    public String getTopicCode() { return topicCode; }
    public void setTopicCode(String topicCode) { this.topicCode = topicCode; }
    public String getTopicName() { return topicName; }
    public void setTopicName(String topicName) { this.topicName = topicName; }
    public String getLecturerCode() { return lecturerCode; }
    public void setLecturerCode(String lecturerCode) { this.lecturerCode = lecturerCode; }
    public String getLecturerName() { return lecturerName; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
    public LocalDate getAssignmentDate() { return assignmentDate; }
    public void setAssignmentDate(LocalDate assignmentDate) { this.assignmentDate = assignmentDate; }
}
