package br.edu.ifsc.model;

<<<<<<< HEAD
import br.edu.ifsc.algorithm.Bidirectional;
import br.edu.ifsc.algorithm.DeepSearch;
import br.edu.ifsc.algorithm.IterativeDeepeningSearch;
import br.edu.ifsc.algorithm.SearchWidth;
=======
>>>>>>> master
import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Ponto;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;

import static br.edu.ifsc.model.Algoritmos.*;

/**
 * @author Felipe
 */
<<<<<<< HEAD
=======

>>>>>>> master
class Algoritmos {

    public static final int Busca_Profundidade = 0;
    public static final int Profundidade_Interativa = 1;
    public static final int Bidirecional = 2;
    public static final int Custo_Uniforme = 3;
    public static final int A_Estrela = 4;
    public static final int Largura = 5;
}

public class Algorithm {

    public static int move;
    private Matrix result;

    public int nodeProcessed = 0;
    DeepSearch dSearch = new DeepSearch();
    IterativeDeepeningSearch iDSearch = new IterativeDeepeningSearch();
    SearchWidth sWidth = new SearchWidth();
    Bidirectional bi = new Bidirectional();
    
    public static boolean validatePosition(Matrix node, int pFinalX, int pFinalY) {
        return node.roboActualPositionX == pFinalX && node.roboActualPositionY == pFinalY;
    }

<<<<<<< HEAD
=======
    public Matrix searchWidth(Matrix init) {
        queue.clear();
        long ini = (System.nanoTime());
        try {
            queue.add(init);
            while (queue.peek() != null) {
                List<Matrix> newNodes = NodeManager.generateNewMatrix(queue.remove());
                NodeManager.totalNodes += newNodes.size();
                for (Matrix newNode : newNodes) {
                    if (validatePosition(newNode, Matrix.roboFinalPositionX, Matrix.roboFinalPositionY)) {
                        long fim = (System.nanoTime());
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + newNode.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, newNode.level, queue.size()));
                        queue.clear();
                        return newNode.clone();
                    } else {
                        queue.add(newNode.clone());
                    }
                }
                nodeProcessed++;
            }
        } catch (Exception ex) {
            long fim = (System.nanoTime());
            System.out.println(LocalTime.ofNanoOfDay(fim - ini) + ex.getMessage());
        }
        return null;
    }

    public Matrix deepSearch(Matrix init) {
        stack.clear();
        long ini = (System.nanoTime());
        try {
            stack.add(init);
            while (stack.peek() != null) {
                List<Matrix> newNodes = NodeManager.generateNewMatrix(stack.pop());
                NodeManager.totalNodes += newNodes.size();
                for (Matrix newNode : newNodes) {
                    if (validatePosition(newNode, Matrix.roboFinalPositionX, Matrix.roboFinalPositionY)) {
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + newNode.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, newNode.level, stack.size()));
                        stack.clear();
                        return newNode.clone();
                    } else {
                        stack.push(newNode);
                    }
                }
                nodeProcessed++;
            }
        } catch (Exception ex) {
            long fim = (System.nanoTime());
            System.out.println(LocalTime.ofNanoOfDay(fim - ini) + ex.getMessage());
        }
        System.err.println("Not found solution");
        return null;
    }

    public Matrix iterativeDeepeningSearch(Matrix init) {
        int initLevel = 1;
        stack.clear();
        long ini = (System.nanoTime());
        try {
            stack.add(init);
            while (stack.peek() != null) {
                Matrix matrix = stack.peek();
                if (matrix.level < initLevel) {
                    nodeProcessed++;
                    List<Matrix> newNodes = NodeManager.generateNewMatrix(stack.pop());
                    NodeManager.totalNodes += newNodes.size();
                    for (Matrix newNode : newNodes) {
                        newNode.level++;
                        if (validatePosition(matrix, Matrix.roboFinalPositionX, Matrix.roboFinalPositionY)) {
                            nodeProcessed++;
                            System.out.println("Terminou no Nivel: " + matrix.level);
                            System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, matrix.level, stack.size()));
                            stack.clear();
                            return matrix.clone();
                        }
                        stack.push(newNode.clone());
                    }
                } else {
                    if (validatePosition(stack.pop(), Matrix.roboFinalPositionX, Matrix.roboFinalPositionY)) {
                        long fim = (System.nanoTime());
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + matrix.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, matrix.level, stack.size()));
                        stack.clear();
                        return matrix.clone();
                    }
                }
                if (stack.isEmpty()) {
                    initLevel++;
                    stack.push(init);
                }
            }
        } catch (Exception ex) {
            long fim = (System.nanoTime());
            System.out.println(LocalTime.ofNanoOfDay(fim - ini) + ex.getMessage());
        }

        System.err.println("Not found solution");

        return null;
    }

    public void bidirectional() {
        System.out.println("Calculando...");
    }

    public ArrayList<Ponto> AStar(Dados dados) {
        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];
        ArrayList<Ponto> caminho = new ArrayList<>();
        ArrayList<Nodo> lista = new ArrayList<>();
        Nodo nodoAtual;

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new Nodo(i,j);
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
        nodo[dados.getPInicialX()][dados.getPInicialY()].setCaminho(new Ponto(dados.getPFinalX(),dados.getPFinalY()));
        // seta o nó de chegada
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);



        //adicionando a lista
        lista.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);

        while (!lista.isEmpty()) {
            nodoAtual = lista.get(0);
            int teste = 0;
            int remove = 0;
            for (Nodo no : lista) {
                teste++;
                if ((no.getPeso() + previsaoPeso(no, dados)) < (nodoAtual.getPeso() + previsaoPeso(nodoAtual,dados))) {
                    nodoAtual = no;
                    remove = teste - 1;
                }
            }
            nodoAtual.setExplored(Boolean.TRUE);
            lista.remove(remove);

            caminho.add(nodoAtual.getCaminho().get(0));
            int i = nodoAtual.getI() + 1;
            int j = nodoAtual.getJ();
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            j++;
            i--;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i--;
            j--;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i++;
            j--;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i++;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            j = j + 2;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i = i - 2;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            j = j - 2;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
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
        return caminho;
    }

>>>>>>> master
    public void execute(Matrix initialmatrix, int tipoAlg) {
        try {
            long init = System.nanoTime();
            switch (tipoAlg) {
                case Busca_Profundidade:
                    result = dSearch.deepSearch(initialmatrix);
                    break;
                case Profundidade_Interativa:
                    result = iDSearch.iterativeDeepeningSearch(initialmatrix);
                    break;
                case Bidirecional:
                    //bi.bidirectional(null);
                    break;
                case Custo_Uniforme:
                    break;
                case A_Estrela:
                    break;
                case Largura:
                    result = sWidth.searchWidth(initialmatrix);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tipoAlg);
            }
            long end = System.nanoTime();

            if (result != null) {
                System.out.println("Algoritmo: " + tipoAlg);
                System.out.println("Tempo: " + LocalTime.ofNanoOfDay(end - init).toString());
                System.out.println("Movimentos: " + result.getMoves().toString());
                System.out.println("Nivel: " + result.level);
                System.out.println("Nodos Processados: " + nodeProcessed);
                nodeProcessed = 0;
                System.out.println("Total de Nodos Gerados: " + NodeManager.totalNodes);
                NodeManager.totalNodes = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

<<<<<<< HEAD
=======
    private double previsaoPeso(Nodo nodo,Dados dados) {
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
>>>>>>> master
}
