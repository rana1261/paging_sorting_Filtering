package com.demo.paging_sorting_Filtering.controller;

import com.demo.paging_sorting_Filtering.model.Product;
import com.demo.paging_sorting_Filtering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class DataTableProductRestController {
    @Autowired
    private ProductService service;

    @RequestMapping(path="/home", method= RequestMethod.GET)
    public String goHome(Model model){
        List<Product> listProducts= service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "tableIndex";
    }

    @RequestMapping(value = "/home/save", method = RequestMethod.POST)
    @ResponseBody
    public Product saveProduct(@RequestParam("name") String name ,
                              @RequestParam("brand") String brand ,
                              @RequestParam("madein") String madein ,
                              @RequestParam("price") float price ) {
        Product product=new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setMadein(madein);
        product.setPrice(price);
        service.save(product);

        return product;
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    @ResponseBody
    public List<Product> getAllEmployees() {
        return service.listAll();
    }


    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Product getProductById(@PathVariable("id") long id) {
        return service.get(id);
    }

    @PutMapping("/home/edit")
    @ResponseBody
    public Product EditProduct(@RequestParam("eid") int id,
                               @RequestParam("ename") String name ,
                               @RequestParam("ebrand") String brand ,
                               @RequestParam("emadein") String madein ,
                               @RequestParam("eprice") float price) {

        Product product = service.get(id);
        product.setName(name);
        product.setBrand(brand);
        product.setMadein(madein);
        product.setPrice(price);
        service.save(product);

        return product;
    }
    @DeleteMapping("/home/delete")
    @ResponseBody
    public String deleteProduct(@RequestParam("id") int id) {
        service.delete(id);
        return "Data Deleted Successfully";
    }


}
