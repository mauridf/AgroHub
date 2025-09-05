package com.agrohub.repository;

import com.agrohub.domain.Produtor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutorRepository extends JpaRepository<Produtor, UUID> {
}
