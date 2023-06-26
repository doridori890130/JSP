package com.myproject.www.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myproject.www.domain.UserVO;
import com.myproject.www.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO udao;
	@Inject
	BCryptPasswordEncoder passwordencoder;
	
	
	@Override
	public int signup(UserVO uvo) {
		log.info("log 서비스 진입~!1");
		UserVO tempUser= udao.getUser(uvo.getId());
		if(tempUser != null) {
			return 0;
		}
		
		if(uvo.getId()==null || uvo.getId().length()==0) {
			return 0;
		}
		
		if(uvo.getPw()==null || uvo.getPw().length()==0) {
			return 0;
		}
		
		// 회원가입 진행
		String pw = uvo.getPw();
		String encodePw = passwordencoder.encode(pw);
		uvo.setPw(encodePw);
		int isOk = udao.insertUser(uvo);
		return isOk;
	}


	@Override
	public UserVO isUser(String id, String pw) {
		log.info("isuser 서비스 진입~!1");
		UserVO user = udao.getUser(id);
		
		if(user==null) {return null;}
		if(passwordencoder.matches(pw, user.getPw())) {
			return user;
		}else {
			return null;
		}
	}
}
