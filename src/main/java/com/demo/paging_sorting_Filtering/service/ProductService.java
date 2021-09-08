package com.demo.paging_sorting_Filtering.service;

import com.demo.paging_sorting_Filtering.model.Product;
import com.demo.paging_sorting_Filtering.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;
    public static int rowPerPage = 1;

    public List<Product> listAll() {
        return repo.findAll();
    }
/*   public Page<Product> listAll(int pageNum) {
       //int pageSize = 1;

       Pageable pageable = PageRequest.of(pageNum - 1, rowPerPage);

       return repo.findAll(pageable);
   }*/
/*   public Page<Product> searchItems(int pageNum, String searchWord) {

       Pageable pageable = PageRequest.of(pageNum - 1, rowPerPage);
       return repo.search(searchWord,pageable);
   }*/





    public Page<Product> listAll(int pageNum, String sortField, String sortDir) {

        Pageable pageable = PageRequest.of(pageNum - 1, rowPerPage,
                sortDir.equals("asc")? Sort.by(sortField).ascending():Sort.by(sortField).descending());
        return repo.findAll(pageable);
    }

    public void save(Product product) {
        repo.save(product);
    }

    public Product get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
