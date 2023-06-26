package com.myproject.www.service;

import com.myproject.www.domain.UserVO;

public interface UserService {

	int signup(UserVO uvo);

	UserVO isUser(String id, String pw);

}
