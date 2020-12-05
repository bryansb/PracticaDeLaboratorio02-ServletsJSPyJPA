package ec.edu.ups.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.entities.Product;

public class JPAProductDAO extends JPAGenericDAO<Product, Integer> implements ProductDAO {

	public JPAProductDAO() {
		super(Product.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findBestProductsByComId(int comId, int limit) {
		em.clear();
		String jpql = "SELECT p, COUNT(p) AS total FROM Product p "
				+ "INNER JOIN p.proBillDetails bd "
				+ "INNER JOIN p.proCompany c "
				+ "WHERE c.comId = " + comId + " GROUP BY (p.proId) ORDER BY total DESC";
		Query query = em.createQuery(jpql, Product.class);
		List<Object []>  results = query.setMaxResults(limit).getResultList();
		List<Product> products = new ArrayList<Product>();
		for(Object[] result : results) {
			products.add((Product) result[0]);
		}
		return products;
	}

}
