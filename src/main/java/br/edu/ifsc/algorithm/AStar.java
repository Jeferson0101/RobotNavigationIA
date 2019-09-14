/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsc.algorithm;

import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Ponto;
import br.edu.ifsc.model.Nodo;

import java.util.ArrayList;

/**
 * @author Aluno
 */
public class AStar {
    Nodo nodoAtual;
    ArrayList<Ponto> caminho = new ArrayList<>();
    ArrayList<Nodo> lista = new ArrayList<>();

    public ArrayList<Ponto> AStar(Dados dados) {
        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new Nodo(i, j);
                }
            }
        }

        //adicionando os obstaculos ao nodo
        for (int i = 0; i < dados.getObstaculos().size(); i++) {
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setIsObstacle(Boolean.TRUE);
        }

        // seta o nó de saída
        nodo[dados.getPInicialX()][dados.getPInicialY()].setIsStart(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setExplored(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setCaminho(new Ponto(dados.getPFinalX(), dados.getPFinalY()));
        // seta o nó de chegada
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);


        //adicionando a lista
        lista.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);

        while (!lista.isEmpty()) {
            nodoAtual = lista.get(0);
            int aux = 0;
            int toRemovePosition = 0;
            for (Nodo no : lista) {
                aux++;
                if ((no.getPeso() + previsaoPeso(no, dados)) < (nodoAtual.getPeso() + previsaoPeso(nodoAtual, dados))) {
                    nodoAtual = no;
                    toRemovePosition = aux - 1;
                }
            }
            nodoAtual.setExplored(Boolean.TRUE);
            lista.remove(toRemovePosition);

            caminho.add(nodoAtual.getCaminho().get(0));
            int i = nodoAtual.getI() + 1;
            int j = nodoAtual.getJ();
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;

            j++;
            i--;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;
            i--;
            j--;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;
            i++;
            j--;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;
            i++;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;
            j = j + 2;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;
            i = i - 2;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;
            j = j - 2;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(nodo, i, j))
                return caminho;
        }
        return caminho;
    }

    private boolean verificaChegada(Nodo[][] no, int i, int j) {
        if (no[i][j].isIsEnd()) {
            no[i][j].setPeso(nodoAtual.getPeso() + 1);
            caminho.add(new Ponto(i, j));
            return true;
        }
        return false;
    }

    private void verificaCaminho(Dados dados, Nodo[][] nodo, int i, int j) {
        if (!(i < 0 || i > dados.getTamanhoX() || j < 0 | j > dados.getTamanhoY())) {
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
        }
    }

    private double previsaoPeso(Nodo nodo, Dados dados) {
        int x = dados.getPFinalX();
        int y = dados.getPFinalY();


        double a = Math.abs(nodo.getI() - x);
        double b = Math.abs(nodo.getJ() - y);
        if (a > b) {
            return ((b * 1.4) + (a - b));
        } else {
            return ((a * 1.4) + (b - a));
        }

    }
}
