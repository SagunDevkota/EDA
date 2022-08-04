package eda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import eda.dao.UserInfoDAOImpl;
import eda.dto.UserInfo;

@Controller
public class AuthController {
	@Autowired
	UserInfoDAOImpl userInfoDAOImpl;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/process-signup")
	public RedirectView processSignup(@ModelAttribute UserInfo userInfo,RedirectAttributes redir) {
		System.out.println(userInfo.toString());
		RedirectView redirectView = new RedirectView("/login", true);
		try {
			userInfoDAOImpl.saveUserInfo(userInfo);
		}catch(DuplicateKeyException e) {
			redir.addFlashAttribute("error", "User already exists");
			redir.addFlashAttribute("success", false);
			return redirectView;
		}
		redir.addFlashAttribute("success", true);
		return redirectView;
	}
	
	@RequestMapping("/loginError")
	public RedirectView loginError(RedirectAttributes redir) {
		RedirectView redirectView = new RedirectView("/login", true);
		redir.addFlashAttribute("error", "Invalid Credentials");
		redir.addFlashAttribute("success", false);
		return redirectView;
	}
}
