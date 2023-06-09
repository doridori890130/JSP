package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.Databasebuilder;

public class CommentDAOImpl implements CommentDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	//NameSpace = SQL 이 mapper 를 구분하기 위한 이름
	//NameSpace.SQL구문이름
	private String NS="CommentMapper.";
	
	public CommentDAOImpl() {
		new Databasebuilder();
		sql= Databasebuilder.getFactory().openSession();
	}

	@Override
	public int post(CommentVO cvo) {
		int isOk= sql.insert(NS+"insert",cvo);
		log.info("isok: "+isOk);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<CommentVO> getList(int bno) {
		// TODO Auto-generated method stub
		return sql.selectList(NS+"list",bno);
	}

	@Override
	public int remove(int cno) {
		int isOk = sql.delete(NS+"remove",cno);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int update(CommentVO cvo) {
		int isOk =sql.update(NS+"update",cvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}
}
