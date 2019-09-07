package br.edu.ifsc.model;

import br.edu.ifsc.algorithm.Bidirectional;
import br.edu.ifsc.algorithm.DeepSearch;
import br.edu.ifsc.algorithm.IterativeDeepeningSearch;
import br.edu.ifsc.algorithm.SearchWidth;
import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Ponto;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;

import static br.edu.ifsc.model.Algoritmos.*;

/**
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
    DeepSearch dSearch = new DeepSearch();
    IterativeDeepeningSearch iDSearch = new IterativeDeepeningSearch();
    SearchWidth sWidth = new SearchWidth();
    Bidirectional bi = new Bidirectional();
    
    public static boolean validatePosition(Matrix node, int pFinalX, int pFinalY) {
        return node.roboActualPositionX == pFinalX && node.roboActualPositionY == pFinalY;
    }

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

}
