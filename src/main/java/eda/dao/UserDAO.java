package eda.dao;

import eda.dto.User;

public interface UserDAO {
	User findUserByUsername(String name);
}
