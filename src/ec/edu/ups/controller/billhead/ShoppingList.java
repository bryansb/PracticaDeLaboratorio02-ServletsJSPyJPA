package ec.edu.ups.controller.billhead;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.BillHeadDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;
import ec.edu.ups.entities.User;

/**
 * Servlet implementation class ShoppingList
 */
@WebServlet("/ShoppingList")
public class ShoppingList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private BillHeadDAO billHeadDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingList() {
        super();
        billHeadDAO = DAOFactory.getFactory().getBillHeadDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = (User) request.getSession().getAttribute("user");
			int useId = user.getUseId();
			BillHead billHead;
			
			String[][] attributes = {{"heaStatus"},{"heaUser", "useId"}};
			String[] values = {"like&C", "like&" + useId};
			List<BillHead> aux = (List<BillHead>) billHeadDAO.findByPath(attributes, values, null, 0, 0, false);
			
			if (aux.isEmpty())
				billHead = null;
			else 
				billHead = aux.get(0);
			
			if(billHead == null) {
				billHead = new BillHead();
				billHead.setHeaUser(user);
				billHeadDAO.create(billHead);
				aux = billHeadDAO.findByPath(attributes, values, null, 0, 0, false);
				billHead = aux.get(0);
			}
			
			List<BillDetail> billDetails = new ArrayList<BillDetail>();
			billDetails = DAOFactory.getFactory().getBillDetailDAO().findByHeaId(billHead.getHeaId());
			for (BillDetail billDetail : billDetails) {
				billDetail.calculateTotal();
			}
			billHead = billHeadDAO.read(billHead.getHeaId());
			billHead.setHeaBillDetails(billDetails);
			billHead.calcualteTotal();
			request.setAttribute("billHead", billHead);
			RequestDispatcher view = request.getRequestDispatcher("/JSP/private/user/cart.jsp");
	        view.forward(request, response);
	        
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/sgrc/HTML/login.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
