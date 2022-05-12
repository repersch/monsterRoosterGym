package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class FichaTreino {
    private Integer id;
    private String nome;
    private Boolean valido;
    private LocalDate dataInicio;
    private LocalDate validade;
    private Usuario instrutor;
}
