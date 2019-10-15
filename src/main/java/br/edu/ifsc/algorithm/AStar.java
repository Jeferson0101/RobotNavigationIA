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

/**
 * @author Iago Bianquini
 */
public class AStar {
    Nodo nodoAtual;
    ArrayList<Ponto> caminho = new ArrayList<>();
    ArrayList<Nodo> lista = new ArrayList<>();
    int nodosGerados;
    int nodosExpandidos;
    long resultTime;


    /**
     *
     * @param dados os dados vindos do frontEnd, contendo pontos iniciais, finais e obstaculos.
     * @return dto  Retorna um objeto contendo o caminho gerado pelo algoritmo, alem do tempo de execução, nodos gerados e nodos expandidos.
     */
    public DataDTO AStar(Dados dados) {
        long startTime = System.currentTimeMillis();
        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];

        /**
         * Instancia a matriz referente ao tamanho do mapa que o robo pode percorrer.
         */
        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new Nodo(i, j);
                }
            }
        }

        /**
         * adicionando os obstaculos ao nodo
         */
        for (int i = 0; i < dados.getObstaculos().size(); i++) {
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setIsObstacle(Boolean.TRUE);
        }

        /**
         * seta o nó de saída
          */
        nodo[dados.getPInicialX()][dados.getPInicialY()].setIsStart(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setExplored(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setCaminho(new Ponto(dados.getPFinalX(), dados.getPFinalY()));

        /**
         * seta o nó de chegada
         */
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);


        //adicionando a lista
        lista.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);

        /**
         * Faz o processamento do algoritmo em si, realizando verificacoes nos nodos, além de verificar a euristica.
         */
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
            nodosExpandidos++;
            lista.remove(toRemovePosition);

            caminho.add(nodoAtual.getCaminho().get(0));
            int i = nodoAtual.getI() + 1;
            int j = nodoAtual.getJ();
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }

            j++;
            i--;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }
            i--;
            j--;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }
            i++;
            j--;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }
            i++;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }
            j = j + 2;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }
            i = i - 2;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }
            j = j - 2;
            this.verificaCaminho(dados, nodo, i, j);
            if (verificaChegada(dados,nodo, i, j)) {
                long stopTime = System.currentTimeMillis();
                long resultTime = stopTime - startTime;
                DataDTO dto = new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
                return dto;
            }
        }
         return new DataDTO(caminho, nodosGerados, nodosExpandidos, resultTime);
    }


    /**
     * verifica se o ponto na matriz é ponto final passado pelo frontEnd.
     *
     * @param no Matriz referente ao mapa que o robo pode percorrer.
     * @param i ponto X onde o robo esta parado (processando).
     * @param j ponto Y onde o robo esta parado (processando).
     * @return retorna True caso seja o ponto de chegada, retorna False caso contrario.
     */
    private boolean verificaChegada(Dados dados,Nodo[][] no, int i, int j) {
        if (!(i < 0 || i > dados.getTamanhoX() || j < 0 | j > dados.getTamanhoY())) {
            if (no[i][j].isIsEnd()) {
                no[i][j].setPeso(nodoAtual.getPeso() + 1);
                caminho.add(new Ponto(i, j));
                System.out.println("Saida");
                return true;
            }
        }
        return false;
    }

    /**
     * Realiza a verifcao se o ponto que o robo esta proparado ja foi visitado ou expandido, tambem verifica se este ponto é um obstaculo ou não.
     * @param dados Os dados enviados pelo frontEnd, para poder fazer a verificao de possiveis obstaculos.
     * @param nodo Matriz referente ao mapa que o robo pode percorrer.
     * @param i ponto X onde o robo esta parado (processando).
     * @param j ponto Y onde o robo esta parado (processando).
     */
    private void verificaCaminho(Dados dados, Nodo[][] nodo, int i, int j) {
        if (!(i < 0 || i > dados.getTamanhoX() || j < 0 | j > dados.getTamanhoY())) {
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodosGerados++;
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

    /**
     * Euristica do processamento
     * @param nodo Nodo atual do robo
     * @param dados Os dados enviados pelo frontEnd
     * @return Retorna o valor da euristica do ponto em que o robo esta.
     */
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
