package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	int register(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int lastlogin(String id);

	int modify(MemberVO mvo);

	List<MemberVO> list();

	int remove(String delId);

}
