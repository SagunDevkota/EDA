package eda.dao;

import eda.dto.UserInfo;

public interface UserInfoDAO {
	public void saveUserInfo(UserInfo userInfo);
	public boolean updateUserInfo(UserInfo userInfo);
	public boolean removeUserInfo(UserInfo userInfo);
}
