package br.edu.ifsp.domain.entities;

public class Dados {

    private Integer idUsuarioAutenticado;
    private Integer idAuxiliar;

    public Dados(Integer idUsuarioAutenticado, Integer idAuxiliar) {
        this.idUsuarioAutenticado = idUsuarioAutenticado;
        this.idAuxiliar = idAuxiliar;
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
}
