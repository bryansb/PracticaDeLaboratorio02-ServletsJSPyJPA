package ec.edu.ups.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.entities.Category;
import ec.edu.ups.entities.Company;
import ec.edu.ups.entities.Product;
import ec.edu.ups.entities.User;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/CreateProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDAO productDAO;
	private Product product;
	private Category category;
	private Company company;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateProduct() {
        super();
        productDAO = DAOFactory.getFactory().getProductDAO();
        product = new Product();
        category = new Category();
        company = new Company();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			int com_id = user.getUseCompany().getComId();
			company.setComId(com_id);
			category.setCatId(Integer.parseInt(request.getParameter("cat_id")));
			product.setProName(request.getParameter("pro_name"));
			product.setProStock(Integer.parseInt(request.getParameter("pro_stock")));
			product.setProPrice(Double.parseDouble(request.getParameter("pro_price")));
			product.setProCompany(company);
			product.setProCategory(category);
			
			productDAO.create(product);
			response.getWriter().append("Se ha registrado correctamente&e_notice_sucess");
			
		} catch (Exception e) {
			response.getWriter().append("Hubo un error al crear el Producto&e_notice_error");
			e.printStackTrace();
		}
		//getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
