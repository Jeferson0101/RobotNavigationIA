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
 * @author Jeferson
 */
public class UniformCost {

    public List<Ponto> Buscar(Dados dados) {

        // caminho que será retornado
        List<Ponto> caminho;
        //List de Pontos caso der failure ele retornará esta List
        ArrayList<Ponto> failure = new ArrayList<>();
        int linha, coluna;
        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];
        // fila onde será guardado os nós conforme forem visitados
        Queue<Nodo> fila = new LinkedList<Nodo>();
        // nodoAtual usado no WHile logo abaixo para pegar sempre o topo da fila
        Nodo nodoAtual;

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new Nodo(i, j);
                }
            }
        }

//        for (int i = 0; i < dados.getObstaculos().size(); i++) {
//            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setIsObstacle(Boolean.TRUE);
//        }
        // seta o nó de saída
        nodo[dados.getPInicialX()][dados.getPInicialY()].setIsStart(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setCaminho(new Ponto(dados.getPInicialX(), dados.getPInicialY()));
        // seta o nó de chegada 
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);

        //adicionando a lista
        fila.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);

        while (!fila.isEmpty()) {
            // linha e coluna para visitar os nós vizinhos 

            //System.out.println("Inicio");
            // retira o nó atual da lista de nós visitados
            nodoAtual = fila.remove();
            caminho = nodoAtual.getCaminho();

            linha = nodoAtual.getI() + 1;
            coluna = nodoAtual.getJ();

            /*[ ][ ][ ]
             [ ][x][ ]
             [ ][x][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }
            coluna++;
            linha--;

            /*[ ][ ][ ]
             [ ][x][x]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }
            linha--;
            coluna--;
            /*[ ][x][ ]
             [ ][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }
            linha++;
            coluna--;
            /*[ ][ ][ ]
             [x][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }

            //diagonais
            linha++;
            /*[ ][ ][ ]
             [ ][x][ ]
             [x][x][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }

            coluna = coluna + 2;
            /*[ ][ ][ ]
             [ ][x][ ]
             [ ][ ][x]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }

            linha = linha - 2;
            /*[ ][ ][x]
             [ ][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }

            coluna = coluna - 2;
            /*[x][ ][ ]
             [ ][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, fila, dados)) {
                return caminho;
            }
        }
        System.out.println("Cabou os nodos da lista");
        return failure;
    }

    public boolean Verifica(Nodo[][] nodo, int linha, int coluna, List<Ponto> caminho, Queue<Nodo> fila, Dados dados) {
        try {
            if (!(linha < 0 || linha > dados.getTamanhoX() || coluna < 0 | coluna > dados.getTamanhoY())) {
                if (nodo[linha][coluna] != null) {
                    if (!nodo[linha][coluna].isIsStart()) {
                        if (!nodo[linha][coluna].isIsObstacle()) {
                            if (!nodo[linha][coluna].isIsEnd()) {
                                nodo[linha][coluna].setVisited(Boolean.TRUE);
                            // pega o caminho do nodo atual E adiciona um novo ponto

                                nodo[linha][coluna].setCaminho2(caminho, new Ponto(linha, coluna));
                                fila.add(nodo[linha][coluna]);
                            } else {
                                System.out.println("Entrei");
                                System.out.println(caminho.toString());
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        } catch (Exception e) {

            System.out.println(e);
            System.out.println("linha: " + linha + "\n coluna: " + coluna);
            return false;
        }
        return false;
    }

}
