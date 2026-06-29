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
import uef.edu.vn.huynhthanhphuc_003.dao.StudentDao;
import uef.edu.vn.huynhthanhphuc_003.model.Student;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentDao.findAll());
        return "students/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("isEdit", false);
        return "students/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Student student, Model model, RedirectAttributes redirectAttributes) {
        String error = validate(student);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("student", student);
            model.addAttribute("isEdit", false);
            return "students/form";
        }
        try {
            studentDao.insert(student);
            redirectAttributes.addFlashAttribute("message", "Added student successfully.");
            return "redirect:/students";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Student code already exists.");
        } catch (DataAccessException e) {
            model.addAttribute("error", "Cannot save student: " + e.getMostSpecificCause().getMessage());
        }
        model.addAttribute("student", student);
        model.addAttribute("isEdit", false);
        return "students/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Student student = studentDao.findById(id);
        if (student == null) {
            redirectAttributes.addFlashAttribute("error", "Student not found.");
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "students/form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Student student, Model model, RedirectAttributes redirectAttributes) {
        String error = validate(student);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("student", student);
            model.addAttribute("isEdit", true);
            return "students/form";
        }
        try {
            studentDao.update(student);
            redirectAttributes.addFlashAttribute("message", "Updated student successfully.");
            return "redirect:/students";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Student code already exists.");
        } catch (DataAccessException e) {
            model.addAttribute("error", "Cannot update student: " + e.getMostSpecificCause().getMessage());
        }
        model.addAttribute("student", student);
        model.addAttribute("isEdit", true);
        return "students/form";
    }

    private String validate(Student student) {
        if (isBlank(student.getStudentCode()) || isBlank(student.getFullName()) || isBlank(student.getMajor()) || isBlank(student.getEmail())) {
            return "Student code, full name, major, and email are required.";
        }
        if (!student.getEmail().contains("@")) {
            return "Email is invalid.";
        }
        return null;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
