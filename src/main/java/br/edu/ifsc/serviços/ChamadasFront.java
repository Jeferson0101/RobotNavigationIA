package br.edu.ifsc.serviços;

import br.edu.ifsc.algorithm.*;
import br.edu.ifsc.dto.DataDTO;
import br.edu.ifsc.input.Dados;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/robo")
public class ChamadasFront {

    private DataDTO dto;
    private AStar aStar;
    private Bidirectional bidirecional;
    private DeepSearch deepSearch;
    private IterativeDeepeningSearch iterativeDeepeningSearch;
    private SearchWidth searchWidth;
    private UniformCost uniformCost;


    @CrossOrigin
    @RequestMapping(value = "/busca", method = RequestMethod.POST)
    public ResponseEntity<DataDTO> search(@RequestBody Dados dados){
        System.out.println("Entrouuu");
        System.out.println(dados.toString());
        if (dados.getTipoAlg() == 4) {
            dto = aStar.AStar(dados);
            return new ResponseEntity<DataDTO>(dto,HttpStatus.OK);
        }
        return null;
    }

}