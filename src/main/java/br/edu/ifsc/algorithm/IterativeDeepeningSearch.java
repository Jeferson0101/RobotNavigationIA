/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsc.algorithm;

import br.edu.ifsc.dto.DataDTO;
import br.edu.ifsc.input.Ponto;
import static br.edu.ifsc.model.Algorithm.validatePosition;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Felip
 */
public class IterativeDeepeningSearch {
    public Stack<Matrix> stack = new Stack<>();
    public int nodeProcessed = 0;
    DataDTO dataDTO = new DataDTO();
    
    public DataDTO iterativeDeepeningSearch(Matrix init) {
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
                            long fim = (System.nanoTime());
                            nodeProcessed++;
                            System.out.println("Terminou no Nivel: " + matrix.level);
                            System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, matrix.level, stack.size()));
                            stack.clear();
                            dataDTO.setNodosExpandidos(nodeProcessed);
                            dataDTO.setNodosGerados(NodeManager.totalNodes);
                            dataDTO.setPontos(matrix.clone().getMoves());
                            dataDTO.setTempoExecucao(fim - ini);
                            return dataDTO;
                           
                          
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
                        dataDTO.setNodosExpandidos(nodeProcessed);
                        dataDTO.setNodosGerados(NodeManager.totalNodes);
                        dataDTO.setPontos(matrix.clone().getMoves());
                        dataDTO.setTempoExecucao(fim - ini);
                        return dataDTO;
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
}
