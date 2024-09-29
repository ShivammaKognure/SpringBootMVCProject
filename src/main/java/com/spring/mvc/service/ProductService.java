package com.spring.mvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.entities.ProductEntity;
import com.spring.mvc.modals.ProductModel;
import com.spring.mvc.repository.ProductRepository;

@Service
public class ProductService {
	
	//add Repository dependency
	@Autowired
	ProductRepository productRepository;
	
	//when controller send the form data to save it will perfom business
	//logics and save
	public void saveProduct(ProductModel productModel) 
	{
		double price = productModel.getProPrice();
		String category = productModel.getProCategory();
		
		double disPrice=0.0;
		
		switch(category) {
		
		case "Mobile":
			        disPrice = price *0.1;
			break;
		case "Laptop":
					disPrice = price *0.15;
			break;
		case "Tablet":
					disPrice = price *0.2;
			break;
			default:
				
		}
		
		//in database entity will store
		ProductEntity productEntity = new ProductEntity();
		
		//get from model class and set to entity class
		productEntity.setProName(productModel.getProName());
		productEntity.setProPrice(productModel.getProPrice());
		productEntity.setProDescription(productModel.getProDescription());
		productEntity.setProBrand(productModel.getProBrand());
		productEntity.setProCategory(productModel.getProCategory());
		
		productEntity.setProPriceDisc(disPrice);
		productEntity.setCreatedAt(LocalDate.now());
		productEntity.setCreatedBy(System.getProperty("user.name"));
		
		//save method to svae the data in database
		productRepository.save(productEntity);
		
		
	}

   //it will return all list 
	public List<ProductEntity> getAllProducts() {
		
		//findAll() method retun all list of table
		List<ProductEntity> products1 = productRepository.findAll();	
		
		return products1;
		
	}

    //it will return data by id
	public ProductEntity getProductById(Long id) {
		
		//findById() is db method it will return Optional class to handle null exception
		Optional<ProductEntity> productEntity = productRepository.findById(id);
		ProductEntity product=null;
		
		if(productEntity.isPresent()) {
			product = productEntity.get();
			
		}
		
		return product;
	}


	//it will delete the product by id
	public void deleteProduct(Long id) {
		
		productRepository.deleteById(id);
		
	}


	//it will return edit page with product by id
	public ProductModel editProduct(Long id) {

	Optional<ProductEntity> productOpt = productRepository.findById(id);
	ProductEntity productEntity=null;
	
	ProductModel productModel = new ProductModel();
	
	if(productOpt.isPresent()) {
		productEntity= productOpt.get();
		}
	
	productModel.setProName(productEntity.getProName());
	productModel.setProPrice(productEntity.getProPrice());
	productModel.setProBrand(productEntity.getProBrand());
	productModel.setProCategory(productEntity.getProCategory());
	productModel.setProDescription(productEntity.getProDescription());
	
	return productModel;
		
	}
	


	//it will save the updated data
	public void saveEditProduct(Long id, ProductModel productModel) {
		
	Optional<ProductEntity> productOpt=	productRepository.findById(id);
	ProductEntity productEntity =null;
	
	if(productOpt.isPresent()) {
		productEntity = productOpt.get();
		
		}
	productEntity.setProName(productModel.getProName());
	productEntity.setProBrand(productModel.getProBrand());
	productEntity.setProCategory(productModel.getProCategory());
	productEntity.setProDescription(productModel.getProDescription());
	productEntity.setProPrice(productModel.getProPrice());
	
	double price = productModel.getProPrice();
	String category = productModel.getProCategory();
	
	double disPrice=0.0;
	
	switch(category) {
	
	case "Mobile":
		        disPrice = price *0.1;
		break;
	case "Laptop":
				disPrice = price *0.15;
		break;
	case "Tablet":
				disPrice = price *0.2;
		break;
		default:
			
	}
	productEntity.setProPriceDisc(disPrice);
	
	productRepository.save(productEntity);
	
		
	}
	
	

}
