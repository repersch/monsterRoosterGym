package br.edu.ifsp.domain.entities;

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

    @Override
    public String toString() {
        return musculo;
    }
}
