package eda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String processSignup(@ModelAttribute UserInfo userInfo) {
		System.out.println(userInfo.toString());
		try {
			userInfoDAOImpl.saveUserInfo(userInfo);
		}catch(DuplicateKeyException e) {
			return "redirect:auth?error=User already exists";
		}
		return "redirect:auth?success=true";
	}
}
