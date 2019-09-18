/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.view;

import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Obstaculo;
import br.edu.ifsc.input.Ponto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author cesar
 */
public class Resultado extends JFrame {

    public void Resultado(Dados dados, ArrayList<Ponto> pontos) {
        int i = dados.getTamanhoX();
        int j = dados.getTamanhoY();

        javax.swing.JButton botao;
        JPanel pGrid = new JPanel(new GridLayout(i, j));

        for (i = 0; i < dados.getTamanhoX(); i++) {
            for (j = 0; j < dados.getTamanhoY(); j++) {
                botao = new JButton();

                for (Ponto ponto : pontos) {
                    if (ponto.getX() == i && ponto.getY() == j) {
                        botao.setBackground(Color.BLACK);
                    }
                }
                for (int n = 0; n < dados.getObstaculos().size(); n++) {
                    if (dados.getObstaculos().get(n).getX()== i&&dados.getObstaculos().get(n).getY()== j) {
                        botao.setBackground(Color.RED);
                    }
                }
                if (dados.getPInicialX() == i && dados.getPInicialY() == j) {
                    botao.setBackground(Color.GREEN);
                }
                if (dados.getPFinalX() == i && dados.getPFinalY() == j) {
                    botao.setBackground(Color.BLUE);
                }

                pGrid.add(botao);
            }
        }

        this.setLayout(new BorderLayout());
        this.getContentPane().add(pGrid, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setTitle("Resultado");
        this.setSize(1080, 800);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
