package ec.edu.ups.dao;

import java.util.List;

import ec.edu.ups.entities.BillDetail;

public interface BillDetailDAO extends GenericDAO<BillDetail, Integer>{
	public List<BillDetail> findByHeaId(int id);
}
