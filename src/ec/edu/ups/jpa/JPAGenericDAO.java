package ec.edu.ups.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.edu.ups.dao.GenericDAO;

public class JPAGenericDAO<T, ID> implements GenericDAO<T, ID>{

	private Class<T> persistentClass;
	protected EntityManager em;
	
	public JPAGenericDAO(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
		this.em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
	}
	
	@Override
	public void create(T entity) {
		em.getTransaction().begin();
		try {
			em.persist(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:create" + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public T read(ID id) {
		return em.find(persistentClass, id);
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		try {
			em.merge(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:update" + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public void delete(T entity) {
		em.getTransaction().begin();
		try {
			em.remove(entity);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(">>>> ERROR>JPAGenericDAO:delete" + e);
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}
	}

	@Override
	public void deleteByID(ID id) {
		T entity = this.read(id);
		if (entity != null) {
			this.delete(entity);
		}
	}

	@Override
	public List<T> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> find(String[] attributes, String[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> find(String[] attributes, String[] values, 
						String order, int index, int size) {
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		
		// FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		
		// SELECT
		criteriaQuery.select(root);
//		criteriaQuery.multiselect(atr);
		
		// Predicados, combinados con AND
		Predicate predicate = criteriaBuilder.conjunction();
		for (int i = 0; i < attributes.length; i++) {
			Predicate sig = criteriaBuilder.like(root.get(attributes[i]).as(String.class), values[i]);
			predicate = criteriaBuilder.and(predicate, sig);
		}
		
		// WHERE 
		criteriaQuery.where(predicate);
		
		// ORDER
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		// Resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return tq.getResultList();
		} else {
			// Se realiza la Query
			Query query = em.createQuery(criteriaQuery);
			return query.getResultList();
		}		
	}
	
}
