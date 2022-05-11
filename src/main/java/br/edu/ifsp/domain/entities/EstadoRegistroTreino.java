package br.edu.ifsp.domain.entities;

import java.util.Arrays;

public enum EstadoRegistroTreino {

    INICIADO("iniciado"),
    FINALIZADO("finalizado");

    private String estado;

    EstadoRegistroTreino(String estado) {
        this.estado = estado;
    }

    public static EstadoRegistroTreino toEnum(String value) {
        return Arrays.stream(EstadoRegistroTreino.values())
            .filter(e -> value.equals(e.toString()))
            .findAny()
            .orElseThrow(IllegalArgumentException::new
        );
    }
}
