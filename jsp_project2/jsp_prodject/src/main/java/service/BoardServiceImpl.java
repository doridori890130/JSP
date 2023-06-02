package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	
	private static final Logger log= LoggerFactory.getLogger(BoardController.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info(">>> insert service 진입~!!");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> list() {
		log.info(">>> list service 진입~!!");
		return bdao.list();
	}

	@Override
	public BoardVO detail(int bno) {
		log.info(">>> detail service 진입~!!");
		//read_count update 요청 후 detail값을 요청
		int isOk = bdao.updateCount(bno);
	
		return (isOk>0)? bdao.detail(bno) : null;
	}

	@Override
	public BoardVO modify(int bno) {
		log.info(">>> modify service 진입~!!");
		return bdao.modify(bno);
	}

	@Override
	public int edit(BoardVO bvo) {
		log.info(">>> edit service 진입~!!");
		return bdao.edit(bvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		
		return bdao.getPageList(pgvo);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.totCount(pgvo);
	}

	@Override
	public int delete(int bno) {
		// TODO Auto-generated method stub
		return bdao.delete(bno);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return bdao.getFileName(bno);
	}





}
