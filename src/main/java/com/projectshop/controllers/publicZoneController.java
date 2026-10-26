package com.projectshop.controllers;

import java.util.ArrayList;
import java.util.List;

import com.projectshop.models.Stock;
import com.projectshop.services.StockService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projectshop.models.Product;
import com.projectshop.services.ProductService;

@Controller
@RequestMapping("/public")
public class publicZoneController {
	
	@Autowired
	ProductService productService;

	@Autowired
	HttpSession session;

	@Autowired
	StockService stockService;

	/* // ya no queremos cargar productos sin ventas, cargaremos TODOS los productos
	@ModelAttribute("productos")
	public List<Product> ProductWithoutSales()
	{
		return productService.productWithOutSales();
	}

	 */

	@ModelAttribute("productos")
	public List<Product> loadProducts()
	{
		return productService.findAll();
	}
	
	
	
	@GetMapping({"/","/index"})
	public String getPublicHome(Model model, @RequestParam(name = "q", required = false) String query,
								@RequestParam(name="page", defaultValue = "1")int page)
	{
		int itemsPerPage = 20;

		int blockSize = 5;
		int currentBlock = (page - 1) / blockSize;

		int totalRegisters = productService.findAll().size();

		int totalPages = (int)Math.ceil((double)totalRegisters / itemsPerPage);

		int start = currentBlock * blockSize + 1;
		int end = Math.min(start + blockSize - 1, totalPages);

		Long offset = (long)(page - 1) * itemsPerPage;
		Long limit = (long)itemsPerPage;

		List<Product> paginedList = productService.pagination(offset,limit);
		List<Integer> pages = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			pages.add(i);
		}

		boolean hasNextBlock = end < totalPages;
		boolean manualFiltering = false;
		if(query != null && !query.isEmpty())
		{
			List<Product> productFilter = productService.search(query);
			model.addAttribute("productos", productFilter);
		}
		else
		{
			model.addAttribute("productos", paginedList);
		}

		model.addAttribute("filter", true);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("pages",pages);
		model.addAttribute("hasNextBlock",hasNextBlock);
		model.addAttribute("lastPage",totalPages);
		return "index";
	}
	
	
	@GetMapping("/producto/{id}")
	public String showProduct(Model model, @PathVariable Long id)
	{
		Product result = productService.findById(id);
		Stock productStock = stockService.findByProduct(result);

		if(result != null)
		{
			model.addAttribute("producto",result);
			model.addAttribute("productStock", productStock);
			return "producto";
		}
		return"redirect:/public";
	}


	@ModelAttribute("carrito")
	public List<Product> getCartProducts() {
		List<Product> content = (List<Product>) session.getAttribute("carrito");
		return content;
	}

	@GetMapping("/aboutus")
	public String getAboutUs(Model model)
	{
		return"aboutUs";
	}

	@GetMapping("/news")
	public String getNews(Model model)
	{
		return"news";
	}
	

}
