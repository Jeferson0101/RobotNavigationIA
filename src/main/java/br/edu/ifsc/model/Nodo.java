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
 *
 * @author Jeferson
 */
public class Nodo {
  private boolean isObstacle;
  private boolean isStart;
  private boolean isEnd;
  private boolean Visited;
  private boolean Explored;
  List<Ponto> caminho = new ArrayList<>();
  // para o A*
  private double peso;

    public Nodo(boolean isObstacle, boolean isStart, boolean isEnd, boolean Visited, boolean Explored) {
        this.isObstacle = isObstacle;
        this.isStart = isStart;
        this.isEnd = isEnd;
        this.Visited = Visited;
        this.Explored = Explored;
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

    public void setCaminho(List<Ponto> caminho) {
        this.caminho = caminho;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
  
  
  
  
}
