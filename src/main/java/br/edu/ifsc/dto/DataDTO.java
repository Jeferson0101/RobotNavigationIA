package br.edu.ifsc.dto;

import br.edu.ifsc.input.Ponto;

import java.util.ArrayList;

public class DataDTO {

    private ArrayList<Ponto> pontos;
    private int nodosGerados;
    private int nodosExpandidos;
    private long tempoExecucao;

    public DataDTO(ArrayList<Ponto> pontos, int nodosGerados, int nodosExpandidos, long tempoExecucao) {
        this.pontos = pontos;
        this.nodosGerados = nodosGerados;
        this.nodosExpandidos = nodosExpandidos;
        this.tempoExecucao = tempoExecucao;
    }

    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    public void setPontos(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
    }

    public int getNodosGerados() {
        return nodosGerados;
    }

    public void setNodosGerados(int nodosGerados) {
        this.nodosGerados = nodosGerados;
    }

    public int getNodosExpandidos() {
        return nodosExpandidos;
    }

    public void setNodosExpandidos(int nodosExpandidos) {
        this.nodosExpandidos = nodosExpandidos;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }

    public void setTempoExecucao(long tempoExecucao) {
        this.tempoExecucao = tempoExecucao;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "pontos=" + pontos +
                ", nodosGerados=" + nodosGerados +
                ", nodosExpandidos=" + nodosExpandidos +
                ", tempoExecucao=" + tempoExecucao +
                '}';
    }
}
