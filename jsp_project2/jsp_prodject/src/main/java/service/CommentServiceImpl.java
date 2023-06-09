package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {

	 private static Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	 private CommentDAO cdao;
	 
	 public CommentServiceImpl() {
		 cdao = new CommentDAOImpl();
	 }

	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.post(cvo);
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
	public int update(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}
}
