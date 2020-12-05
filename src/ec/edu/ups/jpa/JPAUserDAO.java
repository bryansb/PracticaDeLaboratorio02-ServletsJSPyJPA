package ec.edu.ups.jpa;

import javax.persistence.Query;

import ec.edu.ups.dao.UserDAO;
import ec.edu.ups.entities.User;
import ec.edu.ups.resources.MathFunction;

public class JPAUserDAO extends JPAGenericDAO<User, Integer> implements UserDAO {

	public JPAUserDAO() {
		super(User.class);
	}

	@Override
	public User login(String key, String password) {
		em.clear();
		password = MathFunction.getMd5(password);
		String jpql = "SELECT u FROM User u "
				+ "WHERE (u.useEmail LIKE '" + key + "' OR u.useUsername LIKE '" + key + "') "
				+ "AND u.usePassword LIKE '" + password + "'";
		Query query = em.createQuery(jpql);
		User user = (User)query.getSingleResult();
		return user;
	}

}
