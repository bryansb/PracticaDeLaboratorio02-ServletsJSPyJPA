package ec.edu.ups.test;

import java.util.List;

import ec.edu.ups.dao.CategoryDAO;
import ec.edu.ups.dao.CompanyDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.entities.Category;
import ec.edu.ups.entities.Company;
import ec.edu.ups.entities.Product;
import ec.edu.ups.entities.User;

public class TestJoin {

	public static void main(String[] args) {
//		EntityManager em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		// Empresas
		CompanyDAO companyDAO = DAOFactory.getFactory().getCompanyDAO();
		
		Company nuvaT = new Company();
		nuvaT.setComName("Nuva T");
		companyDAO.create(nuvaT);
		Company nuvaF = new Company();
		nuvaF.setComName("Nuva F");
		companyDAO.create(nuvaF);
		Company nuvaH = new Company();
		nuvaH.setComName("Nuva H");
		companyDAO.create(nuvaH);
		
		Company com = companyDAO.read(1);
		
		// Categorias
		CategoryDAO categoryDAO = DAOFactory.getFactory().getCategoryDAO();
		Category electronics = new Category();
		electronics.setCatName("Electronicos");
		categoryDAO.create(electronics);
		Category food = new Category();
		food.setCatName("Comida");
		categoryDAO.create(food);
		Category toy = new Category();
		toy.setCatName("Juguetes");
		categoryDAO.create(toy);
		Category furniture = new Category();
		furniture.setCatName("Muebles");
		categoryDAO.create(furniture);
		Category clothes = new Category();
		clothes.setCatName("Ropa");
		categoryDAO.create(clothes);
		
		Category cat = categoryDAO.read(1);
		
		Category cat2 = categoryDAO.read(2);
		Category cat3 = categoryDAO.read(3);
		Category cat4 = categoryDAO.read(4);
		
		// Productos
		ProductDAO productDAO = DAOFactory.getFactory().getProductDAO();
		
		Product mouse = new Product();
		mouse.setProName("Black Widow");
		mouse.setProStock(10);
		mouse.setProPrice(50.25);
		mouse.setProCategory(cat);
		mouse.setProCompany(com);
		productDAO.create(mouse);
		
		Product headset = new Product();
		headset.setProName("Corsair Virtuoso");
		headset.setProStock(10);
		headset.setProPrice(50.25);
		headset.setProCategory(cat2);
		headset.setProCompany(com);
		productDAO.create(headset);
		
		Product pizza = new Product();
		pizza.setProName("Pizza Napolitana");
		pizza.setProStock(10);
		pizza.setProPrice(50.25);
		pizza.setProCategory(cat);
		pizza.setProCompany(com);
		productDAO.create(pizza);
		
		Product taco = new Product();
		taco.setProName("Taco de pollo");
		taco.setProStock(10);
		taco.setProPrice(50.25);
		taco.setProCategory(cat3);
		taco.setProCompany(com);
		productDAO.create(taco);
		
		Product pant = new Product();
		pant.setProName("Pantalon Negro");
		pant.setProStock(10);
		pant.setProPrice(50.25);
		pant.setProCategory(cat);
		pant.setProCompany(com);
		productDAO.create(pant);
		
		Product suit = new Product();
		suit.setProName("Traje");
		suit.setProStock(10);
		suit.setProPrice(50.25);
		suit.setProCategory(cat);
		suit.setProCompany(com);
		productDAO.create(suit);
		
		Company com2 = companyDAO.read(2);
		
		Product funko = new Product();
		funko.setProName("Funko Pop");
		funko.setProStock(10);
		funko.setProPrice(50.25);
		funko.setProCategory(cat);
		funko.setProCompany(com2);
		productDAO.create(funko);
		
		Product teddy = new Product();
		teddy.setProName("Peluche");
		teddy.setProStock(10);
		teddy.setProPrice(50.25);
		funko.setProCategory(cat2);
		funko.setProCompany(com2);
		productDAO.create(funko);
		
		Product chair = new Product();
		chair.setProName("Silla");
		chair.setProStock(10);
		chair.setProPrice(50.25);
		chair.setProCategory(cat);
		chair.setProCompany(com2);
		productDAO.create(chair);
		
		Product table = new Product();
		table.setProName("Mesa");
		table.setProStock(10);
		table.setProPrice(50.25);
		table.setProCategory(cat4);
		table.setProCompany(com2);
		productDAO.create(table);
		
		// Usuarios
		UserDAO userDAO = DAOFactory.getFactory().getUserDAO();
		
		User bryan = new User();
		bryan.setUseEmail("sarmiento@example.com");
		bryan.setUseUsername("bryansb");
		bryan.setUseName("Bryan");
		bryan.setUseLastname("Sarmiento");
		bryan.setUsePassword("123");
		bryan.setUseRole('A');
		bryan.setUseCompany(com);
		userDAO.create(bryan);
		
		User eduardo = new User();
		eduardo.setUseEmail("edd@example.com");
		eduardo.setUseUsername("eddZ");
		eduardo.setUseName("Eduardo");
		eduardo.setUseLastname("Zhizhpon");
		eduardo.setUsePassword("123");
		eduardo.setUseRole('U');
		
		User roberto = new User();
		roberto.setUseEmail("roby@example.com");
		roberto.setUseUsername("robyS");
		roberto.setUseName("Roberto");
		roberto.setUseLastname("Serpa");
		roberto.setUsePassword("123");
		roberto.setUseRole('U');
		
		User fumino = new User();
		fumino.setUseEmail("sakura@example.com");
		fumino.setUseUsername("haru12");
		fumino.setUseName("Fumino");
		fumino.setUseLastname("Sakurajima");
		fumino.setUsePassword("123");
		fumino.setUseRole('A');
		
		String[] classes = {"catProducts", "proCompany"};
		String[][] attributes = {{""}, {"comId"}};
		String[] values = {"", "1"};
 		List<Category> products = categoryDAO.findByJoin(classes, attributes, values, "catName", 0, 0, true);
 		
 		System.out.println(products);
 		
		String[][] attributes1 = {{"proCategory", "catId"}};
		String[] values1 = {"1"};
		List<Product> productsCat = productDAO.findByPath(attributes1, values1, null, 0, 0, true);
 		System.out.println(productsCat);
 		
 		String[][] attributes2 = {{"proCategory", "catId"}, {"proName"}};
		String[] values2 = {"1", "Black Widow"};
		List<Product> productsCat2 = productDAO.findByPath(attributes2, values2, null, 0, 0, false);
 		System.out.println(productsCat2);
	}

}
