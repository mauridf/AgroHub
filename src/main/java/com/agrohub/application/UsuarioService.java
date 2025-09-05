package com.agrohub.application;

import com.agrohub.domain.Usuario;
import com.agrohub.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}

