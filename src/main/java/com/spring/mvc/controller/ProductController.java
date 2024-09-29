package com.spring.mvc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.entities.ProductEntity;
import com.spring.mvc.modals.ProductModel;
import com.spring.mvc.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class ProductController {
	
	//adding dependency of Service class
	@Autowired
	ProductService productService;
	
	//it will give the add product form
	@GetMapping("/addProduct")               //url for add product page
	public String getProductForm(Model model) {
		
		//create the empty object of productmodel to send to form
		ProductModel productModel =new ProductModel();
		
		//productModel.setProBrand("Sony");//set by default value for brand
		
		model.addAttribute("productModel", productModel);//add the empty obj to model it will add in form
		
		model.addAttribute("page", "productForm");//add the product file to model it will add index file
		
		
		return "index";//by default return index page with add-product form
	}
	
	//save the product data
	@PostMapping("/saveProduct")      //@Valid for validation purpose and binding result hold the errors 
	public String saveProductData(@Valid ProductModel productModel,BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			//if error return same adding form with error
			return "add-product";
		}
		
		//pass model object to service layer
		productService.saveProduct(productModel);
		
		model.addAttribute("page", "success");//success page to index
		
		return "index";
	}
	
	//it will display all product list
	
	@GetMapping("/getProducts")
	public String getAllProducts(Model model) {
		
		List<ProductEntity> products=productService.getAllProducts();
		model.addAttribute("products",products);  //add list to page
		model.addAttribute("page", "getProducts");  //add list page to index
		
		return "index";
	}
	
	//it will return the product search form
	@GetMapping("/getProductSearchForm")
	public String getProdSearchForm(Model model) {
		
           model.addAttribute("page", "getProdSearchForm");
		
		return "index";
		
	}
	
	//it take id from search form and find product
	@PostMapping("/productFind")
	public String getProductById(Long id,Model model) {
		
		ProductEntity product = productService.getProductById(id);
		
				if(product!=null) {
					model.addAttribute("product",product);
					
					model.addAttribute("page","productFind");
					
					return "index";
				}
				
			else {
					model.addAttribute("errorMessage", "Product Not Found");
					
					return "product-search";
				 }
		
				//return "index";
		}
	
	//it will take id from list and delte the product
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam Long proId) {
		
		productService.deleteProduct(proId);
		
		
		return "redirect:getProducts";//redirect to product list url
	}
	
	
	//it will display edit form with data based on id
	@GetMapping("/edit")                         
	public String editProduct(@RequestParam Long proId,Model model) 
	{
		ProductModel productModel = productService.editProduct(proId);
		
		model.addAttribute("proId",proId);  //add id to form
		
		model.addAttribute("productModel",productModel); // add existing details to form
		model.addAttribute("page", "edit");
		
		return "index";
		
	}
	
	//it will save the updated data
	@PostMapping("/editSave")
	public String saveEditProduct(@ModelAttribute ProductModel productModel,@RequestParam Long proId) {
		productService.saveEditProduct(proId,productModel);
		
		return "redirect:getProducts";
	}

	//it will return the about us page
	@GetMapping("/about")
	public String aboutUs(Model model) {
		
		model.addAttribute("page", "about");
		
		return "index";
	}
	
	//it will return the contact page
	@GetMapping("/contact")
	public String contactUs(Model model) {
		model.addAttribute("page", "contact");
		
		return "index";
	}
	
//	//by default open the index page
//	@GetMapping("/")
//	public String getHomePAge() {c
//		return "index";
//	}
	
	// it will return the home page
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("page", "home");
		return "index";
	}

}
