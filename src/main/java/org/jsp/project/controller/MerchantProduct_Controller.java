package org.jsp.project.controller;

import java.util.List;

import java.util.Scanner;

import org.jsp.project.dao.MerchantDao;
import org.jsp.project.dao.ProductDao;
import org.jsp.project.dto.Merchant;
import org.jsp.project.dto.Product;

public class MerchantProduct_Controller {
	static Scanner in = new Scanner(System.in);
	static MerchantDao mDao = new MerchantDao();
	static ProductDao pDao = new ProductDao();

	public static void saveMerchant() {
		Merchant m = new Merchant();
		System.out.println("Enter the Merchant Name,Phone,Gst no,Email,Password to save ");
		m.setName(in.next());
		m.setPhone(in.nextLong());
		m.setGst_no(in.next());
		m.setEmail(in.next());
		m.setPassword(in.next());
		m = mDao.saveMerchant(m);
		System.out.println("Merchant is saved successfully with id: " + m.getId());
	}

	public static void updateMerchant() {
		System.out.println("Enter the Merchant id to update");
		int id = in.nextInt();
		System.out.println("Enter the Merchant Name,Phone,Gst no,Email,Password to update the record ");
		Merchant m = new Merchant();
		m.setId(id);
		m.setName(in.next());
		m.setPhone(in.nextLong());
		m.setGst_no(in.next());
		m.setEmail(in.next());
		m.setPassword(in.next());
		m = mDao.updateMerchant(m);
		if (m != null) {
			System.out.println("Merchant is updated with id: " + m.getId());
		} else {
			System.err.println("Invalid id to update");
		}
	}

	public static void findMerchantById() {
		System.out.println("Enter the Merchant id to find");
		int id = in.nextInt();
		Merchant m = mDao.findMerchantById(id);
		if (m != null) {
			System.out.println("Merchant Name: " + m.getName());
			System.out.println("Merchant Phone: " + m.getPhone());
			System.out.println("Merchant Email: " + m.getEmail());
			System.out.println("Merchant GST no: " + m.getGst_no());
		} else {
			System.err.println("Invalid Id to fetch Merchant details");
		}
	}

	public static void verifyMerchantByEmailPassword() {
		System.out.println("Enter the Email and Password of Merchant to fetch details");
		String email = in.next();
		String password = in.next();
		Merchant m = mDao.findMerchantByEmailPassword(email, password);
		if (m != null) {
			System.out.println("Merchant Name: " + m.getName());
			System.out.println("Merchant Phone: " + m.getPhone());
			System.out.println("Merchant Email: " + m.getEmail());
			System.out.println("Merchant GST no: " + m.getGst_no());
		} else {
			System.err.println("Invalid data entered to the fetch Merchant details");
		}
	}
	
	public static void verifyMerchantByPhonePassword() {
		System.out.println("Enter the Phone and Password of Merchant to fetch details");
		long phone = in.nextLong();
		String password = in.next();
		Merchant m = mDao.findMerchantByPhonePassword(phone, password);
		if (m != null) {
			System.out.println("Merchant Name: " + m.getName());
			System.out.println("Merchant Phone: " + m.getPhone());
			System.out.println("Merchant Email: " + m.getEmail());
			System.out.println("Merchant GST no: " + m.getGst_no());
		} else {
			System.err.println("Invalid data entered to the fetch Merchant details");
		}
	}
	
	public static void saveProduct() {
		System.out.println("Enter the merchant id to save this product");
		int mid = in.nextInt();
		System.out.println("Enter the product name,brand,category,description,image url to save the product");
		Product p = new Product();
		p.setName(in.next());
		p.setBrand(in.next());
		p.setCategory(in.next());
		p.setDescription(in.next());
		p.setImg_url(in.next());
		p = pDao.saveProduct(p, mid);
		System.out.println("Product is saved with product id: " + p.getId());
	}
	
