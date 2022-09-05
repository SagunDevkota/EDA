package eda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import eda.dto.User;
import eda.service.EmailSenderService;
import eda.service.UserProfileService;

@Controller
@SessionAttributes("id")
public class HomeController {
	@Autowired
	User user;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	
	@RequestMapping("/")
	public String home() {
		return "minidash";
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpSession session,Model model) {
		session.setAttribute("id", user.getId());
		return "dashboard";
	}
	
	@RequestMapping("/user/{name}")
	public String user(@PathVariable("name") String name,Model model) {
		model.addAttribute("name", name);
		return "user";
	}
		
	@RequestMapping("/auth")
	public String auth() {
		return "auth";
	}
	
	@RequestMapping("/profile")
	public String profile(HttpSession session,Model model) {
		User user2 = userProfileService.getUser((int)session.getAttribute("id"));
		model.addAttribute("user", user2);
		return "profile";
	}
}
