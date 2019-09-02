package br.edu.ifsc.input;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Felipe
 */

public class Front_ {

    @SerializedName("dados")
    @Expose
    private Dados dados;
    @SerializedName("algoritmo")
    @Expose
    private int algoritmo;

    public Dados getDados() {
        return dados;
    }

    public void setDados(Dados dados) {
        this.dados = dados;
    }

    public int getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(int algoritmo) {
        this.algoritmo = algoritmo;
    }

    @Override
    public String toString() {
        return "Front_{" + "dados=" + dados + ", algoritmo=" + algoritmo + '}';
    }

}
