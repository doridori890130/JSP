package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;

	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int insert(BoardVO bvo) {
		
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList() {
		
		return bdao.list();
	}

	@Override
	public BoardVO getdetail(int bno) {
		int isOk = bdao.updateCount(bno);
		try {
			Thread.sleep(500); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (isOk > 0)? bdao.selectOne(bno) : null;
	}

	@Override
	public int update(BoardVO bvo) {
		
		return bdao.update(bvo);
	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.totCount(pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.getPageList(pgvo);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return bdao.getFileName(bno);
	}
}
