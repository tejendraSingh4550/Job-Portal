package com.tejendra.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tejendra.jobportal.model.Job;
import com.tejendra.jobportal.model.User;
import com.tejendra.jobportal.repository.JobRepository;
import com.tejendra.jobportal.repository.UserRepository;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/do-login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            return "redirect:/jobs";
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "login";
        }
    }

    @GetMapping("/jobs")
    public String jobs(Model model) {
        model.addAttribute("jobs", jobRepository.findAll());
        return "jobs";
    }

    @GetMapping("/post-job")
    public String postJob(Model model) {
        model.addAttribute("job", new Job());
        return "post-job";
    }

    @PostMapping("/save-job")
    public String saveJob(@ModelAttribute Job job) {
        jobRepository.save(job);
        return "redirect:/jobs";
    }
}