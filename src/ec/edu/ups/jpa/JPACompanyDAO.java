package ec.edu.ups.jpa;

import ec.edu.ups.dao.CompanyDAO;
import ec.edu.ups.entities.Company;

public class JPACompanyDAO extends JPAGenericDAO<Company, Integer> implements CompanyDAO {

	public JPACompanyDAO() {
		super(Company.class);
	}

}
