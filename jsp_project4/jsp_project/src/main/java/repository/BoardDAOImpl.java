package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);

	private SqlSession sql;
	private String NS = "boardMapper.";
	private int isOk;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		int isOk=sql.insert(NS+"insert",bvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> list() {
		
		return sql.selectList(NS+"list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		
		return sql.selectOne(NS+"detail", bno);
	}
	
	@Override
	public int updateCount(int bno) {
		isOk = sql.update(NS+"count", bno);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		isOk = sql.insert(NS+"up", bvo);
		if(isOk > 0) { sql.commit(); }
		return isOk;
	}

	@Override
	public int delete(int bno) {
		isOk = sql.delete(NS+"del", bno);
		if(isOk>0)sql.commit();
		return isOk;
		
	}

	@Override
	public int totCount(PagingVO pgvo) {
		
		return sql.selectOne(NS+"cnt",pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		
		return sql.selectList(NS+"selectList",pgvo);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"removeFile",bno);
	}

}