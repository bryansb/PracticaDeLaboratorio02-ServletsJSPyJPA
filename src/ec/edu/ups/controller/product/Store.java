package ec.edu.ups.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.CategoryDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.entities.Category;
import ec.edu.ups.entities.Product;
import ec.edu.ups.entities.User;
import ec.edu.ups.resources.MathFunction;

/**
 * Servlet implementation class Store
 */
@WebServlet("/store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private UserDAO userDAO;
    private CategoryDAO categoryDAO;
    private ProductDAO productDAO;
    private List<Category> categories;
    private List<Product> products;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Store() {
        super();
        productDAO = DAOFactory.getFactory().getProductDAO();
        categoryDAO = DAOFactory.getFactory().getCategoryDAO();
        userDAO = DAOFactory.getFactory().getUserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		int useId = user.getUseId();
		user = userDAO.read(useId);
		int comId = user.getUseCompany().getComId();
		
        int catId = 0;
        String s = "";
        try {
           catId = Integer.parseInt(request.getParameter("cat_id"));
		} catch (Exception e) {
			
		}
        s = request.getParameter("s") == null ? "" : request.getParameter("s");
        String[] classes = {"catProducts", "proCompany"};
		String[][] attributes1 = {{""}, {"comId"}};
		String[] values1 = {"", "like&" + comId};
 		categories = categoryDAO.findByJoin(classes, attributes1, values1, "catName", 0, 0, true);
		String[][] attributes = {{"proCategory", "catId"},{"proName"}, {"proStock"}};		
        
		products = new ArrayList<Product>();
        for (Category category : categories) {
        	if (catId == 0) {
        		String[] values = {"like&" + category.getCatId(), "like&%" + s + "%", ">&0"};
        		products.addAll(productDAO.findByPath(attributes, values, null, 0, 0, true));
			}else if(catId != 0) {
				if (category.getCatId() == catId) {
					String[] values = {"like&" + category.getCatId(), "like&%" + s + "%", ">&0"};
					products.addAll(productDAO.findByPath(attributes, values, null, 0, 0, true));
				}
			}
		}
        int currentPage;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}catch (Exception e) {
			currentPage = 0;
		}
		Map<String, Integer> nav = MathFunction.getNavPages(products.size(), currentPage, 5);
		int min = nav.get("min");
		int max = nav.get("max");
		int minP = nav.get("minP");
		int maxP = nav.get("maxP");
		int maxPages = nav.get("maxPages");
		products = products.subList(min, max + 1);
		
		request.setAttribute("min", minP);
		request.setAttribute("max", maxP);
		request.setAttribute("maxPages", maxPages);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("catId", catId);
		request.setAttribute("s", s);
        request.setAttribute("categories", categories);
        request.setAttribute("productsList", products);
		RequestDispatcher view = request.getRequestDispatcher("/JSP/private/user/products.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
