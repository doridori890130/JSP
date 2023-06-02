package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public int register(MemberVO mvo) {
		log.info(">>> register service 진입~!!");
		
		return mdao.insert(mvo);
	}

	@Override
	public MemberVO login(MemberVO mvo2) {
		log.info(">>> login service 진입~!!");
		return mdao.selectOne(mvo2);
	}

	@Override
	public int lastLogin(String id2) {
		log.info(">>> logout service 진입~!!");
		return mdao.lastLoin(id2);
	}



	@Override
	public int edit(MemberVO mvo2) {
		log.info(">>> edit service 진입~!!");
		return mdao.edit(mvo2);
	}

	@Override
	public int remove(String id2) {
		log.info(">>> remove service 진입~!!");
		return mdao.remove(id2);
	}

	@Override
	public List<MemberVO> list() {
		log.info(">>> list service 진입~!!");
		return mdao.list();
	}






	}





