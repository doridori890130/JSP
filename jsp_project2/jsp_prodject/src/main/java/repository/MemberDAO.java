package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	int insert(MemberVO mvo);

	MemberVO selectOne(MemberVO mvo2);

	int lastLoin(String id2);

	

	int edit(MemberVO mvo2);

	int remove(String id2);

	List<MemberVO> list();

	

	

	

}
