package com.agrohub.repository;

import com.agrohub.domain.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InsumoRepository extends JpaRepository<Insumo, UUID> {}

