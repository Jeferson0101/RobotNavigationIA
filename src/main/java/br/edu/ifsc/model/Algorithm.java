package br.edu.ifsc.model;

import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import static br.edu.ifsc.model.Algoritmos.*;

/**
 *
 * @author Felipe
 */ 

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
    public Queue<Matrix> queue = new ArrayDeque<>();
    public Stack<Matrix> stack = new Stack<>();


    private boolean validatePosition(Matrix node, int pFinalX, int pFinalY) {
        return node.roboActualPositionX == pFinalX && node.roboActualPositionY == pFinalY;
    }

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
    
    public void execute(Matrix initialmatrix, int tipoAlg) {
        try {
            long init = System.nanoTime();
            switch (tipoAlg) {
                case Busca_Profundidade:
                    result = deepSearch(initialmatrix);
                    break;
                case Profundidade_Interativa:
                    result = iterativeDeepeningSearch(initialmatrix);
                    break;
                case Bidirecional:
                    bidirectional();
                    break;
                case Custo_Uniforme:
                    break;
                case A_Estrela:
                    break;
                case Largura:
                    result = searchWidth(initialmatrix);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tipoAlg);
            }
            long end = System.nanoTime();

            if (result != null) {
                System.out.println("Algoritmo: "+tipoAlg);
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
}
