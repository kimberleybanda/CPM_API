package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.UserTypes;
import com.brokeroffice.springbootws.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UsersTypeRepo extends JpaRepository<UserTypes, Long> {

    List<UserTypes> findAll();


}
