package uef.edu.vn.huynhthanhphuc_003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uef.edu.vn.huynhthanhphuc_003.dao.RegistrationDao;
import uef.edu.vn.huynhthanhphuc_003.dao.StudentDao;
import uef.edu.vn.huynhthanhphuc_003.dao.TopicDao;
import uef.edu.vn.huynhthanhphuc_003.model.Topic;

@Controller
@RequestMapping("/student-registrations")
public class StudentRegistrationController {
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private RegistrationDao registrationDao;

    @GetMapping("/available")
    public String available(Model model) {
        addAvailableData(model);
        return "studentregistrations/available";
    }

    @PostMapping("/register")
    public String register(@RequestParam("studentID") int studentID,
                           @RequestParam("topicID") int topicID,
                           @RequestParam("participationRole") String participationRole,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        String error = validateRegistration(studentID, topicID, participationRole);
        if (error != null) {
            addAvailableData(model);
            model.addAttribute("error", error);
            model.addAttribute("studentID", studentID);
            model.addAttribute("topicID", topicID);
            model.addAttribute("participationRole", participationRole);
            return "studentregistrations/available";
        }
        try {
            registrationDao.register(topicID, studentID, participationRole);
            redirectAttributes.addFlashAttribute("message", "Registered topic successfully. Status is Pending.");
            return "redirect:/student-registrations/my?studentID=" + studentID;
        } catch (DataAccessException e) {
            addAvailableData(model);
            model.addAttribute("error", "Cannot register topic: " + e.getMostSpecificCause().getMessage());
            return "studentregistrations/available";
        }
    }

    @GetMapping("/my")
    public String myRegistrations(@RequestParam(value = "studentID", required = false, defaultValue = "0") int studentID, Model model) {
        model.addAttribute("students", studentDao.findAll());
        model.addAttribute("studentID", studentID);
        if (studentID > 0) {
            model.addAttribute("registrations", registrationDao.findByStudent(studentID));
        }
        return "studentregistrations/my";
    }

    private void addAvailableData(Model model) {
        model.addAttribute("topics", topicDao.findAvailableTopics());
        model.addAttribute("students", studentDao.findAll());
    }

    private String validateRegistration(int studentID, int topicID, String participationRole) {
        if (!studentDao.exists(studentID)) {
            return "Student does not exist.";
        }
        Topic topic = topicDao.findById(topicID);
        if (topic == null) {
            return "Topic does not exist.";
        }
        if (!"Team Leader".equals(participationRole) && !"Team Member".equals(participationRole)) {
            return "Participation role must be Team Leader or Team Member.";
        }
        if (!"Open".equals(topic.getStatus())) {
            return "This topic is closed.";
        }
        if (topicDao.countRegisteredStudents(topicID) >= topic.getMaxStudents()) {
            return "This topic has reached the maximum number of students.";
        }
        if (registrationDao.hasActiveRegistration(studentID)) {
            return "This student has already registered for another topic.";
        }
        return null;
    }
}
