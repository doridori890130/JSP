package com.myproject.www.repository;

import com.myproject.www.domain.UserVO;

public interface UserDAO {

	UserVO getUser(String id);

	int insertUser(UserVO uvo);

}
