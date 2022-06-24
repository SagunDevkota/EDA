package eda.service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eda.dao.UserDAO;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		eda.dto.User user = userDAO.findUserByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException(username+" not found");
		}
		UserDetails currentUser = User.withUsername(user.getEmail()).password(user.getPassword()).roles("").build();
		return currentUser;
	}

}
