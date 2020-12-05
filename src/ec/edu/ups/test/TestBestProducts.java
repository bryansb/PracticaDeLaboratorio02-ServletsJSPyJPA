package ec.edu.ups.test;

import java.util.List;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.Product;

public class TestBestProducts {

	public static void main(String[] args) {
		ProductDAO productDAO = DAOFactory.getFactory().getProductDAO();
		String[][] attributes = {{"proId"}};
		String[] values = {"1"};
//		List<Product> products = (List<Product>) productDAO.findByPath(attributes, values, null, 0, 0, false);
		List<Product>  products = productDAO.findBestProductsByComId(1, 5);
		System.out.println(products);
//		for (int i = 0; i < products.size(); i++) {
//			System.out.println(products.get(i));
//		}
		
		List<BillDetail> billDetails = DAOFactory.getFactory().getBillDetailDAO().findByHeaId(0);
		System.out.println(billDetails.get(0));
	}
	
}
