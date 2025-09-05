package com.agrohub.web.dto;

public record UsoAreasDTO(
        String fazendaNome,
        Double areaTotalHa,
        Double areaAgricultavelHa,
        Double areaVegetacaoHa,
        Double areaConstruidaHa
) {}
