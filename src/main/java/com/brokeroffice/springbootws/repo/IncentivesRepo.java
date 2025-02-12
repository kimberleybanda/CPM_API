package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.Incentives;
import com.brokeroffice.springbootws.entities.PdfTutorials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IncentivesRepo extends JpaRepository<Incentives, Long> {

    List<Incentives> findAll();
    Incentives findById(long Id);
    Incentives save(Incentives incentives);

}
