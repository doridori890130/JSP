package com.myproject.www.service;

import java.util.List;

import com.myproject.www.domain.BoardDTO;
import com.myproject.www.domain.BoardVO;
import com.myproject.www.domain.PagingVO;
import com.myproject.www.domain.UserVO;

public interface BoardService {

	int register(BoardVO bvo);


	int getTotalCount(PagingVO pvo);


	List<BoardVO> getlist(PagingVO pvo);


	BoardVO getDetail(int bno);


	int readCount(int bno);


	BoardDTO getDetailFile(int bno);


	int register(BoardDTO bdto);


	int modify(BoardDTO bdto, UserVO user);


	int delete(int bno, UserVO user);


	int removeFile(String uuid);

}
