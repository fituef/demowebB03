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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uef.edu.vn.huynhthanhphuc_003.dao.TopicDao;
import uef.edu.vn.huynhthanhphuc_003.model.Topic;

@Controller
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private TopicDao topicDao;

    @GetMapping
    public String list(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("topics", topicDao.findAll(keyword));
        model.addAttribute("keyword", keyword);
        return "topics/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        Topic topic = new Topic();
        topic.setStatus("Open");
        model.addAttribute("topic", topic);
        model.addAttribute("isEdit", false);
        return "topics/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Topic topic, Model model, RedirectAttributes redirectAttributes) {
        String error = validate(topic);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("topic", topic);
            model.addAttribute("isEdit", false);
            return "topics/form";
        }
        try {
            topicDao.insert(topic);
            redirectAttributes.addFlashAttribute("message", "Added topic successfully.");
            return "redirect:/topics";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Topic code already exists.");
        } catch (DataAccessException e) {
            model.addAttribute("error", "Cannot save topic: " + e.getMostSpecificCause().getMessage());
        }
        model.addAttribute("topic", topic);
        model.addAttribute("isEdit", false);
        return "topics/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Topic topic = topicDao.findById(id);
        if (topic == null) {
            redirectAttributes.addFlashAttribute("error", "Topic not found.");
            return "redirect:/topics";
        }
        model.addAttribute("topic", topic);
        model.addAttribute("isEdit", true);
        return "topics/form";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Topic topic, Model model, RedirectAttributes redirectAttributes) {
        String error = validate(topic);
        if (error != null) {
            model.addAttribute("error", error);
            model.addAttribute("topic", topic);
            model.addAttribute("isEdit", true);
            return "topics/form";
        }
        try {
            topicDao.update(topic);
            redirectAttributes.addFlashAttribute("message", "Updated topic successfully.");
            return "redirect:/topics";
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Topic code already exists.");
        } catch (DataAccessException e) {
            model.addAttribute("error", "Cannot update topic: " + e.getMostSpecificCause().getMessage());
        }
        model.addAttribute("topic", topic);
        model.addAttribute("isEdit", true);
        return "topics/form";
    }

    private String validate(Topic topic) {
        if (isBlank(topic.getTopicCode()) || isBlank(topic.getTopicName()) || isBlank(topic.getResearchField())) {
            return "Topic code, topic name, and research field are required.";
        }
        if (topic.getStartYear() <= 0 || topic.getEndYear() <= 0 || topic.getEndYear() < topic.getStartYear()) {
            return "End year must be greater than or equal to start year.";
        }
        if (topic.getMaxStudents() <= 0) {
            return "Maximum number of students must be greater than 0.";
        }
        if (!"Open".equals(topic.getStatus()) && !"Closed".equals(topic.getStatus())) {
            return "Status must be Open or Closed.";
        }
        return null;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
