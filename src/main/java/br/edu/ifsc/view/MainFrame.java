/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.view;

import br.edu.ifsc.algorithm.AStar;
import br.edu.ifsc.algorithm.Bidirectional;
import br.edu.ifsc.algorithm.DeepSearch;
import br.edu.ifsc.algorithm.IterativeDeepeningSearch;
import br.edu.ifsc.algorithm.SearchWidth;
import br.edu.ifsc.algorithm.UniformCost;
import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Obstaculo;
import br.edu.ifsc.input.Ponto;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
import br.edu.ifsc.model.Robot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        jBBuscar.setEnabled(false);

    }
    public Robot robo = new Robot();

    Gson gson = new Gson();
                
            
    Dados dados = new Dados();
    Obstaculo obstaculos;
    List<Obstaculo> listaObstaculo = new ArrayList<>();
    ArrayList<Ponto> pontos = new ArrayList<>();
    Resultado r;
    Matrix matrix;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCSelecao = new javax.swing.JComboBox<>();
        jBBuscar = new javax.swing.JButton();
        jBCriar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTxtTamanhoX = new javax.swing.JTextField();
        jTxtInicioX = new javax.swing.JTextField();
        jTxtFinalX = new javax.swing.JTextField();
        jTxtInicioY = new javax.swing.JTextField();
        jTxtFinalY = new javax.swing.JTextField();
        jTxtTamanhoY = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTxtObsX = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTxtObsY = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jBAdicionar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Plano B");
        setName(""); // NOI18N

        jCSelecao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Busca em Profundidade", "Profundidade Interativa", "Bidirecional", "Custo Uniforme", "A Estrela", "Largura" }));
        jCSelecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSelecaoActionPerformed(evt);
            }
        });

        jBBuscar.setText("Buscar");
        jBBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBuscarActionPerformed(evt);
            }
        });

        jBCriar.setText("Criar");
        jBCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCriarActionPerformed(evt);
            }
        });

        jLabel1.setText("Coordenada Inicial  X:");

        jLabel3.setText("Coordenada Final   X:");

        jLabel4.setText("Y:");

        jLabel5.setText("Y:");

        jLabel2.setText("Tamanho da Matriz  X:");

        jLabel6.setText("Y:");

        jTxtFinalY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtFinalYActionPerformed(evt);
            }
        });

        jLabel7.setText("Obstaculo:");

        jLabel8.setText("X:");

        jLabel9.setText("Y:");

        jBAdicionar.setText("Adicionar");
        jBAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtObsX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtObsY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jBAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jTxtObsX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtObsY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBAdicionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBCriar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTxtTamanhoX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTxtTamanhoY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTxtFinalX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTxtFinalY))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(8, 8, 8)
                                    .addComponent(jTxtInicioX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTxtInicioY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBBuscar)))
                        .addGap(0, 10, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(jTxtTamanhoX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtTamanhoY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jTxtInicioX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtInicioY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jTxtFinalX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFinalY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jBCriar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCSelecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBBuscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCSelecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSelecaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCSelecaoActionPerformed

    private void jBCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCriarActionPerformed
        dados.setTamanhoX(Integer.parseInt(jTxtTamanhoX.getText()));
        dados.setTamanhoY(Integer.parseInt(jTxtTamanhoY.getText()));
        dados.setPInicialX(Integer.parseInt(jTxtInicioX.getText()));
        dados.setPInicialY(Integer.parseInt(jTxtInicioY.getText()));
        dados.setPFinalX(Integer.parseInt(jTxtFinalX.getText()));
        dados.setPFinalY(Integer.parseInt(jTxtFinalY.getText()));
        JOptionPane.showMessageDialog(rootPane, "Criado com sucesso");
        jBBuscar.setEnabled(true);
    }//GEN-LAST:event_jBCriarActionPerformed

    private void jBBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBuscarActionPerformed
        dados.setObstaculos(listaObstaculo);
        if (jCSelecao.getSelectedIndex() == 0) {
            r = new Resultado();
            DeepSearch dSearch = new DeepSearch();
            matrix = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
            pontos = dSearch.deepSearch(matrix);
            
            r.Resultado(dados, pontos);
            
            
        } else if (jCSelecao.getSelectedIndex() == 1) {
            r = new Resultado();
            IterativeDeepeningSearch iDSearch = new IterativeDeepeningSearch();
            matrix = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
            pontos = iDSearch.iterativeDeepeningSearch(matrix);
            r.Resultado(dados, pontos);
        } else if (jCSelecao.getSelectedIndex() == 2) {
            r = new Resultado();
            Bidirectional bidirecional = new Bidirectional();
            pontos = bidirecional.Converter(bidirecional.Buscar(dados));
            r.Resultado(dados, pontos);
        } else if (jCSelecao.getSelectedIndex() == 3) {
            r = new Resultado();
            UniformCost custoUniforme = new UniformCost();
            pontos = (ArrayList<Ponto>) custoUniforme.Buscar(dados);
            r.Resultado(dados, pontos);
        } else if (jCSelecao.getSelectedIndex() == 4) {
            r = new Resultado();
            AStar aStar = new AStar();
            pontos = aStar.AStar(dados);
            r.Resultado(dados, pontos);
        } else if (jCSelecao.getSelectedIndex() == 5) {
            r = new Resultado();
            SearchWidth sWidth = new SearchWidth();
            matrix = new NodeManager(dados.getObstaculos()).makeInitialSetupByFront(dados);
            pontos = sWidth.searchWidth(matrix);
            r.Resultado(dados, pontos);
        }

        try {
            robo.Enviar(gson.toJson(pontos));
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBBuscarActionPerformed

    private void jBAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAdicionarActionPerformed
        obstaculos = new Obstaculo();
        obstaculos.setX(Integer.parseInt(jTxtObsX.getText()));
        obstaculos.setY(Integer.parseInt(jTxtObsY.getText()));
        obstaculos.setLarg(1);
        obstaculos.setAltura(1);
        listaObstaculo.add(obstaculos);
        JOptionPane.showMessageDialog(rootPane, "Obstaculo adicionado com sucesso");
    }//GEN-LAST:event_jBAdicionarActionPerformed

    private void jTxtFinalYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtFinalYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtFinalYActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBAdicionar;
    private javax.swing.JButton jBBuscar;
    private javax.swing.JButton jBCriar;
    private javax.swing.JComboBox<String> jCSelecao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTxtFinalX;
    private javax.swing.JTextField jTxtFinalY;
    private javax.swing.JTextField jTxtInicioX;
    private javax.swing.JTextField jTxtInicioY;
    private javax.swing.JTextField jTxtObsX;
    private javax.swing.JTextField jTxtObsY;
    private javax.swing.JTextField jTxtTamanhoX;
    private javax.swing.JTextField jTxtTamanhoY;
    // End of variables declaration//GEN-END:variables
}
