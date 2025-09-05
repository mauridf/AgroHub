package com.agrohub.repository;

import com.agrohub.domain.Produtor;
import com.agrohub.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutorRepository extends JpaRepository<Produtor, UUID> {
    Optional<Produtor> findByUsuario(Usuario usuario);
    List<Produtor> findAllByUsuario(Usuario usuario);
}
