package eda.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import eda.dao.UserDAO;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	eda.dto.User user;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// to load user from database and create new user(eda.dto) instance
		user.setId(userDAO.findUserByUsername(username).getId());
		user.setEmail(userDAO.findUserByUsername(username).getEmail());
		user.setPassword(userDAO.findUserByUsername(username).getPassword());
		user.setFullName(userDAO.findUserByUsername(username).getFullName());
		if(user == null) {
			throw new UsernameNotFoundException(username+" not found");
		}
		UserDetails currentUser = User.withUsername(user.getEmail()).password(user.getPassword()).roles("").build();
		return currentUser;
	}

}
