package ec.edu.ups.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;
import ec.edu.ups.entities.Category;
import ec.edu.ups.entities.Company;
import ec.edu.ups.entities.Product;
import ec.edu.ups.entities.User;
import ec.edu.ups.resources.MathFunction;

public class CreateTable {
	
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		
		// Empresas
		Company nuvaT = new Company();
		nuvaT.setComName("Nuva T");
		
		Company nuvaF = new Company();
		nuvaF.setComName("Nuva F");
		
		Company nuvaH = new Company();
		nuvaH.setComName("Nuva H");
		
		try {
			em.getTransaction().begin();
			em.persist(nuvaH);
			em.persist(nuvaF);
			em.persist(nuvaT);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERROR:CreateCompanies"+e.getMessage());
		}
		
		// Categorias
		Category electronics = new Category();
		electronics.setCatName("Electronicos");
		
		Category food = new Category();
		food.setCatName("Comida");
		
		Category toy = new Category();
		toy.setCatName("Juguetes");
		
		Category furniture = new Category();
		furniture.setCatName("Muebles");
		
		Category clothes = new Category();
		clothes.setCatName("Ropa");
		
		try {
			em.getTransaction().begin();
			em.persist(electronics);
			em.persist(toy);
			em.persist(furniture);
			em.persist(clothes);
			em.persist(food);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERROR:CreateCategories"+e.getMessage());
		}
		
		// Usuarios
		User bryan = new User();
		bryan.setUseEmail("sarmiento@example.com");
		bryan.setUseUsername("bryansb");
		bryan.setUseName("Bryan");
		bryan.setUseLastname("Sarmiento");
		bryan.setUsePassword(MathFunction.getMd5("123"));
		bryan.setUseRole('A');
		bryan.setUseCompany(nuvaT);
		
		User eduardo = new User();
		eduardo.setUseEmail("edd@example.com");
		eduardo.setUseUsername("eddZ");
		eduardo.setUseName("Eduardo");
		eduardo.setUseLastname("Zhizhpon");
		eduardo.setUsePassword(MathFunction.getMd5("123"));
		eduardo.setUseRole('A');
		eduardo.setUseCompany(nuvaF);
		
		User roberto = new User();
		roberto.setUseEmail("roby@example.com");
		roberto.setUseUsername("robyS");
		roberto.setUseName("Roberto");
		roberto.setUseLastname("Serpa");
		roberto.setUsePassword(MathFunction.getMd5("123"));
		roberto.setUseRole('U');
		roberto.setUseCompany(nuvaT);
		
		User fumino = new User();
		fumino.setUseEmail("sakura@example.com");
		fumino.setUseUsername("haru12");
		fumino.setUseName("Fumino");
		fumino.setUseLastname("Sakurajima");
		fumino.setUsePassword(MathFunction.getMd5("123"));
		fumino.setUseRole('A');
		fumino.setUseCompany(nuvaF);
		
		
		try {
			em.getTransaction().begin();
			em.persist(bryan);
			em.persist(fumino);
			em.persist(eduardo);
			em.persist(roberto);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERROR:CreateUsers"+e.getMessage());
		}
		
		// Productos
		Product mouse = new Product();
		mouse.setProName("Black Widow");
		mouse.setProStock(10);
		mouse.setProPrice(50.25);
		mouse.setProCategory(electronics);
		mouse.setProCompany(nuvaT);
		
		Product headset = new Product();
		headset.setProName("Corsair Virtuoso");
		headset.setProStock(10);
		headset.setProPrice(50.25);
		headset.setProCategory(electronics);
		headset.setProCompany(nuvaT);
		
		Product pizza = new Product();
		pizza.setProName("Pizza Napolitana");
		pizza.setProStock(10);
		pizza.setProPrice(50.25);
		pizza.setProCategory(food);
		pizza.setProCompany(nuvaH);
		
		Product taco = new Product();
		taco.setProName("Taco de pollo");
		taco.setProStock(10);
		taco.setProPrice(50.25);
		taco.setProCategory(food);
		taco.setProCompany(nuvaH);
		
		Product pant = new Product();
		pant.setProName("Pantalon Negro");
		pant.setProStock(10);
		pant.setProPrice(50.25);
		pant.setProCategory(clothes);
		pant.setProCompany(nuvaH);
		
		Product suit = new Product();
		suit.setProName("Traje");
		suit.setProStock(10);
		suit.setProPrice(50.25);
		suit.setProCategory(clothes);
		suit.setProCompany(nuvaH);
		
		Product funko = new Product();
		funko.setProName("Funko Pop");
		funko.setProStock(10);
		funko.setProPrice(50.25);
		funko.setProCategory(toy);
		funko.setProCompany(nuvaT);
		
		Product teddy = new Product();
		teddy.setProName("Peluche");
		teddy.setProStock(10);
		teddy.setProPrice(50.25);
		teddy.setProCategory(toy);
		teddy.setProCompany(nuvaH);
		
