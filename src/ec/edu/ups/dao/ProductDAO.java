package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.entities.Product;

public interface ProductDAO extends GenericDAO<Product, Integer>{
	public List<Product> findBestProductsByComId(int comId, int limit);
}
