package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;



public class MemberDAOImpl implements MemberDAO {
	private static Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql= DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int register(MemberVO mvo) {
		int isOk=sql.insert(NS+"register",mvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		
		return sql.selectOne(NS+"login",mvo);
	}

	@Override
	public int lastlogin(String id) {
		int isOk=sql.update(NS+"lastlogin",id);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int modify(MemberVO mvo) {
		int isOk=sql.update(NS+"modify",mvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<MemberVO> list() {
		
		return sql.selectList(NS+"list");
	}

	@Override
	public int remove(String delId) {
		int isOk=sql.delete(NS+"del",delId);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}
}