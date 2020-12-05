package ec.edu.ups.jpa;

import java.util.List;

import javax.persistence.Query;

import ec.edu.ups.dao.BillDetailDAO;
import ec.edu.ups.entities.BillDetail;

public class JPABillDetailDAO extends JPAGenericDAO<BillDetail, Integer> implements BillDetailDAO {

	public JPABillDetailDAO() {
		super(BillDetail.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BillDetail> findByHeaId(int id) {
		String jpql = "SELECT bd FROM BillDetail bd WHERE bd.detBillHead.heaId = " + id;
		Query query = em.createQuery(jpql, BillDetail.class);
		return (List<BillDetail>) query.getResultList();
	}

}
