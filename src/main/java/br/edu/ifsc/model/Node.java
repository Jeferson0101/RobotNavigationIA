/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.model;

public class Node implements Cloneable {

    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean isObstacle;
    private boolean isRobo;

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public Node clone() throws CloneNotSupportedException {
        return (Node) super.clone();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + (this.up ? 1 : 0);
        hash = 73 * hash + (this.down ? 1 : 0);
        hash = 73 * hash + (this.left ? 1 : 0);
        hash = 73 * hash + (this.right ? 1 : 0);
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
        final Node other = (Node) obj;
        if (this.up != other.up) {
            return false;
        }
        if (this.down != other.down) {
            return false;
        }
        if (this.left != other.left) {
            return false;
        }
        if (this.right != other.right) {
            return false;
        }

        return true;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean obstacle) {
        isObstacle = obstacle;
    }

    public boolean isRobo() {
        return isRobo;
    }

    public void setRobo(boolean robo) {
        isRobo = robo;
    }
}
