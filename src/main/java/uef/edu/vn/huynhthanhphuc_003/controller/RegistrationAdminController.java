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

@Controller
@RequestMapping("/registrations")
public class RegistrationAdminController {
    @Autowired
    private RegistrationDao registrationDao;

    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("registrations", registrationDao.findAll(keyword));
        model.addAttribute("keyword", keyword);
        return "registrations/list";
    }

    @PostMapping("/update-status")
    public String updateStatus(@RequestParam("registrationID") int registrationID,
                               @RequestParam("registrationStatus") String registrationStatus,
                               RedirectAttributes redirectAttributes) {
        if (!"Pending".equals(registrationStatus) && !"Approved".equals(registrationStatus) && !"Rejected".equals(registrationStatus)) {
            redirectAttributes.addFlashAttribute("error", "Invalid registration status.");
            return "redirect:/registrations";
        }
        try {
            registrationDao.updateStatus(registrationID, registrationStatus);
            redirectAttributes.addFlashAttribute("message", "Updated registration status successfully.");
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("error", "Cannot update registration status: " + e.getMostSpecificCause().getMessage());
        }
        return "redirect:/registrations";
    }
}
