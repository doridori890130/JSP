package com.myproject.www.repository;

import java.util.List;

import com.myproject.www.domain.BoardVO;
import com.myproject.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	int gettotalcount(PagingVO pvo);

	List<BoardVO> selectBoardListPaging(PagingVO pvo);

	BoardVO getDetail(int bno);

	int readCount(int bno);

	int selectBno();

	int modify(BoardVO bvo);

	int delete(int bno);


}
