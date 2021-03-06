package ec.edu.ups.controller.billdetail;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.dao.BillDetailDAO;
import ec.edu.ups.dao.BillHeadDAO;
import ec.edu.ups.dao.DAOFactory;
import ec.edu.ups.dao.ProductDAO;
import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;
import ec.edu.ups.entities.Product;
import ec.edu.ups.entities.User;

/**
 * Servlet implementation class CreateBillDetail
 */
@WebServlet("/CreateBillDetail")
public class CreateBillDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BillDetailDAO billDetailDAO;
	private BillHeadDAO billHeadDAO;
	private ProductDAO productDAO;
	private BillDetail billDetail;
	private BillHead billHead;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBillDetail() {
        super();
        billDetailDAO = DAOFactory.getFactory().getBillDetailDAO();
        billHeadDAO = DAOFactory.getFactory().getBillHeadDAO();
        productDAO = DAOFactory.getFactory().getProductDAO();
        billDetail = new BillDetail();
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
			int useId = user.getUseId();
			int res = 0;
			int proId = Integer.parseInt(request.getParameter("pro_id"));
			int detAmount = Integer.parseInt(request.getParameter("det_amount"));
			Product product = productDAO.read(proId);
			
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
				aux = (List<BillHead>) billHeadDAO.findByPath(attributes, values, null, 0, 0, false);
				billHead = aux.get(0);
			}
			
			String[][] attributesDet = {{"detProduct", "proId"},{"detBillHead", "heaId"}};
			String[] valuesDet = {"like&" + proId, "like&" + billHead.getHeaId()};
			List<BillDetail> auxDet = billDetailDAO.findByPath(attributesDet, valuesDet, null, 0, 0, false);
			if(auxDet.isEmpty()) {
				billDetail = null;
			}else {
				billDetail = auxDet.get(0);
			}
			if(billDetail == null) {
				billDetail = new BillDetail();
				billDetail.setDetAmount(detAmount);
				billDetail.setDetBillHead(billHead);
				billDetail.setDetProduct(product);
				billDetailDAO.create(billDetail);
			} else {
				billDetail.setDetAmount(1);
				billDetail.setDetBillHead(billHead);
				billDetail.setDetProduct(product);
				billDetail.setDetDeleted(false);
				billDetailDAO.update(billDetail);
			}
			if(res == 1062) {
				response.getWriter().append("No se pudo agregar al carrito&e_notice_error");
			} else if(res == 0) {
				response.getWriter().append("Agregado&e_notice_sucess");
			} else {
				response.getWriter().append("No se pudo agregar al carrito&e_notice_error");
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append("Error interno, no coincide la llave-valor&e_notice_error");
			
		}
	}

}
