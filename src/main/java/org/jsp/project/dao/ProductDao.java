package org.jsp.project.dao;

import java.util.List;
import javax.persistence.EntityManager;

import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.jsp.project.dto.Merchant;
import org.jsp.project.dto.Product;

public class ProductDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	Query q = null;
	
	public Product saveProduct(Product p,int id) {
		Merchant m=manager.find(Merchant.class, id);
		if(m!=null) {
			m.getProducts().add(p);
			p.setMerchant(m);
			manager.persist(p);
			transaction.begin();
			transaction.commit();
			return p;
		}
		return null;
	}
	
	public Product updateProduct(Product p,int pid) {
		Product dbp=manager.find(Product.class, pid);
		if(dbp!=null) {
			dbp.setId(p.getId());
			dbp.setName(p.getName());
			dbp.setBrand(p.getBrand());
			dbp.setCategory(p.getCategory());
			dbp.setDescription(p.getDescription());
			dbp.setImg_url(p.getImg_url());
			transaction.begin();
			transaction.commit();
			return dbp;
		}
		return null;
	}
		
	public List<Product> findProductsByMId(int id){
		q=manager.createQuery("select m.products from Merchant m where m.id=?1 ");
		q.setParameter(1, id);
		return q.getResultList();
	}
	
	public List<Product> findProductsByBrand(String brand){
		q=manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}
	
	public List<Product> findProductsByCategory(String category){
		q=manager.createQuery("select p from Product p where p.category=?1");
		q.setParameter(1, category);
		return q.getResultList();
	}
	
}
