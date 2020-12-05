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
import ec.edu.ups.entities.BillHead;
import ec.edu.ups.entities.User;

/**
 * Servlet implementation class UserBill
 */
@WebServlet("/myBills")
public class UserBillList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BillHeadDAO billHeadDAO;
	private List<BillHead> billHeads;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBillList() {
        super();
        billHeadDAO = DAOFactory.getFactory().getBillHeadDAO();
        billHeads = new ArrayList<BillHead>();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		String[][] attributes = {{"heaUser", "useId"}};
		String[] values = {user.getUseId() + ""};
		billHeads = (List<BillHead>) billHeadDAO.findByPath(attributes, values, null, 0, 0, false);
		BillHead billHeadRead;
		try {
			billHeadRead = (BillHead) getServletContext().getAttribute("billHeadRead");
		} catch (Exception e) {
			billHeadRead = new BillHead();
		}
		request.setAttribute("billHeads", billHeads);
		request.setAttribute("billHeadRead", billHeadRead);
		RequestDispatcher view = request.getRequestDispatcher("/JSP/private/user/bills.jsp");
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