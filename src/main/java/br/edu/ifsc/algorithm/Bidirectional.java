/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.algorithm;

import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Ponto;
import br.edu.ifsc.model.Nodo;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Aluno
 */
public class Bidirectional {

    Queue<Nodo> saida = new LinkedList<Nodo>();
    Queue<Nodo> chegada = new LinkedList<Nodo>();

    public String Buscar(Dados dados) {
        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new Nodo(i, j);
                }
            }
        }

        //adicionando os obstaculos ao nodo
        for (int i = 0; i < dados.getObstaculos().size(); i++) {
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setIsObstacle(Boolean.TRUE);
        }

        // seta o nó de saída
        nodo[dados.getPInicialX()][dados.getPInicialY()].setIsStart(Boolean.TRUE);
        // seta o nó de chegada
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j].isIsStart()) {
                    nodo[i][j].setVisited(Boolean.TRUE);
                    nodo[i][j].setCaminho3(i + ":" + j);
                    saida.add(nodo[i][j]);
                }
                if (nodo[i][j].isIsEnd()) {
                    nodo[i][j].setExplored(Boolean.TRUE);
                    nodo[i][j].setCaminho3(";" + i + ":" + j);
                    chegada.add(nodo[i][j]);
                }
            }
        }

        while (!saida.isEmpty() || !chegada.isEmpty()) {
            int i, j;
            if (!saida.isEmpty()) {
                Nodo nodoA = saida.remove();
                String caminho = nodoA.getCaminho3();
                i = nodoA.getI() + 1;
                j = nodoA.getJ();
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
                i--;
                j++;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
                i--;
                j--;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
                i++;
                j--;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
                i++;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
                j = j + 2;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
                i = i - 2;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
                j = j - 2;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isVisited()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isExplored()) {
                                    nodo[i][j].setVisited(true);
                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    //caminho.add(new Ponto(i, j));
                                    saida.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }
            }
            if (!chegada.isEmpty()) {
                Nodo nodoB = chegada.remove();
                //caminho = (ArrayList<Ponto>) nodoB.getCaminho();
                String caminho = nodoB.getCaminho3();
                i = nodoB.getI() + 1;
                j = nodoB.getJ();
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isExplored()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isVisited()) {
                                    nodo[i][j].setExplored(true);

                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    chegada.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }

                j++;
                i--;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isExplored()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isVisited()) {
                                    nodo[i][j].setExplored(true);

                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    chegada.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }

                i++;
                j--;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isExplored()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isVisited()) {
                                    nodo[i][j].setExplored(true);

                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    chegada.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }

                i++;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isExplored()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isVisited()) {
                                    nodo[i][j].setExplored(true);

                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    chegada.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }

                j = j + 2;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isExplored()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isVisited()) {
                                    nodo[i][j].setExplored(true);

                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    chegada.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }

                i = i - 2;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isExplored()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isVisited()) {
                                    nodo[i][j].setExplored(true);

                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    chegada.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }

                j = j - 2;
                if (!(i < 0 || j < 0 || i >= dados.getTamanhoX() || j >= dados.getTamanhoY())) {
                    if (nodo[i][j] != null) {
                        if (!nodo[i][j].isExplored()) {
                            if (!nodo[i][j].isIsObstacle()) {
                                if (!nodo[i][j].isVisited()) {
                                    nodo[i][j].setExplored(true);

                                    nodo[i][j].setCaminho3(caminho + ";" + i + ":" + j);
                                    chegada.add(nodo[i][j]);
                                } else {
                                    return caminho + nodo[i][j].getCaminho3();
                                }
                            }
                        }
                    }
                }

            }
        }
        return "não deu";
    }

    public ArrayList<Ponto> Converter(String pontos) {
        ArrayList<Ponto> caminho = new ArrayList<>();
        String[] ponto = pontos.split(";");
        for (String s : ponto) {
            String[] n = s.split(":");
            int x = Integer.parseInt(n[0]);
            int y = Integer.parseInt(n[1]);
            caminho.add(new Ponto(x, y));
        }
        return caminho;
    }
}
