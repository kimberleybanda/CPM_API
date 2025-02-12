package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.Product;
import com.brokeroffice.springbootws.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findAll();
    Product findById(long Id);
    Product save(Product products);

}
