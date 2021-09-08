package com.demo.paging_sorting_Filtering.repository;

import com.demo.paging_sorting_Filtering.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
/*    @Query("SELECT p FROM Product p WHERE CONCAT(p.id, ' ', p.name, ' ', p.brand, ' ', p.madein, ' ', p.price) LIKE %?1%")
    Page<Product> search(String name, Pageable pageable);*/
}
