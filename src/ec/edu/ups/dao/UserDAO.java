package ec.edu.ups.dao;

import ec.edu.ups.entities.User;

public interface UserDAO extends GenericDAO<User, Integer>{

	public User login(String key, String password);
	
}
