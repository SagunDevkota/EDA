package eda.dao;

import java.sql.Types;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.SessionAttribute;

import eda.dto.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public User findUserByUsername(String name) {
		//Search user in database
		String query = "SELECT * FROM Users WHERE email = ?";
		Object[] args = new Object[] {name};
		int[] argsType = new int[] {Types.VARCHAR};
		List<User> users = jdbcTemplate.query(query, args,argsType,new UserRowMapperImpl());
		return users.size()>0?users.get(0):null;
	}
	
	@Override
	public User findUserById(int id) {
		String query = "SELECT * FROM Users WHERE id = ?";
		Object[] args = new Object[] {id};
		int[] argsType = new int[] {Types.INTEGER};
		List<User> users = jdbcTemplate.query(query, args,argsType,new UserRowMapperImpl());
		return users.size()>0?users.get(0):null;
	}
}
