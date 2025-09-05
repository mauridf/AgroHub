package com.agrohub.repository;

import com.agrohub.domain.Cultura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CulturaRepository extends JpaRepository<Cultura, UUID> {
}
