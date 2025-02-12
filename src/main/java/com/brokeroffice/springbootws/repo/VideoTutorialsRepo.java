package com.brokeroffice.springbootws.repo;

import com.brokeroffice.springbootws.entities.Product;
import com.brokeroffice.springbootws.entities.VideoTutorials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VideoTutorialsRepo extends JpaRepository<VideoTutorials, Long> {

    List<VideoTutorials> findAll();
    VideoTutorials findById(long Id);
    VideoTutorials save(VideoTutorials videoTutorials);

}
