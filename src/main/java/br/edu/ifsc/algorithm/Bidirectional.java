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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Aluno
 */
public class Bidirectional {

    Queue<Nodo> saida = new LinkedList<Nodo>();
    Queue<Nodo> chegada = new LinkedList<Nodo>();
    ArrayList<Ponto> caminho = new ArrayList<>();

    public ArrayList<Ponto> bidirectional(Dados dados) {

        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];
        Nodo nodoAtual;

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new br.edu.ifsc.model.Nodo(i, j);
                }
            }
        }
        for (int i = 0; i < dados.getObstaculos().size(); i++) {
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setIsObstacle(Boolean.TRUE);
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

        while (!saida.isEmpty() || !chegada.isEmpty()) {
            int i, j;
            if (!saida.isEmpty()) {
                Nodo nodoA = saida.remove();
                caminho = (ArrayList<Ponto>) nodoA.getCaminho();
                i = nodoA.getI() + 1;
                j = nodoA.getJ();
                checkVisited(nodo, i, j);
                i--;
                j++;
                checkVisited(nodo, i, j);
                i--;
                j--;
                checkVisited(nodo, i, j);
                i++;
                j--;
                checkVisited(nodo, i, j);
                i++;
                checkVisited(nodo, i, j);
                j = j + 2;
                checkVisited(nodo, i, j);
                i = i - 2;
                checkVisited(nodo, i, j);
                j = j - 2;
            }
            if (!chegada.isEmpty()) {
                Nodo nodoB = chegada.remove();
                caminho = (ArrayList<Ponto>) nodoB.getCaminho();
                i = nodoB.getI() + 1;
                j = nodoB.getJ();
                checkExplored(nodo, i, j);
                j++;
                i--;
                checkExplored(nodo, i, j);
                i++;
                j--;
                checkExplored(nodo, i, j);
                i++;
                checkExplored(nodo, i, j);
                j = j + 2;
                checkExplored(nodo, i, j);
                i = i - 2;
                checkExplored(nodo, i, j);
                j = j - 2;
                checkExplored(nodo, i, j);
            }
        }
        return caminho;
    }

    public ArrayList<Ponto> checkVisited(Nodo[][] nodo, int i, int j) {
        if (nodo[i][j] != null) {
            if (!nodo[i][j].isVisited()) {
                if (!nodo[i][j].isIsObstacle()) {
                    if (!nodo[i][j].isExplored()) {
                        nodo[i][j].setVisited(true);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                        saida.add(nodo[i][j]);
                    } else {
                        return caminho;
                    }
                }
            }
        }
        return caminho;
    }
    
    public ArrayList<Ponto> checkExplored(Nodo[][] nodo, int i, int j) {
        if (nodo[i][j] != null) {
            if (!nodo[i][j].isExplored()) {
                if (!nodo[i][j].isIsObstacle()) {
                    if (!nodo[i][j].isVisited()) {
                        nodo[i][j].setExplored(true);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                        chegada.add(nodo[i][j]);
                    } else {
                        return caminho;
                    }
                }
            }
        }
        return caminho;
    }

}
