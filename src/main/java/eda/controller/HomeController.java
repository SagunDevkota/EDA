package eda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping({"/","/dashboard"})
	public String home() {
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
