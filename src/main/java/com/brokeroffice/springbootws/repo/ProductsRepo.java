package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.Products;
import com.brokeroffice.springbootws.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {

    List<Products> findAll();
    Products findById(long Id);
    Products save(Products products);
    void deleteById(Long Long);
}
