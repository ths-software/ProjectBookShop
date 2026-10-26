package com.projectshop.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.projectshop.models.Stock;
import com.projectshop.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;
import com.projectshop.models.Product;
import com.projectshop.services.BuyService;
import com.projectshop.services.ProductService;
import com.projectshop.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class BuyController {

	@Autowired
	BuyService buyService;

	@Autowired
	ProductService productService;

	@Autowired
	StockService stockService;

	@Autowired
	UserService userService;

	@Autowired
	HttpSession session;

	private AppUser appUser;

	@ModelAttribute("carrito")
	public List<Product> getCartProducts() {

		List<Product> content = (List<Product>) session.getAttribute("carrito");

		return content;
	}

	@ModelAttribute("total_carrito")
	public Double totalCarrito() {
		List<Product> productosCarrito = getCartProducts();
		if (productosCarrito != null) {
			return productosCarrito.stream().mapToDouble(p -> p.getPrice() * p.getBuyQuantity()).sum();
		}
		return 0.0;
	}


	@GetMapping("/carrito")
	public String getCarrito(Model model) {
		return "app/compra/carrito";

	}


	@PostMapping("/carrito/add/{id}")
	public String addCarrito(Model model, @PathVariable Long id, @RequestParam int quantity)
	{

		List<Product> content = getCartProducts();
		Product product = productService.findById(id);

		if(content == null)
		{
			content = new ArrayList<>();
		}
		if(!content.contains(product))
		{
			product.setBuyQuantity(quantity);
			content.add(product);
		}
		session.setAttribute("carrito", content);
		return "redirect:/public/producto/" + id;
	}


	@PostMapping("/comprar/add/{id}")
	public String addComprar(Model model, @PathVariable Long id, @RequestParam int quantity)
	{
		List<Product> content = getCartProducts();
		Product product = productService.findById(id);

		if(content == null)
		{
			content = new ArrayList<>();
		}
		if(!content.contains(product))
		{
			product.setBuyQuantity(quantity);
			content.add(product);
		}
		session.setAttribute("carrito", content);
		return "redirect:/app/carrito";
	}
	
	@GetMapping("/carrito/eliminar/{id}")
	public String deleteFromCarrito(Model model, @PathVariable Long id)
	{
		List<Product> content = getCartProducts();
		Product product = productService.findById(id);

		if(content == null)
		{
			return "redirect:/public";
		}
		boolean borrado = content.remove(product);
		
		if(content.isEmpty()) 
		{
			session.removeAttribute("carrito");
		}
		else
		{
			session.setAttribute("carrito", content);
		}
		
		return "redirect:/app/carrito";
	}
	
	//
	
	
	// Aquí procesamos la compra, y luego enseñamos la factura
	@GetMapping("/carrito/finalizar")
	public String checkout()
	{
		
		List<Product> products = getCartProducts();
		
		if(products == null)
		{
			return "redirect:/public";
		}

		
		System.out.println("productsSizeFinalizar: "+products.size());
		
		Buy b = buyService.insert(appUser, new Buy());
		
		
		products.forEach(p-> buyService.addBuyToProduct(p, b));

		for (Product p : products) {
			Stock stock = stockService.findByProduct(p);
			stock.setStockQuantity(stock.getStockQuantity()-p.getBuyQuantity());
			stockService.insert(stock);
		}
		
		session.removeAttribute("carrito");
		
		return "redirect:/app/compra/factura/"+b.getId();
	}
	
	
	
	@GetMapping("/compra/factura/{id}")
	public String factura(Model model, @PathVariable Long id)
	{
		Buy b = buyService.findById(id);
		List<Product> productosCompra = productService.productOfABuy(b);
		
		
		model.addAttribute("productos", productosCompra);
		model.addAttribute("compra",b);
		model.addAttribute("total_compra",productosCompra.stream().mapToDouble(p->p.getPrice() * p.getBuyQuantity()).sum());
		
		return "/app/compra/factura";
	}

	/*
		Los ModelAttribute se ejecutan antes que cada GetMapping etc.

		La vista "listado" requiere de una variable "mis_compras" que se obtiene justo en el metodo de abajo.
		El metodo obtiene el usuario actualmente autenticado mediante el email introducido y busca las compras hechas

	 */

	@ModelAttribute("mis_compras")
	public List<Buy> misCompras() {
		// obtenemos user actualmente autenticado
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		appUser = userService.emailFilter(email); // busca el user por el email
		return buyService.findByPropietary(appUser);  // devuelve compras hechas por un user

	}

	
	@GetMapping("/miscompras")
	public String getMyBuys(Model model)
	{
		return "/app/compra/listado";
	}

}
