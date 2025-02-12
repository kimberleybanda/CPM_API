package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.Deals;
import com.brokeroffice.springbootws.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersRepo extends JpaRepository<Users, Long> {

    List<Users> findAll();
    Users findById(long Id);

    Users findByPhone(String phone);
    Users save(Users users);

}
