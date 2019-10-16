package br.edu.ifsc.input;

import java.util.List;

public class FrontData {

    private List<Obstaculo> obstaculos;

    private int pFinalX;

    private int pFinalY;

    private int pInicialX;

    private int pInicialY;

    private int tamanhoX;

    private int tamanhoY;

    private int tipoAlg;

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public void setObstaculos(List<Obstaculo> obstaculos) {
        this.obstaculos = obstaculos;
    }

    public int getpFinalX() {
        return pFinalX;
    }

    public void setpFinalX(int pFinalX) {
        this.pFinalX = pFinalX;
    }

    public int getpFinalY() {
        return pFinalY;
    }

    public void setpFinalY(int pFinalY) {
        this.pFinalY = pFinalY;
    }

    public int getpInicialX() {
        return pInicialX;
    }

    public void setpInicialX(int pInicialX) {
        this.pInicialX = pInicialX;
    }

    public int getpInicialY() {
        return pInicialY;
    }

    public void setpInicialY(int pInicialY) {
        this.pInicialY = pInicialY;
    }

    public int getTamanhoX() {
        return tamanhoX;
    }

    public void setTamanhoX(int tamanhoX) {
        this.tamanhoX = tamanhoX;
    }

    public int getTamanhoY() {
        return tamanhoY;
    }

    public void setTamanhoY(int tamanhoY) {
        this.tamanhoY = tamanhoY;
    }

    public int getTipoAlg() {
        return tipoAlg;
    }

    public void setTipoAlg(int tipoAlg) {
        this.tipoAlg = tipoAlg;
    }

    @Override
    public String toString() {
        return "FrontData{" +
                "obstaculos=" + obstaculos +
                ", pFinalX=" + pFinalX +
                ", pFinalY=" + pFinalY +
                ", pInicialX=" + pInicialX +
                ", pInicialY=" + pInicialY +
                ", tamanhoX=" + tamanhoX +
                ", tamanhoY=" + tamanhoY +
                ", tipoAlg=" + tipoAlg +
                '}';
    }
}
