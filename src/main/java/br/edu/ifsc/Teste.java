/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc;

import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Front;
import br.edu.ifsc.input.Front_;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author felip
 */
public class Teste {
    public static void main(String[] args) {
         Front front = null;
        Front_ front_ = null;
        try {
            String basePath = new File("").getAbsolutePath();
            String s = File.separator;
            front = Front.convertJsonInputToFrontObject(basePath + String.format("%ssrc%smain%sjava%sbr%sedu%sifsc%sjsons%sinput3.json",s,s,s,s,s,s,s,s));
            front_ = front.getFront();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dados dados = front_.getDados();
//        Algorithm algorithm = new Algorithm();
//        Matrix matrix = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
//        algorithm.execute(matrix, dados);
    }
}
