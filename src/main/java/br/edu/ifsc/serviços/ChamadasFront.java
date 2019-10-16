package br.edu.ifsc.serviços;

import br.edu.ifsc.algorithm.*;
import br.edu.ifsc.dto.DataDTO;
import br.edu.ifsc.input.Dados;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
import com.google.gson.Gson;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/robo")
public class ChamadasFront {

    private DataDTO dto = new DataDTO();
    private AStar aStar = new AStar();
    private Bidirectional bidirecional = new Bidirectional();
    private DeepSearch deepSearch = new DeepSearch();
    private IterativeDeepeningSearch iterativeDeepeningSearch = new IterativeDeepeningSearch();
    private SearchWidth searchWidth = new SearchWidth();
    private UniformCost uniformCost = new UniformCost();


    @PostMapping("/busca")
    public ResponseEntity<String> search(@RequestBody Dados dados) {
        System.out.println("Entrouuu");
        System.out.println(dados.toString());

        switch (dados.getTipoAlg()){
            case 0:
                Matrix matrix = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
                dto = deepSearch.deepSearch(matrix);
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 1:
                Matrix matrixInterativeDeep = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
                dto = iterativeDeepeningSearch.iterativeDeepeningSearch(matrixInterativeDeep);
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 2:
                dto = bidirecional.Buscar(dados);
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 3:
                uniformCost.Buscar(dados);
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            case 4:
                dto = aStar.AStar(dados);
                System.out.println("Retorno ASTAR: " + new ResponseEntity<DataDTO>(dto,HttpStatus.OK));
                return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);

            default:
                System.out.println("Inexperado");
                break;
        }

        return null;
    }

}