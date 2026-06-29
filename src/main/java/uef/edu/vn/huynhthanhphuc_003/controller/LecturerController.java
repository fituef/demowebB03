package uef.edu.vn.huynhthanhphuc_003.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uef.edu.vn.huynhthanhphuc_003.dao.LecturerDao;
import uef.edu.vn.huynhthanhphuc_003.model.Lecturer;

@Controller
@RequestMapping("/lecturers")
public class LecturerController {
    @Autowired
    private LecturerDao lecturerDao;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("lecturers", lecturerDao.findAll());
        return "lecturers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        model.addAttribute("isEdit", false);
        return "lecturers/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Lecturer lecturer, Model model, RedirectAttributes redirectAttributes) {
        String error = validate(lecturer);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("lecturer", lecturer);
            model.addAttribute("isEdit", false);
            return "lecturers/form";
        }
        try {
            lecturerDao.insert(lecturer);
            redirectAttributes.addFlashAttribute("message", "Added lecturer successfully.");
            return "redirect:/lecturers";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Lecturer code already exists.");
        } catch (DataAccessException e) {
            model.addAttribute("error", "Cannot save lecturer: " + e.getMostSpecificCause().getMessage());
        }
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("isEdit", false);
        return "lecturers/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Lecturer lecturer = lecturerDao.findById(id);
        if (lecturer == null) {
            redirectAttributes.addFlashAttribute("error", "Lecturer not found.");
            return "redirect:/lecturers";
        }
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("isEdit", true);
        return "lecturers/form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Lecturer lecturer, Model model, RedirectAttributes redirectAttributes) {
        String error = validate(lecturer);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("lecturer", lecturer);
            model.addAttribute("isEdit", true);
            return "lecturers/form";
        }
        try {
            lecturerDao.update(lecturer);
            redirectAttributes.addFlashAttribute("message", "Updated lecturer successfully.");
            return "redirect:/lecturers";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Lecturer code already exists.");
        } catch (DataAccessException e) {
            model.addAttribute("error", "Cannot update lecturer: " + e.getMostSpecificCause().getMessage());
        }
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("isEdit", true);
        return "lecturers/form";
    }

    private String validate(Lecturer lecturer) {
        if (isBlank(lecturer.getLecturerCode()) || isBlank(lecturer.getFullName()) || isBlank(lecturer.getFaculty()) || isBlank(lecturer.getEmail())) {
            return "Lecturer code, full name, faculty, and email are required.";
        }
        if (!lecturer.getEmail().contains("@")) {
            return "Email is invalid.";
        }
        return null;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
