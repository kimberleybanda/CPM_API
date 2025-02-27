package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.Countries;
import com.brokeroffice.springbootws.entities.Deals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CountriesRepo extends JpaRepository<Countries, Long> {

    List<Countries> findAll();
    Countries findById(long Id);
    Countries save(Countries countries);


}
