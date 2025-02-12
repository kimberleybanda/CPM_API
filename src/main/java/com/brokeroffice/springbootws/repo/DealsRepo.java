package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.Deals;
import com.brokeroffice.springbootws.entities.Incentives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DealsRepo extends JpaRepository<Deals, Long> {

    List<Deals> findAll();
    Deals findById(long Id);
    Deals save(Deals deals);

    @Query(nativeQuery = true, value = "select count(*) from deals where status=1 and user_id=:user_id")
    int dealsStatsApproved(@Param("user_id") Long user_id);

    @Query(nativeQuery = true, value = "select count(*) from deals where status=0 and user_id=:user_id")
    int dealsStatsNonApproved(@Param("user_id") Long user_id);

}
