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

/**
 *
 * @author Aluno
 */
public class AStar {
    public ArrayList<Ponto> AStar(Dados dados) {
        Nodo[][] nodo = new Nodo[dados.getTamanhoX()][dados.getTamanhoY()];
        ArrayList<Ponto> caminho = new ArrayList<>();
        ArrayList<Nodo> lista = new ArrayList<>();
        Nodo nodoAtual;

        for (int i = 0; i < dados.getTamanhoX(); i++) {
            for (int j = 0; j < dados.getTamanhoY(); j++) {
                if (nodo[i][j] == null) {
                    nodo[i][j] = new Nodo(i,j);
                }
            }
        }
        //adicionando os obstaculos ao nodo
        for (int i = 0; i < dados.getObstaculos().size(); i++) {
            nodo[dados.getObstaculos().get(i).getX()][dados.getObstaculos().get(i).getY()].setIsObstacle(Boolean.TRUE);
        }
        // seta o nó de saída
        nodo[dados.getPInicialX()][dados.getPInicialY()].setIsStart(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setExplored(Boolean.TRUE);
        nodo[dados.getPInicialX()][dados.getPInicialY()].setCaminho(new Ponto(dados.getPFinalX(),dados.getPFinalY()));
        // seta o nó de chegada
        nodo[dados.getPFinalX()][dados.getPFinalY()].setIsEnd(Boolean.TRUE);



        //adicionando a lista
        lista.add(nodo[dados.getPInicialX()][dados.getPInicialY()]);

        while (!lista.isEmpty()) {
            nodoAtual = lista.get(0);
            int teste = 0;
            int remove = 0;
            for (Nodo no : lista) {
                teste++;
                if ((no.getPeso() + previsaoPeso(no, dados)) < (nodoAtual.getPeso() + previsaoPeso(nodoAtual,dados))) {
                    nodoAtual = no;
                    remove = teste - 1;
                }
            }
            nodoAtual.setExplored(Boolean.TRUE);
            lista.remove(remove);

            caminho.add(nodoAtual.getCaminho().get(0));
            int i = nodoAtual.getI() + 1;
            int j = nodoAtual.getJ();
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            j++;
            i--;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i--;
            j--;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i++;
            j--;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i++;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            j = j + 2;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            i = i - 2;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
            j = j - 2;
            if (nodo[i][j] != null) {
                if (!nodo[i][j].isVisited()) {
                    if (!nodo[i][j].isIsObstacle()) {
                        if (!nodo[i][j].isIsEnd()) {
                            nodo[i][j].setVisited(Boolean.TRUE);
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            nodo[i][j].setCaminho(new Ponto(i, j));
                            lista.add(nodo[i][j]);
                        } else {
                            nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                            System.out.println(nodo[i][j].getPeso());
                            caminho.add(new Ponto(i, j));
                            return caminho;
                        }
                    }
                } else {
                    if ((nodo[i][j].getPeso()) > (nodoAtual.getPeso() + 1)) {
                        nodo[i][j].setPeso(nodoAtual.getPeso() + 1);
                        nodo[i][j].setCaminho(new Ponto(i, j));
                    }

                }
            }
        }
        return caminho;
    }
    
    private double previsaoPeso(Nodo nodo,Dados dados) {
        int x = dados.getPFinalX();
        int y = dados.getPFinalY();


        double a = Math.abs(nodo.getI() - x);
        double b = Math.abs(nodo.getJ() - y);
        if (a > b) {
            return ((b * 1.4) + (a - b));
        } else {
            return ((a * 1.4) + (b - a));
        }

    }
}
