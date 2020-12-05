package ec.edu.ups.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.CategoryDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.entities.Category;
import ec.edu.ups.entities.Product;
import ec.edu.ups.entities.User;

/**
 * Servlet implementation class ListProduct
 */
@WebServlet("/ListProduct")
public class ListProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListProduct() {
        super();
        productDAO = DAOFactory.getFactory().getProductDAO();
        categoryDAO = DAOFactory.getFactory().getCategoryDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = (User) request.getSession().getAttribute("user");
			Product product;
			int com_id = user.getUseCompany().getComId();
//			int com_id = 3;
			//System.out.println(com_id);
			
			List<Product> auxPL;
			List<Category> auxCL;
			
			String[][] attributes3 = {{"proCompany", "comId"}};
			String[] values3 = {com_id+""};
			auxPL = productDAO.findByPath(attributes3, values3, null, 0, 0, true);
			List<Product> productsList = auxPL;

			List<Category> categoriesList = categoryDAO.find(null, 0, 0);

			
			try {
				product = (Product) getServletContext().getAttribute("productRead");
			} catch (Exception e) {
				product = new Product();
			}
			
			request.setAttribute("products", productsList);
			request.setAttribute("categories", categoriesList);
			request.setAttribute("productRead", product);
			RequestDispatcher view = request.getRequestDispatcher("/JSP/private/admin/product_register.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			response.sendRedirect("/JSP/public/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
