package com.agrohub.web.dto;

import java.util.Map;

public class DashboardDTOs {

    public record ProducaoPorSafra(String safra, double quantidadeTotal) {}
    public record RentabilidadePorHectare(String fazenda, double rentabilidade) {}
    public record CustoReceita(String fazenda, double custoTotal, double receitaTotal) {}
    public record UsoDeAreas(String fazenda, double areaAgricultavel, double areaVegetacao) {}
    public record GraficoPizza(String label, double valor) {}
    public record PrevisaoReceita(String fazenda, double receitaEsperada) {}
    // outros DTOs podem ser adicionados conforme necessidade
}

