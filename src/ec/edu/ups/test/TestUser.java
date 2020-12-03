package ec.edu.ups.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.entities.User;

public class TestUser {

	public static void main(String[] args) {
		
		EntityManager em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		User user;

		try {
			
			user = new User();
			user.setUseEmail("sarmesdasdi@example.com");
			user.setUseName("asd");
			user.setUseLastname("asd");
			user.setUsePassword("123");
			user.setUseRole('U');
			

			em.getTransaction().begin();
			em.persist(user);
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.getMessage();
		}
		
	}

}
