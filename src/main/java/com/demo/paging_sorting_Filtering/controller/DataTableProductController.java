package com.demo.paging_sorting_Filtering.controller;


import com.demo.paging_sorting_Filtering.model.Product;
import com.demo.paging_sorting_Filtering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class DataTableProductController {
    @Autowired
    private ProductService service;

/*    @RequestMapping(path="/home", method= RequestMethod.GET)
    public String goHome(Model model){
        List<Product> listProducts= service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "tableIndex";
    }*/
    @RequestMapping(path="/test", method= RequestMethod.GET)
    public String gotest(){


        return "datatable_famwork_Index";
    }

    @RequestMapping(value = "/test/save", method = RequestMethod.POST)
    @ResponseBody
    public String saveProduct(@RequestParam("name") String name ,
                              @RequestParam("brand") String brand ,
                              @RequestParam("madein") String madein ,
                              @RequestParam("price") float price ) {
        Product product=new Product();
        product.setName(name);
        product.setBrand(brand);
        product.setMadein(madein);
        product.setPrice(price);
        service.save(product);

        return "Data Saved Successfully";
    }

    @PutMapping("/test/edit")
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
    @DeleteMapping("/test/delete")
    @ResponseBody
    public String deleteProduct(@RequestParam("id") int id) {
        service.delete(id);
        return "Data Deleted Successfully";
    }
}
