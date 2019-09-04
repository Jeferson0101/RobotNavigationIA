/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.serviços;

import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Ponto;
import br.edu.ifsc.navegacaoderobos.BuscaUniforme;
import br.edu.ifsc.navegacaoderobos.Nodo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jeferson
 */
public class ChamadasFront {
    Nodo[][] nodo;
    
    public void buscaUniforme(Dados dados){    
        //Criando instância da matriz de nodo com o tamanho definido no Json
        
        nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];
        
        
        List<Ponto> caminho = new ArrayList<>();
        Ponto ponto;
        //Faz uma varredura nos obstáculos e coloca-os na matriz de nodo.
        for(int i=0; i< dados.getObstaculos().size();i++){
            //Ajustar isso. Pois aqui só estou referenciando o xy inicial, tem que calcular 
            //a altura e largura e fazer um for setando true do perímetro do obstáculo 
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()] = new Nodo("a");
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setEstado(Boolean.TRUE);
        }
        
        // seta o nó de saída 
        nodo[dados.getPInicialX()][dados.getPInicialY()] = new Nodo("");
        nodo[dados.getPInicialX()][dados.getPInicialY()].setSaida(Boolean.TRUE);
        
        // seta o nó de chegada 
        nodo[dados.getPFinalX()][dados.getPFinalY()] = new Nodo("");
        nodo[dados.getPFinalX()][dados.getPFinalY()].setChegada(Boolean.TRUE);
        
       
        BuscaUniforme busca = new BuscaUniforme();
        String resultado = busca.buscar(nodo);
        //separa os pontos x e y
        String[] res = resultado.split(";");
        
        
        //adiciona os pontos do caminho gerado em um AraayList 
        for(int i=0; i< res.length; i++ ){
            if( i % 2 == 0){
                ponto = new Ponto();
                ponto.setX(i);
                ponto.setY(i+1);
                caminho.add(ponto);
            }
        }
        
        System.out.println("Caminho..");
        for(int i=0; i < caminho.size(); i++){
            System.out.print("X" + caminho.get(i).getX());
            System.out.println("Y" + caminho.get(i).getY());
        }
    }
}
