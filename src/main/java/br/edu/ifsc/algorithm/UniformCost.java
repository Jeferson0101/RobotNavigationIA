/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.algorithm;

import br.edu.ifsc.dto.DataDTO;
import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Ponto;
import br.edu.ifsc.model.Nodo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Jeferson
 */
public class UniformCost {

    int nodosGerados;
    int nodosExpandidos;
    long resultTime;

    public DataDTO Buscar(Dados dados) {
        // inicializa as variáveis
        nodosGerados = 0;
        nodosExpandidos = 0;
        long startTime = System.currentTimeMillis();

        // caminho que será retornado
        ArrayList<Ponto> caminhoFinal = new ArrayList<>();
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
                    nodo[i][j].setCaminho3(i + ":" + j);
                }
            }
        }

        for (int i = 0; i < dados.getObstaculos().size(); i++) {
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setIsObstacle(Boolean.TRUE);
        }
        // seta o nó de saída
        nodo[dados.getPInicialX()][dados.getPInicialY()].setIsStart(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setVisited(Boolean.TRUE);

        nodo[dados.getPInicialX()][dados.getPInicialY()].setCaminho3(dados.getPInicialX() + ":" + dados.getPInicialX());

        // seta o nó de chegada 
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);

        //adicionando a lista
        fila.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);

        while (!fila.isEmpty()) {
            // linha e coluna para visitar os nós vizinhos 

            //System.out.println("Inicio");
            // retira o nó atual da lista de nós visitados
            nodoAtual = fila.remove();
            String caminho = nodoAtual.getCaminho3();

            linha = nodoAtual.getI() + 1;
            coluna = nodoAtual.getJ();

            /*[ ][ ][ ]
             [ ][x][ ]
             [ ][x][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }
            coluna++;
            linha--;

            /*[ ][ ][ ]
             [ ][x][x]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }
            linha--;
            coluna--;
            /*[ ][x][ ]
             [ ][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }
            linha++;
            coluna--;
            /*[ ][ ][ ]
             [x][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }

            //diagonais
            linha++;
            /*[ ][ ][ ]
             [ ][x][ ]
             [x][x][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }

            coluna = coluna + 2;
            /*[ ][ ][ ]
             [ ][x][ ]
             [ ][ ][x]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }

            linha = linha - 2;
            /*[ ][ ][x]
             [ ][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }

            coluna = coluna - 2;
            /*[x][ ][ ]
             [ ][x][ ]
             [ ][ ][ ]*/
            if (Verifica(nodo, linha, coluna, caminho, caminhoFinal, fila, dados)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
                return dto;

            }
        }
        System.out.println("Cabou os nodos da lista");
        return new DataDTO(caminhoFinal, nodosGerados, nodosExpandidos, resultTime);
    }

    public boolean Verifica(Nodo[][] nodo, int linha, int coluna, String caminho, ArrayList<Ponto> caminhoFinal, Queue<Nodo> fila, Dados dados) {
        try {
            if (!(linha < 0 || linha > dados.getTamanhoX() || coluna < 0 | coluna > dados.getTamanhoY())) {
                if (nodo[linha][coluna] != null) {
                    if (!nodo[linha][coluna].isVisited()) {
                        if (!nodo[linha][coluna].isIsStart()) {
                            if (!nodo[linha][coluna].isIsObstacle()) {
                                if (!nodo[linha][coluna].isIsEnd()) {
                                    nodosGerados++;
                                    nodo[linha][coluna].setVisited(Boolean.TRUE);
                                    // pega o caminho do nodo atual E adiciona um novo ponto
                                    nodo[linha][coluna].setCaminho3(caminho + ";" + linha + ":" + coluna);
                                    fila.add(nodo[linha][coluna]);
                                } else {
                                    // acrescenta a última coordenada!

                                    caminho = caminho + ";" + linha + ":" + coluna;
                                    Converter(caminho, caminhoFinal);
                                    return true;
                                }
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

    public ArrayList<Ponto> Converter(String pontos, ArrayList<Ponto> caminho) {

        String[] ponto = pontos.split(";");
        for (String s : ponto) {
            String[] n = s.split(":");
            int x = Integer.parseInt(n[0]);
            int y = Integer.parseInt(n[1]);
            caminho.add(new Ponto(x, y));
            // Como ele só visita. não toma decisão por distância ou heurística neste caso em específico(até poderia fazer por 1 ou 1.4). Mas por fim
            // ficou mesma distância para qualquer lado. Logo, tive que fazer os Expandidos aqui aproveitando a passagem por todas coordenadas do
            // caminho gerado
            nodosExpandidos++;
        }
        return caminho;
    }

}
