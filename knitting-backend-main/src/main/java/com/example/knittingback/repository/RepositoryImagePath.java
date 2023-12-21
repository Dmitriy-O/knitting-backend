package com.example.knittingback.repository;

import com.example.knittingback.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@org.springframework.stereotype.Repository

public interface RepositoryImagePath extends JpaRepository<ImageEntity,Long> {
    ImageEntity findById(long id);
    ImageEntity findByName (String name);
    ImageEntity findByFilePath (String path);
    ImageEntity findTopByName(String name);


}
