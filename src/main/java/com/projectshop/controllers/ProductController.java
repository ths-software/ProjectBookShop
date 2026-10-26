package com.projectshop.controllers;

import java.util.List;

import com.projectshop.models.Stock;
import com.projectshop.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.projectshop.models.AppUser;
import com.projectshop.models.Product;
import com.projectshop.services.ProductService;
import com.projectshop.services.UserService;
import com.projectshop.storage.StorageService;

@Controller
@RequestMapping("/app")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	private AppUser appUser;

	

	

	
	

	
	
	
	

}
