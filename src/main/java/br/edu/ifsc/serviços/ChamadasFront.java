package br.edu.ifsc.serviços;

import br.edu.ifsc.algorithm.*;
import br.edu.ifsc.dto.DataDTO;
import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.FrontData;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
import br.edu.ifsc.model.Robot;
import com.google.gson.Gson;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/robo")
public class ChamadasFront {

    private DataDTO dto = new DataDTO();
    private AStar aStar = new AStar();
    private Bidirectional bidirecional = new Bidirectional();
    private DeepSearch deepSearch = new DeepSearch();
    private IterativeDeepeningSearch iterativeDeepeningSearch = new IterativeDeepeningSearch();
    private UniformCost uniformCost = new UniformCost();
    Robot robot = new Robot();


    @CrossOrigin
    @RequestMapping(value = "/busca", method = RequestMethod.POST)
    public ResponseEntity<String> search(@RequestBody FrontData data) throws IOException {

        Dados dados = new Dados();
        dados = dados.fromFrontData(data);
        System.out.println("Processando");
        System.out.println(dados.toString());

        switch (dados.getTipoAlg()) {
            case 0:
                Matrix matrix = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
                dto = deepSearch.deepSearch(matrix);
                System.out.println("Retorno deepSearch: " + new ResponseEntity<DataDTO>(dto, HttpStatus.OK));
                robot.Enviar(dto.getPontos().toString());
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 1:
                Matrix matrixInterativeDeep = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
                dto = iterativeDeepeningSearch.iterativeDeepeningSearch(matrixInterativeDeep);
                System.out.println("Retorno Iterative: " + new ResponseEntity<DataDTO>(dto, HttpStatus.OK));
                robot.Enviar(dto.getPontos().toString());
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 2:
                dto = bidirecional.Buscar(dados);
                System.out.println("Retorno Bidirecional: " + new ResponseEntity<DataDTO>(dto, HttpStatus.OK));
                robot.Enviar(dto.getPontos().toString());
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 3:
                dto = uniformCost.Buscar(dados);
                System.out.println("Retorno UniformCost: " + new ResponseEntity<DataDTO>(dto, HttpStatus.OK));
                robot.Enviar(dto.getPontos().toString());
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 4:
                dto = aStar.AStar(dados);
                System.out.println("Retorno ASTAR: " + new ResponseEntity<DataDTO>(dto, HttpStatus.OK));
                robot.Enviar(dto.getPontos().toString());
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            default:
                System.out.println("Inexperado");
                return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

}