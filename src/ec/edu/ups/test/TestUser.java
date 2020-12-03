package ec.edu.ups.test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.entities.User;

public class TestUser {

	public static void main(String[] args) {
		
		EntityManager em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
		User bryan;
		User eduardo;
		User roberto;
		User fumino;

		// CREATE
		try {
			
			bryan = new User();
			bryan.setUseEmail("sarmiento@example.com");
			bryan.setUseName("Bryan");
			bryan.setUseLastname("Sarmiento");
			bryan.setUsePassword("123");
			bryan.setUseRole('U');
			
			eduardo = new User();
			eduardo.setUseEmail("edd@example.com");
			eduardo.setUseName("Eduardo");
			eduardo.setUseLastname("Zhizhpon");
			eduardo.setUsePassword("123");
			eduardo.setUseRole('U');
			
			roberto = new User();
			roberto.setUseEmail("roby@example.com");
			roberto.setUseName("Roberto");
			roberto.setUseLastname("Serpa");
			roberto.setUsePassword("123");
			roberto.setUseRole('U');
			
			fumino = new User();
			fumino.setUseEmail("sakura@example.com");
			fumino.setUseName("Fumino");
			fumino.setUseLastname("Sakurajima");
			fumino.setUsePassword("123");
			fumino.setUseRole('U');

			em.getTransaction().begin();
			em.persist(bryan);
			em.persist(eduardo);
			em.persist(roberto);
			em.persist(fumino);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.getMessage();
		}
		
		// READ
		em.getTransaction().begin();
		User buscar1 = em.find(User.class, 1);
		System.out.println("Usuario con 1> " + buscar1);
		
		User buscar2 = em.find(User.class, 2);
		System.out.println("Usuario con 2> " + buscar2);
		
		// UPDATE
		buscar1.setUseLastname("SAKURA");
		
		try {
			em.merge(buscar1);
		} catch (Exception e) {
			e.getMessage();
		}
		
		
		
	}

}
