package com.agrohub.web.dto;

public record ProducaoSafraDTO(
        String safra,
        String fazendaNome,
        String culturaNome,
        Double areaPlantadaHa,
        Double produtividadeEsperada,
        Double produtividadeObtida,
        Double receitaTotal
) {}
