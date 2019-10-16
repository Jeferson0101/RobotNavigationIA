/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifsc.algorithm;

import br.edu.ifsc.dto.DataDTO;
import br.edu.ifsc.input.Ponto;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
import java.time.LocalTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Felip
 */
public class SearchWidth {
    public Queue<Matrix> queue = new ArrayDeque<>();
    public int nodeProcessed = 0;
    DataDTO dataDTO = new DataDTO();
    public DataDTO searchWidth(Matrix init) {
        queue.clear();
        long ini = (System.nanoTime());
        try {
            queue.add(init);
            while (queue.peek() != null) {
                List<Matrix> newNodes = NodeManager.generateNewMatrix(queue.remove());
                NodeManager.totalNodes += newNodes.size();
                for (Matrix newNode : newNodes) {
                    if (br.edu.ifsc.model.Algorithm.validatePosition(newNode, Matrix.roboFinalPositionX, Matrix.roboFinalPositionY)) {
                        long fim = (System.nanoTime());
                        nodeProcessed++;
                        System.out.println("Terminou no Nivel: " + newNode.level);
                        System.out.println(String.format("Total de Nodos Gerados: %d Total de Nodos Processados: %d Nivel: %d Sobrou na Fila: %d", NodeManager.totalNodes, nodeProcessed, newNode.level, queue.size()));
                        queue.clear();
                        dataDTO.setNodosExpandidos(nodeProcessed);
                        dataDTO.setNodosGerados(NodeManager.totalNodes);
                        dataDTO.setPontos(newNode.clone().getMoves());
                        dataDTO.setTempoExecucao(fim - ini);
                        return dataDTO;
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
    
}
