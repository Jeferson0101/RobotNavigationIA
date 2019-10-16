package br.edu.ifsc.input;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * @author Felipe
 */

public class Dados {

    public Dados() {
        pInicialX = 2;
        pInicialY = 2;
        pFinalX = 5;
        pFinalY = 5;
    }
    
    @SerializedName("tamanhoX")
    @Expose
    private int tamanhoX;
    @SerializedName("tamanhoY")
    @Expose
    private int tamanhoY;
    @SerializedName("pInicialX")
    @Expose
    private int pInicialX;
    @SerializedName("pInicialY")
    @Expose
    private int pInicialY;
    @SerializedName("pFinalX")
    @Expose
    private int pFinalX;
    @SerializedName("pFinalY")
    @Expose
    private int pFinalY;
    @SerializedName("tipoAlg")
    @Expose
    private int tipoAlg;
    @SerializedName("obstaculos")
    @Expose
    private List<Obstaculo> obstaculos = null;

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

    public int getPInicialX() {
        return pInicialX;
    }

    public void setPInicialX(int pInicialX) {
        this.pInicialX = pInicialX;
    }

    public int getPInicialY() {
        return pInicialY;
    }

    public void setPInicialY(int pInicialY) {
        this.pInicialY = pInicialY;
    }

    public int getPFinalX() {
        return pFinalX;
    }

    public void setPFinalX(int pFinalX) {
        this.pFinalX = pFinalX;
    }

    public int getPFinalY() {
        return pFinalY;
    }

    public void setPFinalY(int pFinalY) {
        this.pFinalY = pFinalY;
    }

    public int getTipoAlg() {
        return tipoAlg;
    }

    public void setTipoAlg(int tipoAlg) {
        this.tipoAlg = tipoAlg;
    }

    public List<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public void setObstaculos(List<Obstaculo> obstaculos) {
        this.obstaculos = obstaculos;
    }

    @Override
    public String toString() {
        return "Dados{" + "tamanhoX=" + tamanhoX + ", tamanhoY=" + tamanhoY + ", pInicialX=" + pInicialX + ", pInicialY=" + pInicialY + ", pFinalX=" + pFinalX + ", pFinalY=" + pFinalY + ", tipoAlg=" + tipoAlg + ", obstaculos=" + obstaculos + '}';
    }

}
