package com.agrohub.application;

import com.agrohub.domain.Usuario;
import com.agrohub.repository.UsuarioRepository;
import com.agrohub.infrastructure.security.JwtService;
import com.agrohub.web.dto.AuthDTOs.*;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.senha, usuario.getSenhaHash())) {
            throw new RuntimeException("Senha inválida");
        }

        usuario.setUltimoLogin(LocalDateTime.now());
        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario.getEmail(), usuario.getRole());

        LoginResponse response = new LoginResponse();
        response.token = token;
        response.role = usuario.getRole();
        return response;
    }

    public void register(RegisterRequest request) {
        if (usuarioRepository.findByEmail(request.email).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.email);
        usuario.setSenhaHash(passwordEncoder.encode(request.senha));
        usuario.setRole(request.role);
        usuario.setDataCadastro(LocalDateTime.now());

        usuarioRepository.save(usuario);
    }
}
