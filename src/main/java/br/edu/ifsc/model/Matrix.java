/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.model;

import br.edu.ifsc.input.Ponto;
import java.util.ArrayList;
import java.util.Arrays;

public class Matrix implements Cloneable {

    public int level;
    private Node[][] nodes;
    private ArrayList<Ponto> moves = new ArrayList<>();
    public static int tamanhoX;
    public static int tamanhoY;
    public int roboActualPositionX;
    public int roboActualPositionY;
    public static int roboFinalPositionX;
    public static int roboFinalPositionY;

    public Matrix(Node[][] nodes) {
        this.nodes = nodes;
    }

    public Matrix() {
        moves = new ArrayList<Ponto>();
    }

    public ArrayList<Ponto> getMoves() {
        return moves;
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    @Override
    public Matrix clone() throws CloneNotSupportedException {
        return (Matrix) super.clone();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Matrix other = (Matrix) obj;

        if (!Arrays.deepEquals(this.nodes, other.nodes)) {
            return false;
        }
        return true;
    }

    public int getRoboActualPositionX() {
        return roboActualPositionX;
    }

    public void setRoboActualPositionX(int roboActualPositionX) {
        this.roboActualPositionX = roboActualPositionX;
    }

    public int getRoboActualPositionY() {
        return roboActualPositionY;
    }

    public void setRoboActualPositionY(int roboActualPositionY) {
        this.roboActualPositionY = roboActualPositionY;
    }

    public void setMoves(ArrayList<Ponto> stringBuilder) {
        this.moves = stringBuilder;
    }
}
