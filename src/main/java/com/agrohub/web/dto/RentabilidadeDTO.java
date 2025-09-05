package com.agrohub.web.dto;

public record RentabilidadeDTO(
        String fazendaNome,
        String safra,
        Double receitaTotal,
        Double custoTotal,
        Double rentabilidadePorHa
) {}
