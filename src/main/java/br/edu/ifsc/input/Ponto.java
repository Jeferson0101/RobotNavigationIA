/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.input;

import br.edu.ifsc.model.Matrix;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import br.edu.ifsc.model.NodeManager;
import java.util.ArrayList;

/**
 *
 * @author Jeferson
 */
public class Ponto {
    
    
    
    private int x,y;

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{\"x\": " + x +
                ", \"y\": " + y +"}";
    }
}
