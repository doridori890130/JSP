package com.myproject.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myproject.www.domain.CommentVO;
import com.myproject.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
	
	@Inject
	private CommentDAO cdao;

	@Override
	public int register(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.register(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		// TODO Auto-generated method stub
		return cdao.getList(bno);
	}

	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		return cdao.remove(cno);
	}

	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.modify(cvo);
	}
	


}
