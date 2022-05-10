package br.edu.ifsp.domain.entities;

import java.util.Arrays;

public enum GrupoMuscular {
    ABDOMEN ("Abdômen"),
    ANTEBRACO ("Antebraço"),
    BICEPS ("Bíceps"),
    DELTOIDES ("Deltóide"),
    OBLIQUOS ("Oblíquo"),
    TIBIALIS ("Panturrilha"),
    PEITORAL ("Peito"),
    POSTERIOR ("Posterior"),
    QUADRICEPS ("Quadríceps");

    private String musculo;

    GrupoMuscular(String musculo) {
        this.musculo = musculo;
    }

    public static GrupoMuscular toEnum(String value) {
        return Arrays.stream(GrupoMuscular.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public String toString() {
        return musculo;
    }
}