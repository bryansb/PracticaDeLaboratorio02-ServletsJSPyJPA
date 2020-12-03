package ec.edu.ups.test;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ec.edu.ups.entities.BillDetail;
import ec.edu.ups.entities.BillHead;

public class TestBillHead {

	public static void main(String[] args) {
//		System.out.println(args);
//		EntityManager em = Persistence.createEntityManagerFactory("jpa").createEntityManager();
//		
//		BillHead bh = new BillHead();
//		
//		bh.setHeaDate(Calendar.getInstance());
//		bh.setHeaSubtotal(100);
//		bh.setHeaVat(10.0001);
//		bh.setHeaTotal(110);
//		
//		em.getTransaction().begin();
//		em.persist(bh);
//		em.getTransaction().commit();
//		
//		// Read
//		BillHead bh1 = em.find(BillHead.class, 1);
//		System.out.println(bh1);
//		
//		BillDetail bd = new BillDetail();
//		bd.setDetBillHead(bh1);
//		em.getTransaction().begin();
//		em.persist(bd);
//		em.getTransaction().commit();
//		
//		BillDetail bd1 = em.find(BillDetail.class, 1);
//		System.out.println(bd1);
//		
//		bd1.setDetAmount(10);
//		bd1.setDetUnitPrice(10.0);
//		bd1.getDetBillHead().setHeaTotal(200);
//		em.getTransaction().begin();
//		em.merge(bd1);
//		em.getTransaction().commit();
//		
//		bd1 = em.find(BillDetail.class, 1);
//		System.out.println(bd1);
//		
//		bh = em.find(BillHead.class, 1);
//		System.out.println(bh);
//		em.getTransaction().begin();
//		em.merge(bh);
//		em.getTransaction().commit();
//		
//		bh = em.find(BillHead.class, 1);
//		System.out.println(bh);
		
	}
	
}
