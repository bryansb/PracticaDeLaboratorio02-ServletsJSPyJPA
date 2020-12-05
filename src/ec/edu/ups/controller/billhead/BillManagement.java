package ec.edu.ups.controller.billhead;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.BillHeadDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.entities.BillHead;
import ec.edu.ups.entities.User;

/**
 * Servlet implementation class BillManagement
 */
@WebServlet("/bills")
public class BillManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BillHeadDAO billHeadDAO;
	private UserDAO userDAO;
	private List<BillHead> billHeads;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillManagement() {
        super();
        billHeadDAO = DAOFactory.getFactory().getBillHeadDAO();
        userDAO = DAOFactory.getFactory().getUserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User user = (User) request.getSession().getAttribute("user");
			int useId = user.getUseId();
			user = userDAO.read(useId);
			String s = "";
			s = request.getParameter("s") == null ? "" : request.getParameter("s");
			String[] classes = {"heaUser", "useCompany"};
			String[][] attributes = {{"useEmail"}, {"comId"}};
			String[] values = {"%" + s + "%", user.getUseCompany().getComId() + ""};
			billHeads = billHeadDAO.findByJoin(classes, attributes, values, null, 0, 0, false);
			BillHead billHeadRead;
			try {
				billHeadRead = (BillHead) getServletContext().getAttribute("billHeadRead");
			} catch (Exception e) {
				billHeadRead = new BillHead();
			}
			request.setAttribute("billHeads", billHeads);
			request.setAttribute("billHeadRead", billHeadRead);
			request.setAttribute("s", s);
			RequestDispatcher view = request.getRequestDispatcher("/JSP/private/admin/bills_management.jsp");
	        view.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.toString());
			response.sendRedirect("/sgrc/JSP/public/error.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
