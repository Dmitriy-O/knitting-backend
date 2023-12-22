package com.example.knittingback.repository;

import com.example.knittingback.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * T: The type of the entity that the repository will manage.
 * ID: The type of the identifier for the entity.
 */
@org.springframework.stereotype.Repository
public interface RepositoryItemEntity extends JpaRepository<ItemEntity, Long> {
    ItemEntity findById (long id);
}
