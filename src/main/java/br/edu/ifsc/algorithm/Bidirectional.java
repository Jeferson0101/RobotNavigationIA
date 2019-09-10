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
        Nodo nodoAtual; 
        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];
        

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new Nodo(i, j);
                }
            }
        }

        for (int i = 0; i < dados.getObstaculos().size(); i++) {
            for (int x = 0; x <= dados.getObstaculos().get(i).getX(); x++) {
                for (int y = 0; y <= dados.getObstaculos().get(i).getY(); y++) {
                    nodo[dados.getObstaculos().get(i).getX() + x][dados.getObstaculos().get(i).getY() + y].setIsObstacle(Boolean.TRUE);
                    int t1 = dados.getObstaculos().get(i).getX() + x;
                    int t2 = dados.getObstaculos().get(i).getY() + y;
                    System.out.println("\n\nT1:" + t1 + "\nt2: " + t2);
                }
            }

        }

        nodo[dados.getPInicialX()][dados.getPInicialY()].setIsStart(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setExplored(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setCaminho(new Ponto(dados.getPFinalX(), dados.getPFinalY()));
        // seta o nó de chegada
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);

        saida.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);
        chegada.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);
        //caminho.add(new Ponto(0, 0));
        while (!saida.isEmpty() || !chegada.isEmpty()) {
            int i, j;
            if (!saida.isEmpty()) {
                Nodo nodoA = saida.remove();
                caminho.add(new Ponto(nodoA.getI(), nodoA.getJ()));
                //caminho = (ArrayList<Ponto>) nodoA.getCaminho();
                //caminho.add(nodoA.getCaminho().get(0));
                i = nodoA.getI() + 1;
                j = nodoA.getJ();
                checkVisited(nodo, i, j, dados);
                i--;
                j++;
                checkVisited(nodo, i, j, dados);
                i--;
                j--;
                checkVisited(nodo, i, j, dados);
                i++;
                j--;
                checkVisited(nodo, i, j, dados);
                i++;
                checkVisited(nodo, i, j, dados);
                j = j + 2;
                checkVisited(nodo, i, j, dados);
                i = i - 2;
                checkVisited(nodo, i, j, dados);
                j = j - 2;
            }
            if (!chegada.isEmpty()) {
                Nodo nodoB = chegada.remove();
                //caminho = (ArrayList<Ponto>) nodoB.getCaminho();
                //caminho.add(nodoB.getCaminho().get(0));
                i = nodoB.getI() + 1;
                j = nodoB.getJ();
                checkExplored(nodo, i, j, dados);
                j++;
                i--;
                checkExplored(nodo, i, j, dados);
                i++;
                j--;
                checkExplored(nodo, i, j, dados);
                i++;
                checkExplored(nodo, i, j, dados);
                j = j + 2;
                checkExplored(nodo, i, j, dados);
                i = i - 2;
                checkExplored(nodo, i, j, dados);
                j = j - 2;
                checkExplored(nodo, i, j, dados);
            }
        }
        return caminho;
    }

    public ArrayList<Ponto> checkVisited(Nodo[][] nodo, int i, int j, Dados dados) {
        //System.out.println("A: " + i + " " + j);
        if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isExplored()) {
                            nodo[i][j].setVisited(true);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            //caminho.add(new Ponto(i, j));
                            saida.add(nodo[i][j]);
                        } else {
                            return caminho;
                        }
                    }
                }
            }
        }
        return caminho;
    }

    public ArrayList<Ponto> checkExplored(Nodo[][] nodo, int i, int j, Dados dados) {
        //System.out.println("B: " + i + " " + j);
        if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isExplored()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isVisited()) {
                            nodo[i][j].setExplored(true);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            chegada.add(nodo[i][j]);
                            //caminho.add(new Ponto(i, j));
                        } else {
                            return caminho;
                        }
                    }
                }
            }
        }

        return caminho;
    }

}
