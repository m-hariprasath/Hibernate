package org.jsp.project.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.project.dto.Merchant;

public class MerchantDao {

	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	Query q = null;

	public Merchant saveMerchant(Merchant m) {
		manager.persist(m);
		transaction.begin();
		transaction.commit();
		return m;
	}

	public Merchant updateMerchant(Merchant m) {
		Merchant dm = manager.find(Merchant.class, m.getId());
		if (dm != null) {
			System.out.println("==========Entered======");
			transaction.begin();
			dm.setId(m.getId());
			dm.setName(m.getName());
			dm.setPhone(m.getPhone());
			dm.setGst_no(m.getGst_no());
			dm.setEmail(m.getEmail());
			dm.setPassword(m.getPassword());
			transaction.commit();
			return dm;
		}
		return null;
	}

	public Merchant findMerchantById(int id) {
		return manager.find(Merchant.class, id);
	}

	public Merchant findMerchantByPhonePassword(long phone, String password) {
		q=manager.createQuery("select m from Merchant m where m.phone=?1 and m.password=?2 ");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			Merchant m = (Merchant) q.getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Merchant findMerchantByEmailPassword(String email, String password) {
		q=manager.createQuery("select m from Merchant m where m.email=?1 and m.password=?2 ");
		q.setParameter(1, email);
		q.setParameter(2, password);
		try {
			Merchant m = (Merchant) q.getSingleResult();
			return m;
		} catch (NoResultException e) {
			return null;
		}
	}
}
