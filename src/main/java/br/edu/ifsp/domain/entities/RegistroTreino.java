package br.edu.ifsp.domain.entities;

import java.time.LocalDate;

public class RegistroTreino {

    private Integer id;
    private LocalDate inicio;
    private LocalDate fim;
    private EstadoRegistroTreino estadoRegistroTreino;
    private Usuario usuario;
    private Treino ultimoTreinoRealizado;

    public RegistroTreino() {
    }

    public RegistroTreino(Integer id, LocalDate inicio, LocalDate fim, EstadoRegistroTreino estadoRegistroTreino, Usuario usuario, Treino ultimoTreinoRealizado) {
        this.id = id;
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
        this.usuario = usuario;
        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }

    public RegistroTreino(LocalDate inicio, LocalDate fim, EstadoRegistroTreino estadoRegistroTreino, Usuario usuario, Treino ultimoTreinoRealizado) {
        this.inicio = inicio;
        this.fim = fim;
        this.estadoRegistroTreino = estadoRegistroTreino;
        this.usuario = usuario;
        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public EstadoRegistroTreino getEstadoRegistroTreino() {
        return estadoRegistroTreino;
    }

    public void setEstadoRegistroTreino(EstadoRegistroTreino estadoRegistroTreino) {
        this.estadoRegistroTreino = estadoRegistroTreino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Treino getUltimoTreinoRealizado() {
        return ultimoTreinoRealizado;
    }

    public void setUltimoTreinoRealizado(Treino ultimoTreinoRealizado) {
        this.ultimoTreinoRealizado = ultimoTreinoRealizado;
    }

    @Override
    public String toString() {
        return "\nRegistroTreino{" +
                "id=" + id +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", estadoRegistroTreino=" + estadoRegistroTreino +
                ", usuario=" + usuario +
                ", ultimoTreinoRealizado=" + ultimoTreinoRealizado +
                '}';
    }
}

