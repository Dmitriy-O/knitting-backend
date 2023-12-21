package com.example.knittingback.repository;

import com.example.knittingback.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface RepositoryClient extends JpaRepository<ClientEntity,Long> {
}
