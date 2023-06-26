package com.myproject.www.service;

import java.util.List;

import com.myproject.www.domain.CommentVO;

public interface CommentService {

	int register(CommentVO cvo);

	List<CommentVO> getList(int bno);

	int remove(int cno);

	int modify(CommentVO cvo);

	

}
