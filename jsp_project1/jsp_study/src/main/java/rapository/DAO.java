package rapository;

import java.util.List;

import domain.ProductVO;

public interface DAO {

	int insert(ProductVO pvo);

	List<ProductVO> selectList();

	

	ProductVO selectone(int pno);

	int editone(ProductVO p1);

	int removeone(int pno3);

	

}
