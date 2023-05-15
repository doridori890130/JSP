package service;

import java.util.List;

import domain.ProductVO;
import rapository.DAO;
import rapository.ProductDAO;

public class ProductService implements Service {
	private DAO dao;
	
	public ProductService() {
		dao = new ProductDAO();
	}
	@Override
	public int register(ProductVO pvo) {
		// JSP에서 받은 객체 PVO를 가지고 DB에 넣어달라고 요청 dao
		
		System.out.println(">> register service 진입!!");
		return dao.insert(pvo);
	}
	@Override
	public List<ProductVO> list() {
		System.out.println(">> list service 진입!!");
		return dao.selectList();
	}
	@Override
	public ProductVO detail(int pno) {
		System.out.println(">> detail service 진입!!");
		return dao.selectone(pno);
	}
	@Override
	public int edit(ProductVO p1) {
		System.out.println(">> edit service 진입!!");
		return dao.editone(p1);
	}
	@Override
	public int remove(int pno3) {
		System.out.println(">> remove service 진입!!");
		return dao.removeone(pno3);
	}


}
