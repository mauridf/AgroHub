package com.agrohub.web.dto;

public class AuthDTOs {

    public static class LoginRequest {
        public String email;
        public String senha;
    }

    public static class LoginResponse {
        public String token;
        public String role;
    }

    public static class RegisterRequest {
        public String email;
        public String senha;
        public String role; // ADM ou PROD
    }
}
