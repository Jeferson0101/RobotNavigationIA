package br.edu.ifsc.navegacaoderobos;

import com.mycompany.quebracabeca.Matrix;
import com.mycompany.quebracabeca.Node;
import com.mycompany.quebracabeca.NodeManager;
import static com.mycompany.quebracabeca.NodeManager.defineMove;
import static com.mycompany.quebracabeca.NodeManager.setAllMovesFalse;
import static com.mycompany.quebracabeca.NodeManager.totalNodes;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author AAEJ
 */
public class Algorithm {

    public static int move;
    public static int sizePuzzle = 4;
    public static int nodeProcessed = 0;
    public static Queue<Matrix> queue = new ArrayDeque<>();
    public static Stack<Matrix> stack = new Stack<>();
    public static boolean runAlgorithm = true;

    public Algorithm(int sizePuzzle) {
        Algorithm.sizePuzzle = sizePuzzle;
    }

    public static boolean validatePosition(Node[][] node) {
        boolean isCorrect = true;
        int number = 1;
        stop:
        for (int i = 0; i < node.length; i++) {
            for (int j = 0; j < node[i].length; j++) {
                if (!(number == node[i][j].getNumber())) {
                    isCorrect = false;
                    break stop;
                }
                ++number;
            }
        }
        return isCorrect;
    }

    public Matrix searchWidth(Matrix init) {
        setAllMovesFalse(init.getNodes());
        defineMove(init.getNodes());
        queue.clear();
        long ini = (System.nanoTime());
        try {
            queue.add(init);
            while (queue.peek() != null && runAlgorithm) {
                List<Matrix> newNodes = NodeManager.generateNewMatrix(queue.remove());
                totalNodes += newNodes.size();
                for (Matrix newNode : newNodes) {
                    if (validatePosition(newNode.getNodes())) {
                        long fim = (System.nanoTime());
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + newNode.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, newNode.level, queue.size()));
                        System.out.println(LocalTime.ofNanoOfDay(fim - ini));
                        System.out.println("Moves:\n" + newNode.getMoves().toString());
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
        setAllMovesFalse(init.getNodes());
        defineMove(init.getNodes());
        List<Matrix> listCompare = new ArrayList<>();
        stack.clear();
        long ini = (System.nanoTime());
        try {
            stack.add(init);
            while (stack.peek() != null && runAlgorithm) {
                listCompare.add(stack.peek().clone());
                List<Matrix> newNodes = NodeManager.generateNewMatrix(stack.pop());
                totalNodes += newNodes.size();
                newNodes = goalTest(newNodes, listCompare);
                for (Matrix newNode : newNodes) {
                    if (validatePosition(newNode.getNodes())) {
                        long fim = (System.nanoTime());
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + newNode.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, newNode.level, stack.size()));
                        System.out.println(LocalTime.ofNanoOfDay(fim - ini));
                        System.out.println("Moves:\n" + newNode.getMoves().toString());
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
        setAllMovesFalse(init.getNodes());
        defineMove(init.getNodes());
        stack.clear();
        long ini = (System.nanoTime());
        try {
            stack.add(init);
            while (stack.peek() != null && runAlgorithm) {
                Matrix matrix = stack.peek();
                if (matrix.level < initLevel) {
                    nodeProcessed++;
                    List<Matrix> newNodes = NodeManager.generateNewMatrix(stack.pop());
                    NodeManager.totalNodes += newNodes.size();
                    for (Matrix newNode : newNodes) {
                        newNode.level++;
                        if (validatePosition(matrix.getNodes())) {
                            long fim = (System.nanoTime());
                            nodeProcessed++;
                            System.out.println("Terminou no Nivel: " + matrix.level);
                            System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, matrix.level, stack.size()));
                            System.out.println(LocalTime.ofNanoOfDay(fim - ini));
                            System.out.println("Moves:\n" + matrix.getMoves().toString());
                            stack.clear();
                            return matrix.clone();
                        }
                        stack.push(newNode.clone());
                    }
                } else {
                    if (validatePosition(stack.pop().getNodes())) {
                        long fim = (System.nanoTime());
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + matrix.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, matrix.level, stack.size()));
                        System.out.println(LocalTime.ofNanoOfDay(fim - ini));
                        System.out.println("Moves:\n" + matrix.getMoves().toString());
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

    private List<Matrix> goalTest(List<Matrix> list, List<Matrix> listCompare) {
        for (int i = 0; i < list.size(); i++) {
            if (listCompare.contains(list.get(i))) {
                list.remove(i);
            }
        }
        return list;
    }
}