	public static void updateProduct() {
		System.out.println("Enter the product id to update the product");
		int pid = in.nextInt();
		System.out.println("Enter the product id,name,brand,category,description,image url to update the product");
		Product p = new Product();
		p.setId(in.nextInt());
		p.setName(in.next());
		p.setBrand(in.next());
		p.setCategory(in.next());
		p.setDescription(in.next());
		p.setImg_url(in.next());
		p = pDao.updateProduct(p, pid);
		if (p != null) {
			System.out.println("Product has been updated with ID: " + p.getId());
		} else {
			System.out.println("Invalid id to update product");
		}
	}
	
	public static void findProductsByMId() {
		System.out.println("Enter the Merchant id to fetch the products");
		int id = in.nextInt();
		List<Product> products = pDao.findProductsByMId(id);
		if (products.size() > 0) {
			for (Product p : products) {
				System.out.println("Product name : " + p.getName());
				System.out.println("Product brand: " + p.getBrand());
				System.out.println("Product Category: " + p.getCategory());
				System.out.println("Product Description: " + p.getDescription());
				System.out.println("Product Image url: " + p.getImg_url());
				System.out.println("Merchant Id: " + p.getMerchant().getId());
				System.out.println("===================><=================");
			}
		} else {
			System.err.println("No products found for this merchant id");
		}
	}
	
	public static void findProductsByCategory() {
		System.out.println("Enter the category to fetch products");
		String category = in.next();
		List<Product> products = pDao.findProductsByCategory(category);
		if (products.size() > 0) {
			for (Product p : products) {
				System.out.println("Product name : " + p.getName());
				System.out.println("Product brand: " + p.getBrand());
				System.out.println("Product Category: " + p.getCategory());
				System.out.println("Product Description: " + p.getDescription());
				System.out.println("Product Image url: " + p.getImg_url());
				System.out.println("Merchant Id: " + p.getMerchant().getId());
				System.out.println("======================><===================");
			}
		} else {
			System.err.println("No products found for this category");
		}
	}
	
	public static void findProductsByBrand() {
		System.out.println("Enter the brand to fetch products");
		String brand = in.next();
		List<Product> products = pDao.findProductsByBrand(brand);
		if (products.size() > 0) {
			for (Product p : products) {
				System.out.println("Product name : " + p.getName());
				System.out.println("Product brand: " + p.getBrand());
				System.out.println("Product Category: " + p.getCategory());
				System.out.println("Product Description: " + p.getDescription());
				System.out.println("Product Image url: " + p.getImg_url());
				System.out.println("Merchant Id: " + p.getMerchant().getId());
				System.out.println("=====================><===============");
			}
		} else {
			System.err.println("No products found for this brand");
		}
	}
	public static void main(String[] args) {
		
		System.out.println("Enter the option");
		System.out.println("1.Save Merchant");
		System.out.println("2.Update Merchant");
		System.out.println("3.Find Merchant By Id");
		System.out.println("4.Verify Merchant by phone and password");
		System.out.println("5.Verify Merchant by Email and password");
		System.out.println("6.Add Product");
		System.out.println("7.Update product");
		System.out.println("8.Find Products by merchant Id");
		System.out.println("9.Find products by brand");
		System.out.println("10.Find products by category");
		System.out.println("11.Exit");

		int choice = in.nextInt();
		switch (choice) {
		case 1: {
			saveMerchant();
			break;
		}
		case 2: {
			updateMerchant();
			break;
		}
		case 3: {
			findMerchantById();
			break;
		}
		case 4: {
			verifyMerchantByPhonePassword();
			break;
		}
		case 5: {
			verifyMerchantByEmailPassword();
			break;
		}
		case 6: {
			saveProduct();
			break;
		}
		case 7: {
			updateProduct();
			break;
		}
		case 8: {
			findProductsByMId();
			break;
		}
		case 9: {
			findProductsByBrand();
			break;
		}
		case 10: {
			findProductsByCategory();
			break;
		}
		case 11: {
			System.out.println("========>>> Thanking you <<<==========");
			System.exit(0);
			break;
		}
		default:
			System.out.println("Invalid option entered");
			break;
		}
	}
}
