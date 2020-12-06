package ec.edu.ups.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.dao.CategoryDAO;
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
		Company nuvaTech = new Company();
		nuvaTech.setComName("NuvaTech");
		
		Company masada = new Company();
		masada.setComName("Masada");
		
		Company fukuokaFood = new Company();
		fukuokaFood.setComName("Fukuoka Food");
		
		try {
			em.getTransaction().begin();
			em.persist(fukuokaFood);
			em.persist(masada);
			em.persist(nuvaTech);
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
		
		// Usuarios NuvaTech
		User bryan = new User();
		bryan.setUseEmail("sarmiento@nuvatech.com");
		bryan.setUseUsername("bryansb");
		bryan.setUseName("Bryan");
		bryan.setUseLastname("Sarmiento");
		bryan.setUsePassword(MathFunction.getMd5("123"));
		bryan.setUseRole('A');
		bryan.setUseCompany(nuvaTech);
		
		User roberto = new User();
		roberto.setUseEmail("roby@nuvatech.com");
		roberto.setUseUsername("robys");
		roberto.setUseName("Roberto");
		roberto.setUseLastname("Serpa");
		roberto.setUsePassword(MathFunction.getMd5("123"));
		roberto.setUseRole('U');
		roberto.setUseCompany(nuvaTech);
		
		User kathy = new User();
		kathy.setUseEmail("karen@nuvatech.com");
		kathy.setUseUsername("kathy1");
		kathy.setUseName("Karen");
		kathy.setUseLastname("Cardenas");
		kathy.setUsePassword(MathFunction.getMd5("123"));
		kathy.setUseRole('U');
		kathy.setUseCompany(nuvaTech);
		
		User eva = new User();
		eva.setUseEmail("eva@nuvatech.com");
		eva.setUseUsername("evavillaroel");
		eva.setUseName("Eva");
		eva.setUseLastname("Villarroel");
		eva.setUsePassword(MathFunction.getMd5("123"));
		eva.setUseRole('U');
		eva.setUseCompany(nuvaTech);
		
		// Usuarios Masada
		User eduardo = new User();
		eduardo.setUseEmail("edd@masada.com");
		eduardo.setUseUsername("eddZ");
		eduardo.setUseName("Eduardo");
		eduardo.setUseLastname("Zhizhpon");
		eduardo.setUsePassword(MathFunction.getMd5("123"));
		eduardo.setUseRole('A');
		eduardo.setUseCompany(masada);
		
		User pablo = new User();
		pablo.setUseEmail("pablo@masada.com");
		pablo.setUseUsername("palol");
		pablo.setUseName("Pablo");
		pablo.setUseLastname("Loja");
		pablo.setUsePassword(MathFunction.getMd5("123"));
		pablo.setUseRole('U');
		pablo.setUseCompany(masada);
		
		User robbyn = new User();
		robbyn.setUseEmail("robbyn@masada.com");
		robbyn.setUseUsername("robbynr");
		robbyn.setUseName("Robbyn");
		robbyn.setUseLastname("Reyes");
		robbyn.setUsePassword(MathFunction.getMd5("123"));
		robbyn.setUseRole('U');
		robbyn.setUseCompany(masada);
		
		User carlos = new User();
		carlos.setUseEmail("carlos@masada.com");
		carlos.setUseUsername("charly");
		carlos.setUseName("Carlos");
		carlos.setUseLastname("Farfan");
		carlos.setUsePassword(MathFunction.getMd5("123"));
		carlos.setUseRole('U');
		carlos.setUseCompany(masada);
			
		// Usuarios Fukuoka
		User fumino = new User();
		fumino.setUseEmail("sakura@fukuokafood.com");
		fumino.setUseUsername("harusf");
		fumino.setUseName("Fumino");
		fumino.setUseLastname("Sakurajima");
		fumino.setUsePassword(MathFunction.getMd5("123"));
		fumino.setUseRole('A');
		fumino.setUseCompany(fukuokaFood);
		
		User lorena = new User();
		lorena.setUseEmail("lore@fukuokafood.com");
		lorena.setUseUsername("lesoni");
		lorena.setUseName("Lorena");
		lorena.setUseLastname("Basurto");
		lorena.setUsePassword(MathFunction.getMd5("123"));
		lorena.setUseRole('U');
		lorena.setUseCompany(fukuokaFood);
		
		User denisse = new User();
		denisse.setUseEmail("denisse@fukuokafood.com");
		denisse.setUseUsername("monita");
		denisse.setUseName("Denisse");
		denisse.setUseLastname("Basurto");
		denisse.setUsePassword(MathFunction.getMd5("123"));
		denisse.setUseRole('U');
		denisse.setUseCompany(fukuokaFood);
		
		User nathy = new User();
		nathy.setUseEmail("nathy@fukuokafood.com");
		nathy.setUseUsername("nathys");
		nathy.setUseName("Nathaly");
		nathy.setUseLastname("Guaraca");
		nathy.setUsePassword(MathFunction.getMd5("123"));
		nathy.setUseRole('U');
		nathy.setUseCompany(fukuokaFood);		
		
		try {
			em.getTransaction().begin();
			em.persist(bryan);
			em.persist(fumino);
			em.persist(eduardo);
			em.persist(roberto);
			
			em.persist(carlos);
			em.persist(eva);
			em.persist(kathy);
			em.persist(robbyn);
			
			em.persist(pablo);
			em.persist(nathy);
			em.persist(lorena);
			em.persist(denisse);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("ERROR:CreateUsers"+e.getMessage());
		}
		
		// Productos NuvaTech
		Product mouse = new Product();
		mouse.setProName("Black Widow");
		mouse.setProStock(100);
		mouse.setProPrice(60);
		mouse.setProCategory(electronics);
		mouse.setProCompany(nuvaTech);
		
		Product headset = new Product();
		headset.setProName("Corsair Virtuoso");
		headset.setProStock(100);
		headset.setProPrice(300);
		headset.setProCategory(electronics);
		headset.setProCompany(nuvaTech);
		
		Product dellG7 = new Product();
		dellG7.setProName("Dell G7");
		dellG7.setProStock(25);
		dellG7.setProPrice(1250);
		dellG7.setProCategory(electronics);
		dellG7.setProCompany(nuvaTech);
		
		Product lenovo = new Product();
		lenovo.setProName("Lenovo Legion");
		lenovo.setProStock(10);
		lenovo.setProPrice(2000);
		lenovo.setProCategory(electronics);
		lenovo.setProCompany(nuvaTech);
		
		// Productos Fukuoka
		Product pizza = new Product();
		pizza.setProName("Pizza Napolitana G");
		pizza.setProStock(30);
		pizza.setProPrice(20);
		pizza.setProCategory(food);
		pizza.setProCompany(fukuokaFood);
		
		Product taco = new Product();
		taco.setProName("Taco de Carne");
		taco.setProStock(50);
		taco.setProPrice(4.50);
		taco.setProCategory(food);
		taco.setProCompany(fukuokaFood);
		
		Product sushi = new Product();
		sushi.setProName("Sushi de Cangrejo");
		sushi.setProStock(45);
		sushi.setProPrice(9.99);
		sushi.setProCategory(food);
		sushi.setProCompany(fukuokaFood);
		
		Product yakisoba = new Product();
		yakisoba.setProName("Yakisoba");
		yakisoba.setProStock(50);
		yakisoba.setProPrice(10);
		yakisoba.setProCategory(food);
		yakisoba.setProCompany(fukuokaFood);
		
		// Productos Masada
		Product pant = new Product();
		pant.setProName("Pantalones");
		pant.setProStock(100);
		pant.setProPrice(25);
		pant.setProCategory(clothes);
		pant.setProCompany(masada);
		
		Product suit = new Product();
		suit.setProName("Traje");
		suit.setProStock(10);
		suit.setProPrice(60);
		suit.setProCategory(clothes);
		suit.setProCompany(masada);
		
		Product funko = new Product();
		funko.setProName("Funko Pop");
		funko.setProStock(100);
		funko.setProPrice(24.99);
		funko.setProCategory(toy);
		funko.setProCompany(masada);
		
		Product teddy = new Product();
		teddy.setProName("Peluche");
		teddy.setProStock(100);
		teddy.setProPrice(9.99);
		teddy.setProCategory(toy);
		teddy.setProCompany(masada);
		
		Product chair = new Product();
		chair.setProName("Silla");
		chair.setProStock(10);
		chair.setProPrice(30);
		chair.setProCategory(furniture);
		chair.setProCompany(masada);
		
		Product table = new Product();
		table.setProName("Mesa");
		table.setProStock(75);
		table.setProPrice(100);
		table.setProCategory(furniture);
		table.setProCompany(masada);
		
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
			em.persist(dellG7);
			em.persist(lenovo);
			em.persist(yakisoba);
			em.persist(sushi);
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
		
		CategoryDAO categoryDAO = DAOFactory.getFactory().getCategoryDAO();
		
		String[] classes = {"catProducts", "proCompany"};
		String[][] attributes5 = {{""}, {"comId"}};
		String[] values5 = {"", "1"};
 		List<Category> cat = categoryDAO.findByJoin(classes, attributes5, values5, "catName", 0, 0, true);
 		
 		System.out.println(cat);
 		
//			// Relaciones
//		// Empresa-Usuario
//		
//		
//		// Categoria-Producto
//		List<Product> electronicsProducts = new ArrayList<Product>();
//		electronicsProducts.add(mouse);
//		electronicsProducts.add(headset);
//		electronics.setCatProducts(electronicsProducts);
		/*
		 * Descomentar lo de abajo para registrar pedidos
		 */
//		// Compras
//		BillHead compra1 = new BillHead();
//		compra1.setHeaUser(eduardo);
//		compra1.setHeaDate(Calendar.getInstance());
//		compra1.setHeaVat(12);
//		
//			BillDetail detalle1 = new BillDetail();
//			detalle1.setDetAmount(2);
//			detalle1.setDetProduct(mouse);
//			detalle1.calculateTotal();
//			detalle1.setDetBillHead(compra1);
//			
//			BillDetail detalle2 = new BillDetail();
//			detalle2.setDetAmount(4);
//			detalle2.setDetProduct(pizza);
//			detalle2.calculateTotal();
//			detalle2.setDetBillHead(compra1);
//			
//		List<BillDetail> detalles = new ArrayList<BillDetail>();
//		detalles.add(detalle1);
//		detalles.add(detalle2);
//		
//		compra1.setHeaBillDetails(detalles);
//		compra1.calcualteTotal();
//		
//		BillHead compra2 = new BillHead();
//		compra2.setHeaUser(roberto);
//		compra2.setHeaDate(Calendar.getInstance());
//		compra2.setHeaVat(12);
//		
//			BillDetail detalle3 = new BillDetail();
//			detalle3.setDetAmount(7);
//			detalle3.setDetProduct(chair);
//			detalle3.calculateTotal();
//			detalle3.setDetBillHead(compra2);
//			
//			BillDetail detalle4 = new BillDetail();
//			detalle4.setDetAmount(3);
//			detalle4.setDetProduct(table);
//			detalle4.calculateTotal();
//			detalle4.setDetBillHead(compra2);
//			
//		List<BillDetail> detalles2 = new ArrayList<BillDetail>();
//		detalles2.add(detalle3);
//		detalles2.add(detalle4);
//		
//		compra2.setHeaBillDetails(detalles2);
//		compra2.calcualteTotal();
//		
//		
//		try {
//			em.getTransaction().begin();
//			em.persist(compra1);
//			em.persist(compra2);
//			em.getTransaction().commit();
//		} catch (Exception e) {
//			em.getTransaction().rollback();
//			System.out.println("ERROR:CreateBills"+e.getMessage());
//		}
		
		
//		ProductDAO productDAO = DAOFactory.getFactory().getProductDAO();
//		String[][] attributes = {{"proId"}};
//		String[] values = {"1"};
////		List<Product> products = (List<Product>) productDAO.findByPath(attributes, values, null, 0, 0, false);
//		List<Product>  products =  (List<Product>) productDAO.findBestProductsByComId(1, 5);
//		for (Product product : products) {
//			System.out.println(product);
//		}

		
	}

}
