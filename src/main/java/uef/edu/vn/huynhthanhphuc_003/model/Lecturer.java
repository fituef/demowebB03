package uef.edu.vn.huynhthanhphuc_003.model;

public class Lecturer {
    private int lecturerID;
    private String lecturerCode;
    private String fullName;
    private String faculty;
    private String email;
    private String phoneNumber;

    public int getLecturerID() { return lecturerID; }
    public void setLecturerID(int lecturerID) { this.lecturerID = lecturerID; }
    public String getLecturerCode() { return lecturerCode; }
    public void setLecturerCode(String lecturerCode) { this.lecturerCode = lecturerCode; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
