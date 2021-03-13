package com.sahana.sportyshoes.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sahana.sportyshoes.model.Admin;
import com.sahana.sportyshoes.model.Category;
import com.sahana.sportyshoes.model.OrderDetails;
import com.sahana.sportyshoes.model.Products;
import com.sahana.sportyshoes.model.Users;
import com.sahana.sportyshoes.service.AdminService;
import com.sahana.sportyshoes.service.CategoryService;
import com.sahana.sportyshoes.service.OrderDetailsService;
import com.sahana.sportyshoes.service.ProductService;
import com.sahana.sportyshoes.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
    private AdminService adminService; 
	
	@Autowired
    private UserService userService; 
	
	@Autowired
    private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;
	
	
	@RequestMapping(value="/adminlogin",  method = RequestMethod.POST)
	public String adminLogin(ModelMap map, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value="emailId", required=true) String emailId,
   		 	@RequestParam(value="pwd", required=true) String pwd) throws IOException{
		 Admin admin = adminService.authenticate(emailId, pwd);
		 if(admin!= null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			session.setMaxInactiveInterval(250);
			
			return "redirect:adminproducts";
		 }
			 return "index";
	
	}
	
	  @RequestMapping(value = "/adminlogout", method = RequestMethod.GET)
	    public String logout(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  	HttpSession session = request.getSession();
		  	session.invalidate();
		  	
	        return "admin-login"; 
	    }
	  
	
	  @RequestMapping(value = "/adminchangepwdaction", method = RequestMethod.POST)
	    public String updatePassword(ModelMap map,  @RequestParam(value="pwd", required=true) String pwd,
	    		 @RequestParam(value="pwd2", required=true) String pwd2, 
	    		 javax.servlet.http.HttpServletRequest request)
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  
		  
		  if (pwd == null || pwd2 == null || pwd.equals("") || pwd2.equals("")) {
			  map.addAttribute("error", "Error , Incomplete passwords submitted.");
			  return "change-password";
		  }
		  if (!pwd.equals(pwd2)) {
			  map.addAttribute("error", "Passwords do not match.");
			  return "change-password";
		  }
		  Admin sessionAdmin= (Admin)session.getAttribute("admin");
		  Admin admin = adminService.getAdminById(sessionAdmin.getId()); 
		  admin.setAdminPwd(pwd);
		  adminService.updatePwd(admin);
		  
	        return "redirect:adminproducts";
	    }	
	  
	 /* @RequestMapping(value = "/editproduct",  method = RequestMethod.GET)
	    public String editProduct(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
	
		  Products product = new Products();
		  if (idValue > 0) {
			  product = productService.getProductById(idValue);
		  } else {
			  product.setProductId(0);
		  }
		
		  map.addAttribute("product", product);
		
	        return "edit-product"; 
	    }	*/
	  @RequestMapping(value = "/adminaddproduct", method = RequestMethod.POST)
	    public String updateProduct(ModelMap map, javax.servlet.http.HttpServletRequest request)
	    {
		 
			  int productId = Integer.parseInt(request.getParameter("id")); 
			  String productName = request.getParameter("name");
			  float price = Float.parseFloat(request.getParameter("price"));
			  float priceValue =0;
		  
		  if (productName == null || productName.equals("") ) { 
			  map.addAttribute("error", "Error, A product name must be specified");
			  return "edit-product";
		  }
		  
			  
		 
		  Products product = new Products();
		  	product.setProductId(productId);
		  	product.setName(productName);
		  	product.setPrice(price);
		  	
		  	productService.addProduct(product); 
		  
	        return "redirect:adminproducts"; 
	    }
	  @RequestMapping(value = "/adminproducts", method = RequestMethod.GET)
	    public String products(ModelMap map, javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  List<Products> list = productService.getAllProducts();

		 
		  map.addAttribute("list", list);
		  

	        return "admin-products"; 
	    }
	  
	   
	  @RequestMapping(value = "/deleteproduct",  method = RequestMethod.GET)
	    public String deleteProduct(ModelMap map,  @RequestParam(value="id", required=true) String id,
	    		javax.servlet.http.HttpServletRequest request) 
	    {
		  HttpSession session = request.getSession();
		  if (session.getAttribute("admin") == null) {
			  return "admin-login";
		  }
		  int productId = Integer.parseInt(id);
		 
			  productService.deleteProduct(productId);
		  
		  return "redirect:adminproducts";
	    }	
	 
}
