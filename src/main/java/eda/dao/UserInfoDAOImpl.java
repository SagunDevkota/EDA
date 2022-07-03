package eda.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import eda.dto.UserInfo;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public void saveUserInfo(UserInfo userInfo) {
		String query = "INSERT INTO users (`email`,`password`,`full_name`) VALUES (?,?,?)";
		String encodedPassword = passwordEncoder.encode(userInfo.getPassword());
		jdbcTemplate.update(query,userInfo.getUsername(),encodedPassword,userInfo.getName());
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
