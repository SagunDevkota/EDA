package eda.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import eda.dto.User;

@Controller
@SessionAttributes("id")
public class HomeController {
	@Autowired
	User user;
	
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
}
