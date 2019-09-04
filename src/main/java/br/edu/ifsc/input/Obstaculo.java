package br.edu.ifsc.input;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 *
 * @author Felipe
 */

public class Obstaculo {
    
    public Obstaculo(){
        
    }
    @SerializedName("x")
    @Expose
    private int x;
    @SerializedName("y")
    @Expose
    private int y;
    @SerializedName("larg")
    @Expose
    private int larg;
    @SerializedName("altura")
    @Expose
    private int altura;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLarg() {
        return larg;
    }

    public void setLarg(int larg) {
        this.larg = larg;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

}
