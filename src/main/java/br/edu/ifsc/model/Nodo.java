/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.model;

import br.edu.ifsc.input.Ponto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jeferson
 */
public class Nodo {
    private boolean isObstacle;
    private boolean isStart;
    private boolean isEnd;
    private boolean Visited;
    private boolean Explored;
    private String caminho2;
    List<Ponto> caminho = new ArrayList<>();
    // para o A*
    private double peso;
    private int i;
    private int j;


    public Nodo(boolean isObstacle, boolean isStart, boolean isEnd, boolean Visited, boolean Explored) {
        this.isObstacle = isObstacle;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.Visited = Visited;
        this.Explored = Explored;
        this.caminho.add(new Ponto(0,1));
    }

    public Nodo(int i, int j) {
        this.isObstacle = false;
        this.isStart = false;
        this.isEnd = false;
        this.Visited = false;
        this.Explored = false;
        this.i = i;
        this.j = j;
        this.caminho.add(new Ponto(i,j));
    }


    public boolean isIsObstacle() {
        return isObstacle;
    }

    public void setIsObstacle(boolean isObstacle) {
        this.isObstacle = isObstacle;
    }

    public boolean isIsStart() {
        return isStart;
    }

    public void setIsStart(boolean isStart) {
        this.isStart = isStart;
    }

    public boolean isIsEnd() {
        return isEnd;
    }

    public void setIsEnd(boolean isEnd) {
        this.isEnd = isEnd;
    }

    public boolean isVisited() {
        return Visited;
    }

    public void setVisited(boolean Visited) {
        this.Visited = Visited;
    }

    public boolean isExplored() {
        return Explored;
    }

    public void setExplored(boolean Explored) {
        this.Explored = Explored;
    }

    public List<Ponto> getCaminho() {
        return caminho;
    }

    public void setCaminho(Ponto caminho) {
        this.caminho.add(caminho);
    }
    
    
    public void setCaminho2(List<Ponto> caminho, Ponto novoPonto) {
        this.caminho.clear();
        this.caminho.addAll(caminho);
        this.caminho.add(novoPonto);
    }


    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public String getCaminho3() {
        return caminho2;
    }

    public void setCaminho3(String caminho2) {
        this.caminho2 = caminho2;
    }


}
