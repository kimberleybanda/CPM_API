package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.PdfTutorials;
import com.brokeroffice.springbootws.entities.VideoTutorials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PdfTutorialsRepo extends JpaRepository<PdfTutorials, Long> {

    List<PdfTutorials> findAll();
    PdfTutorials findById(long Id);
    PdfTutorials save(PdfTutorials pdfTutorials);

}
