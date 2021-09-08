package com.demo.paging_sorting_Filtering.controller;

import com.demo.paging_sorting_Filtering.model.Product;
import com.demo.paging_sorting_Filtering.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService service;

/*    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<Product> listProducts = service.listAll();
        model.addAttribute("listProducts", listProducts);

        return "index";
    }*/

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        return viewPage(model, 1,"name","asc");
    }

    @RequestMapping("/row/{rowPerPages}/{startCount}")
    public String rowPerPage(Model model, @PathVariable(name = "rowPerPages") int rowPerPages,
                             @PathVariable(name = "startCount") int startCount) {
        service.rowPerPage=rowPerPages;
        int sayThisPage=startCount+(rowPerPages-1);
        int currentPageNumber=(int)sayThisPage/rowPerPages;
        return viewPage(model, currentPageNumber,"name","asc");
    }

/*    @RequestMapping("/{pageNum}/{searchWord}")
    public String searchPage(Model model,@PathVariable(name = "pageNum") int pageNum,
                             @PathVariable(name = "searchWord") String searchWord) {

        Page<Product> page = service.searchItems(pageNum,searchWord);
        List<Product> listProducts = page.getContent();
        long startCount=(pageNum-1)* service.rowPerPage+1;
        long endCount=startCount+service.rowPerPage-1;

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        System.out.println(searchWord+""+page.getTotalPages()+" "+page.getTotalElements()+" "+listProducts+" "+startCount+" "+endCount);
        return "index";
    }*/


    @RequestMapping("/page/{pageNum}")
    public String viewPage(Model model, @PathVariable(name = "pageNum") int pageNum,
                           @RequestParam("sortField") String sortField,
                           @RequestParam("sortDir") String sortDir) {

        Page<Product> page = service.listAll(pageNum, sortField, sortDir);

        List<Product> listProducts = page.getContent();

        long startCount=(pageNum-1)* service.rowPerPage+1;
        long endCount=startCount+service.rowPerPage-1;

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        return "index";
    }
    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);

        return "new_product";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        service.save(product);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_product");
        Product product = service.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
