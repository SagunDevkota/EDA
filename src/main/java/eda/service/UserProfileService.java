package eda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eda.dao.UserDAOImpl;
import eda.dto.User;

@Service
public class UserProfileService {
	@Autowired
	UserDAOImpl userDAOImpl;
	
	public User getUser(int id) {
		return userDAOImpl.findUserById(id);
	}
}
