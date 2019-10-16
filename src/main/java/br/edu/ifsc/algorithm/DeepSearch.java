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
public class DeepSearch {
    public Stack<Matrix> stack = new Stack<>();
    public int nodeProcessed = 0;
    DataDTO dataDTO = new DataDTO();
    
    public DataDTO deepSearch(Matrix init) {
        stack.clear();
        long ini = (System.nanoTime());
        try {
            stack.add(init);
            while (stack.peek() != null) {
                List<Matrix> newNodes = NodeManager.generateNewMatrix(stack.pop());
                NodeManager.totalNodes += newNodes.size();
                for (Matrix newNode : newNodes) {
                    if (validatePosition(newNode, Matrix.roboFinalPositionX, Matrix.roboFinalPositionY)) {
                        long fim = (System.nanoTime());
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + newNode.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, newNode.level, stack.size()));
                        stack.clear();
                        dataDTO.setNodosExpandidos(nodeProcessed);
                        dataDTO.setNodosGerados(NodeManager.totalNodes);
                        dataDTO.setPontos(newNode.clone().getMoves());
                        dataDTO.setTempoExecucao(fim - ini);
                        return dataDTO;
                       
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
}
