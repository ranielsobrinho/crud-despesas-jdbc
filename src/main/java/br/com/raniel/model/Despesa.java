package br.com.raniel.model;

import java.time.LocalDate;

public class Despesa {
    private Long id;
    private String descricao;
    private LocalDate date;
    private double valor;
    private Categoria categoria;
}
