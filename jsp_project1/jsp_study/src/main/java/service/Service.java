package service;

import java.util.List;

import domain.ProductVO;

public interface Service {

	int register(ProductVO pvo);

	List<ProductVO> list();

	ProductVO detail(int pno);

	int edit(ProductVO p1);

	int remove(int pno3);

	

}
