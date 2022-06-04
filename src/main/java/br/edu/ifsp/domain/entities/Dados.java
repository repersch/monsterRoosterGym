package br.edu.ifsp.domain.entities;

public class Dados {

    private Integer idUsuarioAutenticado;
    private Integer idAuxiliar;

    private Integer idAuxiliar2;

    public Dados(Integer idUsuarioAutenticado, Integer idAuxiliar) {
        this.idUsuarioAutenticado = idUsuarioAutenticado;
        this.idAuxiliar = idAuxiliar;
    }

    public Dados(Integer idUsuarioAutenticado, Integer idAuxiliar, Integer idAuxiliar2) {
        this.idUsuarioAutenticado = idUsuarioAutenticado;
        this.idAuxiliar = idAuxiliar;
        this.idAuxiliar2 = idAuxiliar2;
    }

    public Dados(Integer idUsuarioAutenticado) {
        this.idUsuarioAutenticado = idUsuarioAutenticado;
        this.idAuxiliar = null;
    }

    public Integer getIdUsuarioAutenticado() {
        return idUsuarioAutenticado;
    }

    public void setIdUsuarioAutenticado(Integer idUsuarioAutenticado) {
        this.idUsuarioAutenticado = idUsuarioAutenticado;
    }

    public Integer getIdAuxiliar() {
        return idAuxiliar;
    }

    public void setIdAuxiliar(Integer idAuxiliar) {
        this.idAuxiliar = idAuxiliar;
    }

    public Integer getIdAuxiliar2() {
        return idAuxiliar2;
    }

    public void setIdAuxiliar2(Integer idAuxiliar2) {
        this.idAuxiliar2 = idAuxiliar2;
    }
}
