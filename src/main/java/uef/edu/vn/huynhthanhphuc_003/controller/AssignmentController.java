package uef.edu.vn.huynhthanhphuc_003.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uef.edu.vn.huynhthanhphuc_003.dao.AssignmentDao;
import uef.edu.vn.huynhthanhphuc_003.dao.LecturerDao;
import uef.edu.vn.huynhthanhphuc_003.dao.TopicDao;
import uef.edu.vn.huynhthanhphuc_003.model.AssignmentView;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {
    @Autowired
    private AssignmentDao assignmentDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private LecturerDao lecturerDao;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assignments", assignmentDao.findAll());
        return "assignments/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        addFormData(model);
        model.addAttribute("assignmentDate", LocalDate.now().toString());
        model.addAttribute("isEdit", false);
        return "assignments/form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("topicID") int topicID,
                       @RequestParam("lecturerID") int lecturerID,
                       @RequestParam("assignmentDate") String assignmentDate,
                       Model model,
                       RedirectAttributes redirectAttributes) {
        String error = validate(topicID, lecturerID, assignmentDate);
        if (error != null) {
            addFormData(model);
            model.addAttribute("error", error);
            model.addAttribute("topicID", topicID);
            model.addAttribute("lecturerID", lecturerID);
            model.addAttribute("assignmentDate", assignmentDate);
            model.addAttribute("isEdit", false);
            return "assignments/form";
        }
        try {
            assignmentDao.saveOrUpdateByTopic(topicID, lecturerID, LocalDate.parse(assignmentDate));
            redirectAttributes.addFlashAttribute("message", "Saved supervising lecturer assignment successfully.");
            return "redirect:/assignments";
        } catch (DataAccessException e) {
            addFormData(model);
            model.addAttribute("error", "Cannot save assignment: " + e.getMostSpecificCause().getMessage());
            model.addAttribute("isEdit", false);
            return "assignments/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        AssignmentView assignment = assignmentDao.findById(id);
        if (assignment == null) {
            redirectAttributes.addFlashAttribute("error", "Assignment not found.");
            return "redirect:/assignments";
        }
        addFormData(model);
        model.addAttribute("assignment", assignment);
        model.addAttribute("topicID", assignment.getTopicID());
        model.addAttribute("lecturerID", assignment.getLecturerID());
        model.addAttribute("assignmentDate", assignment.getAssignmentDate().toString());
        model.addAttribute("isEdit", true);
        return "assignments/form";
    }

    @PostMapping("/update")
    public String update(@RequestParam("assignmentID") int assignmentID,
                         @RequestParam("topicID") int topicID,
                         @RequestParam("lecturerID") int lecturerID,
                         @RequestParam("assignmentDate") String assignmentDate,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        String error = validate(topicID, lecturerID, assignmentDate);
        if (error != null) {
            addFormData(model);
            model.addAttribute("error", error);
            model.addAttribute("assignmentID", assignmentID);
            model.addAttribute("topicID", topicID);
            model.addAttribute("lecturerID", lecturerID);
            model.addAttribute("assignmentDate", assignmentDate);
            model.addAttribute("isEdit", true);
            return "assignments/form";
        }
        try {
            assignmentDao.update(assignmentID, topicID, lecturerID, LocalDate.parse(assignmentDate));
            redirectAttributes.addFlashAttribute("message", "Updated assignment successfully.");
            return "redirect:/assignments";
        } catch (DataAccessException e) {
            addFormData(model);
            model.addAttribute("error", "Cannot update assignment: " + e.getMostSpecificCause().getMessage());
            model.addAttribute("isEdit", true);
            return "assignments/form";
        }
    }

    private void addFormData(Model model) {
        model.addAttribute("topics", topicDao.findAll(null));
        model.addAttribute("lecturers", lecturerDao.findAll());
    }

    private String validate(int topicID, int lecturerID, String assignmentDate) {
        if (!topicDao.exists(topicID)) {
            return "Cannot assign lecturer to a non-existing topic.";
        }
        if (!lecturerDao.exists(lecturerID)) {
            return "Lecturer does not exist.";
        }
        try {
            LocalDate.parse(assignmentDate);
        } catch (Exception e) {
            return "Assignment date is invalid.";
        }
        return null;
    }
}
