package com.projectshop.controllers;

import com.projectshop.models.AppUser;
import com.projectshop.models.Buy;
import com.projectshop.models.Product;
import com.projectshop.models.Stock;
import com.projectshop.services.BuyService;
import com.projectshop.services.ProductService;
import com.projectshop.services.StockService;
import com.projectshop.services.UserService;
import com.projectshop.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    /*
        Añadir servicios necesarios
     */

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    StockService stockService;

    @Autowired
    StorageService storageService;

    @Autowired
    BuyService buyService;

    @GetMapping("/adminProducts")
    public String getProducts(Model model, @RequestParam(name = "q", required = false) String query)
    {
        List<Product> products;

        if(query != null)
        {
            products = productService.search(query);
        }
        else
        {
            products = productService.findAll();
        }

        Map<Product, Stock> productStockMap = new LinkedHashMap<>();

        for(Product product : products)
        {
            Stock s = stockService.findByProduct(product);
            productStockMap.put(product,s);
        }

        model.addAttribute("productStockMap",productStockMap);
        model.addAttribute("filter", true);
        model.addAttribute("addProduct", true);
        return "admin/adminProducts";
    }


    @GetMapping("/product/new")
    public String getNewProduct(Model model)
    {
        model.addAttribute("product", new Product());
        return "admin/addProductAdmin";
    }


    @PostMapping("/product/new/submit")
    public String NewProduct(@ModelAttribute Product product, @RequestParam("stock") int stock,
                             @RequestParam("file") MultipartFile file)
    {

        if(!file.isEmpty())
        {
            String image = storageService.store(file);
            product.setImage(MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "serveFile", image).build().toUriString());
        }
        Stock newStockProduct = new Stock(product,stock);

        productService.insert(product);
        stockService.insert(newStockProduct);

        return "redirect:/admin/adminProducts";
    }



    @GetMapping("/product/edit/{id}")
    public String getEditProduct(@PathVariable long id, Model model)
    {
        Product product = productService.findById(id);
        Stock stock = stockService.findByProduct(product);

        model.addAttribute("product", product);
        model.addAttribute("stock",stock);
        return "admin/editProductAdmin";
    }


    @PostMapping("/product/edit/submit")
    public String EditProduct(@ModelAttribute Product product, @RequestParam("stock") int stock, @RequestParam("file") MultipartFile file)
    {
        Product oldProduct = productService.findById(product.getId());
        if(!file.isEmpty())
        {
            String image = storageService.store(file);
            product.setImage(MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "serveFile", image).build().toUriString());
        }
        else
        {
            product.setImage(oldProduct.getImage());
        }

        Stock newStockProduct = stockService.findByProduct(product);
        newStockProduct.setStockQuantity(stock);

        productService.insert(product);
        stockService.insert(newStockProduct);

        return "redirect:/admin/adminProducts";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id)
    {
        Product p = productService.findById(id);
        Stock s = stockService.findByProduct(p);

        stockService.delete(s);
        productService.delete(id);

        return "redirect:/admin/adminProducts";
    }

    //


    @GetMapping("/adminUsers")
    public String getUsers(Model model, @RequestParam(name = "q", required = false) String query)
    {
        List<AppUser> users;

        if(query != null)
        {
            users = new ArrayList<>();
            AppUser u = userService.emailFilter(query);
            users.add(u);

            users.addAll(userService.findByName(query));
        }
        else
        {
            users = userService.findAll();
        }

        model.addAttribute("users", users);
        model.addAttribute("filter", true);
        model.addAttribute("addProduct", false);
        return "admin/adminUsers";
    }

    @GetMapping("/user/edit/{id}")
    public String getEditUser(@PathVariable long id, Model model)
    {
        AppUser u = userService.findById(id);

        model.addAttribute("user", u);

        return "admin/editUserAdmin";
    }


    @PostMapping("/user/edit/submit")
    public String EditUser(@ModelAttribute AppUser user)
    {
        AppUser u = userService.findById(user.getId());

        u.setName(user.getName());
        u.setSurName(user.getSurName());
        userService.userEdit(u);
        return "redirect:/admin/adminUsers";
    }


    @PostMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        userService.delete(id);

        return "redirect:/admin/adminUsers";
    }

    //

    @GetMapping("/adminBuys")
    public String getBuys(Model model)
    {
        model.addAttribute("buys", buyService.findAll());
        return "/admin/adminBuys";
    }


    @GetMapping("/buy/edit/{id}")
    public String getBuyEdit(@PathVariable long id, Model model)
    {
        Buy b = buyService.findById(id);
        List<Product> products = productService.productOfABuy(b);

        model.addAttribute("buy", b);
        model.addAttribute("productsOfBuy", products);

        return "admin/editBuyAdmin";
    }


    @PostMapping("/buy/edit/submit")
    public String EditBuy(@ModelAttribute Buy buy)
    {
        Buy b = buyService.findById(buy.getId());

        b.setBuyState(buy.getBuyState());
        buyService.insert(b);
        return "redirect:/admin/adminBuys";
    }


}
