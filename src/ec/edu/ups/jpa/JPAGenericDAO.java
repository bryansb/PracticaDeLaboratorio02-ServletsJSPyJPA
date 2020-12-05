package ec.edu.ups.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> find(String order,int index, int size) {
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		
		// FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		
		// SELECT
		criteriaQuery.select(root);
		
		// ORDER
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		// Resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			// Se realiza la Query
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> findByPath(String[][] attributes, String[] values, String order, int index, int size, boolean isDistinct) {
		// Se crea un criterio de consulta
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		
		// FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		
		// SELECT
		criteriaQuery.select(root);
		
		// Predicados, combinados con AND
		Predicate predicate = criteriaBuilder.conjunction();
		for (int i = 0; i < attributes.length; i++) {
			Path path = root.get(attributes[i][0]);
			for (int j = 1; j < attributes[i].length; j++) {
				path = path.get(attributes[i][j]);
			}
			Predicate sig = criteriaBuilder.like(path.as(String.class), values[i]);
			predicate = criteriaBuilder.and(predicate, sig);
		}
		
		// WHERE 
		criteriaQuery.where(predicate);
		
		// ORDER
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		criteriaQuery.distinct(isDistinct);
		
		// Resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			// Se realiza la Query
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> find(String[] attributes, String[] values, 
						String order, int index, int size, boolean isDistinct) {
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
		
		criteriaQuery.distinct(isDistinct);
		
		// Resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			// Se realiza la Query
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<T> findByJoin(String[] classes, String[][] attributes, String[] values, String order, int index,
			int size, boolean isDistinct) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(this.persistentClass);
		// FROM
		Root<T> root = criteriaQuery.from(this.persistentClass);
		Join join = root.join(classes[0]);
		
		// SELECT
		criteriaQuery.select(root);
		
		// Predicados, combinados con AND
		Predicate predicate = criteriaBuilder.conjunction();
		Predicate sig;
		if(!attributes[0][0].isEmpty()) {
			predicate = criteriaBuilder.conjunction();
			sig = criteriaBuilder.equal(join.get(attributes[0][0]).as(String.class), values[0]);
			predicate = criteriaBuilder.and(predicate, sig);
		}
		for (int i = 1; i < classes.length; i++) {
			join = join.join(classes[i]);
			for (int j = 0; j < attributes[0].length; j++) {
				if(!attributes[i][j].isEmpty()) {
					sig = criteriaBuilder.equal(join.get(attributes[i][j]).as(String.class), values[i]);
					predicate = criteriaBuilder.and(predicate, sig);
				}
			}
			
		}
		
		// WHERE 
		criteriaQuery.where(predicate);
		
		// ORDER
		if (order != null) criteriaQuery.orderBy(criteriaBuilder.asc(root.get(order)));
		
		criteriaQuery.distinct(isDistinct);
		
		// Resultado
		if (index >= 0 && size > 0) {
			TypedQuery<T> tq = em.createQuery(criteriaQuery);
			tq.setFirstResult(index);
			tq.setMaxResults(size);
			return (List<T>) tq.getResultList();
		} else {
			// Se realiza la Query
			Query query = em.createQuery(criteriaQuery);
			return (List<T>) query.getResultList();
		}
	}
	
}
