package eda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@RequestMapping({"/","/home"})
	public String home() {
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/user/{name}")
	public String user(@PathVariable("name") String name) {
		return "hello "+name;
	}
}