		Product chair = new Product();
		chair.setProName("Silla");
		chair.setProStock(10);
		chair.setProPrice(50.25);
		chair.setProCategory(furniture);
		chair.setProCompany(nuvaF);
		
		Product table = new Product();
		table.setProName("Mesa");
		table.setProStock(10);
		table.setProPrice(50.25);
		table.setProCategory(furniture);
		table.setProCompany(nuvaF);
		
		try {
			em.getTransaction().begin();
			em.persist(chair);
			em.persist(table);
			em.persist(funko);
			em.persist(suit);
			em.persist(pant);
			em.persist(mouse);
			em.persist(headset);
			em.persist(pizza);
			em.persist(taco);
			em.persist(teddy);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERROR:CreateProducts"+e.getMessage());
		}
		
		
		// Compras
		BillHead compra1 = new BillHead();
		compra1.setHeaUser(eduardo);
		compra1.setHeaDate(Calendar.getInstance());
		compra1.setHeaVat(12);
		
			BillDetail detalle1 = new BillDetail();
			detalle1.setDetAmount(2);
			detalle1.setDetProduct(mouse);
			detalle1.calculateTotal();
			detalle1.setDetBillHead(compra1);
			
			BillDetail detalle2 = new BillDetail();
			detalle2.setDetAmount(4);
			detalle2.setDetProduct(pizza);
			detalle2.calculateTotal();
			detalle2.setDetBillHead(compra1);
			
		List<BillDetail> detalles = new ArrayList<BillDetail>();
		detalles.add(detalle1);
		detalles.add(detalle2);
		
		compra1.setHeaBillDetails(detalles);
		compra1.calcualteTotal();
		
		BillHead compra2 = new BillHead();
		compra2.setHeaUser(roberto);
		compra2.setHeaDate(Calendar.getInstance());
		compra2.setHeaVat(12);
		
			BillDetail detalle3 = new BillDetail();
			detalle3.setDetAmount(7);
			detalle3.setDetProduct(chair);
			detalle3.calculateTotal();
			detalle3.setDetBillHead(compra2);
			
			BillDetail detalle4 = new BillDetail();
			detalle4.setDetAmount(3);
			detalle4.setDetProduct(table);
			detalle4.calculateTotal();
			detalle4.setDetBillHead(compra2);
			
		List<BillDetail> detalles2 = new ArrayList<BillDetail>();
		detalles2.add(detalle3);
		detalles2.add(detalle4);
		
		compra2.setHeaBillDetails(detalles2);
		compra2.calcualteTotal();
		
		
		try {
			em.getTransaction().begin();
			em.persist(compra1);
			em.persist(compra2);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERROR:CreateBills"+e.getMessage());
		}
		
		
		ProductDAO productDAO = DAOFactory.getFactory().getProductDAO();
		String[][] attributes = {{"proId"}};
		String[] values = {"1"};
//		List<Product> products = (List<Product>) productDAO.findByPath(attributes, values, null, 0, 0, false);
		List<Product>  products =  (List<Product>) productDAO.findBestProductsByComId(1, 5);
		for (Product product : products) {
			System.out.println(product);
		}
		
//			// Relaciones
//		// Empresa-Usuario
//		
//		
//		// Categoria-Producto
//		List<Product> electronicsProducts = new ArrayList<Product>();
//		electronicsProducts.add(mouse);
//		electronicsProducts.add(headset);
//		electronics.setCatProducts(electronicsProducts);
//		
//		List<Product> toysProducts = new ArrayList<Product>();
//		toysProducts.add(funko);
//		toysProducts.add(teddy);
//		toy.setCatProducts(toysProducts);
//		
//		List<Product> foodProducts = new ArrayList<Product>();
//		foodProducts.add(pizza);
//		foodProducts.add(taco);
//		food.setCatProducts(foodProducts);
//		
//		// Empresa-Producto
//		List<Product> nuvaTProducts = new ArrayList<Product>();
//		nuvaTProducts.add(mouse);
//		nuvaTProducts.add(headset);
//		nuvaT.setComProducts(nuvaTProducts);
//		
//		List<Product> nuvaFProducts = new ArrayList<Product>();
//		nuvaFProducts.add(funko);
//		nuvaFProducts.add(teddy);
//		nuvaF.setComProducts(nuvaFProducts);
//		
//		List<Product> nuvaHProducts = new ArrayList<Product>();
//		nuvaHProducts.add(pizza);
//		nuvaHProducts.add(taco);
//		nuvaH.setComProducts(nuvaHProducts);	
//		try {
//			
//		} catch (Exception e) {
//			System.out.println("ERROR:CreateCategories"+e.getMessage());
//		}
//		
//		try {
//			
//		} catch (Exception e) {
//			System.out.println("ERROR:CreateUsers"+e.getMessage());
//		}
//		
//		try {
//			
//		} catch (Exception e) {
//			System.out.println("ERROR:CreateProducts"+e.getMessage());
//		}
		
		
	}

}
