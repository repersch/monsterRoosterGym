package br.edu.ifsp.domain.entities;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exercicio {
    private Integer id;
    private String nome;
    private GrupoMuscular grupoMuscular;
    private String descricao;
    private Boolean emUso;

    public Exercicio(String nome, GrupoMuscular grupoMuscular, String descricao, Boolean emUso) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.descricao = descricao;
        this.emUso = emUso;
    }
}
