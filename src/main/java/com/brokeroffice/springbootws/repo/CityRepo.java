package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.City;
import com.brokeroffice.springbootws.entities.Countries;
import com.brokeroffice.springbootws.entities.Deals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CityRepo extends JpaRepository<City, Long> {

    List<City> findAll();
    City findById(long Id);
    City save(City city);


}
