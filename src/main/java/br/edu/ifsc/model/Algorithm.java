package br.edu.ifsc.model;

import br.edu.ifsc.algorithm.AStar;
import br.edu.ifsc.algorithm.Bidirectional;
import br.edu.ifsc.algorithm.DeepSearch;
import br.edu.ifsc.algorithm.IterativeDeepeningSearch;
import br.edu.ifsc.algorithm.SearchWidth;
import br.edu.ifsc.algorithm.UniformCost;
import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Ponto;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.*;

import static br.edu.ifsc.model.Algoritmos.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.Writer;

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
    Robot robot = new Robot();
    public static int move;
    public int nodeProcessed = 0;
    DeepSearch dSearch = new DeepSearch();
    IterativeDeepeningSearch iDSearch = new IterativeDeepeningSearch();
    SearchWidth sWidth = new SearchWidth();
    Bidirectional bidirecional = new Bidirectional();
    UniformCost custoUniforme = new UniformCost();
    AStar aStar = new AStar();
    Gson gson = new Gson();
    String output = "C:\\Users\\Jeferson\\Documents\\RobotNavigationIA\\src\\main\\java\\br\\edu\\ifsc\\jsons\\resultado.json";

    ArrayList<Ponto> pontos = new ArrayList<>();

    public static boolean validatePosition(Matrix node, int pFinalX, int pFinalY) {
        return node.roboActualPositionX == pFinalX && node.roboActualPositionY == pFinalY;
    }

    public ArrayList<Ponto> execute(Matrix initialmatrix, Dados dados) {
        try {
            long init = System.nanoTime();
            switch (dados.getTipoAlg()) {
                case Busca_Profundidade:
                    pontos = dSearch.deepSearch(initialmatrix);
                    break;
                case Profundidade_Interativa:
                    pontos = iDSearch.iterativeDeepeningSearch(initialmatrix);
                    break;
                case Bidirecional:
                    pontos = bidirecional.Converter(bidirecional.Buscar(dados));
                    break;
                case Custo_Uniforme:
                    pontos = (ArrayList<Ponto>) custoUniforme.Buscar(dados);
                    break;
                case A_Estrela:
                    pontos = aStar.AStar(dados);
                    break;
                case Largura:
                    pontos = sWidth.searchWidth(initialmatrix);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + dados.getTipoAlg());
            }
            long end = System.nanoTime();

            if (pontos != null) {
                try ( Writer writer = new FileWriter(output)) {
                    Gson gson = new GsonBuilder().create();
                    gson.toJson(pontos, writer);
                    robot.Enviar(gson.toJson(pontos));
                } catch (Exception ex) {
                    System.out.println("Deu ruim");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
