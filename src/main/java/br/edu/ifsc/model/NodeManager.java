package br.edu.ifsc.model;

import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Obstaculo;

import java.util.ArrayList;
import java.util.List;


public class NodeManager {

    public static final String PATTERNID = "(%d,%d)";
    public static List<Obstaculo> obstaculos;
    public static int totalNodes = 0;
    private Node[][] initNode;

    public NodeManager(List<Obstaculo> obstaculos) {
        this.obstaculos = obstaculos;
    }

    public static void setAllMovesFalse(Node[][] nodes) {
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                nodes[i][j].setRight(false);
                nodes[i][j].setLeft(false);
                nodes[i][j].setUp(false);
                nodes[i][j].setDown(false);
            }
        }
    }

    public static Node[][] generateNodes(Dados dados) {

        List<Obstaculo> obstaculos = dados.getObstaculos();
        int initialPositionRoboX = dados.getPInicialX();
        int initialPositionRoboY = dados.getPInicialY();

        Node[][] nodes = new Node[dados.getTamanhoX()][dados.getTamanhoY()];

        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[x].length; y++) {
                nodes[x][y] = new Node();
            }
        }

        for (Obstaculo obstaculo : obstaculos) {
            for (int i = 0; i < obstaculo.getAltura(); i++) {
                try {
                    nodes[obstaculo.getX()][obstaculo.getY() - i].setObstacle(true);
                } catch (Exception ex) {
                }
            }
            for (int i = 0; i < obstaculo.getLarg(); i++) {
                try {
                    nodes[obstaculo.getX() + i][obstaculo.getY()].setObstacle(true);
                } catch (Exception ex) {
                }
            }
        }

        nodes[initialPositionRoboX][initialPositionRoboY].setRobo(true);

        return nodes;
    }

    public static Node[][] defineMove(Node[][] nodes) {
        setAllMovesFalse(nodes);
        Algorithm.move = 0;
        for (int x = 0; x < nodes.length; x++) {
            for (int y = 0; y < nodes[x].length; y++) {
                try {
                    if (!nodes[x][y].isObstacle() && nodes[x][y - 1].isRobo()) {
                        nodes[x][y].setUp(true);
                        Algorithm.move++;
                    }
                } catch (Exception e) {
                    nodes[x][y].setUp(false);
                }
                try {
                    if (!nodes[x][y].isObstacle() && nodes[x][y + 1].isRobo()) {
                        nodes[x][y].setDown(true);
                        Algorithm.move++;
                    }
                } catch (Exception e) {
                    nodes[x][y].setDown(false);
                }
                try {
                    if (!nodes[x][y].isObstacle() && nodes[x + 1][y].isRobo()) {
                        nodes[x][y].setRight(true);
                        Algorithm.move++;
                    }
                } catch (Exception e) {
                    nodes[x][y].setRight(false);
                }
                try {
                    if (!nodes[x][y].isObstacle() && nodes[x - 1][y].isRobo()) {
                        nodes[x][y].setLeft(true);
                        Algorithm.move++;
                    }
                } catch (Exception e) {
                    nodes[x][y].setLeft(false);
                }
            }
        }
        return nodes;
    }

    public static List<Matrix> generateNewMatrix(Matrix init) {
        defineMove(init.getNodes());

        List<Matrix> list = new ArrayList<>(Algorithm.move);
        try {
            for (int moves = 0; moves < Algorithm.move; moves++) {
                Node[][] newNode = new Node[Matrix.tamanhoX][Matrix.tamanhoY];
                for (int x = 0; x < init.getNodes().length; x++) {
                    for (int y = 0; y < init.getNodes()[x].length; y++) {
                        newNode[x][y] = init.getNodes()[x][y].clone();
                    }
                }
                Matrix newMatrix = new Matrix(newNode);
                newMatrix.setRoboActualPositionX(init.getRoboActualPositionX());
                newMatrix.setRoboActualPositionY(init.getRoboActualPositionY());
                newMatrix.level = init.level;
                newMatrix.getMoves().append(new String(init.getMoves().toString().getBytes()));
                list.add(newMatrix);
            }
            int index = 0;
            for (int x = 0; x < list.get(index).getNodes().length; x++) {
                for (int y = 0; y < list.get(index).getNodes()[x].length; y++) {
                    if (list.get(index).getNodes()[x][y].isLeft()) {
                        Node nineClone = list.get(index).getNodes()[x - 1][y].clone();
                        list.get(index).getNodes()[x - 1][y] = list.get(index).getNodes()[x][y].clone();
                        list.get(index).getNodes()[x][y] = nineClone;
                        list.get(index).getMoves().append(String.format(NodeManager.PATTERNID, x, y));
                        list.get(index).setRoboActualPositionX(x);
                        index++;
                    } else if (list.get(index).getNodes()[x][y].isRight()) {
                        Node nineClone = list.get(index).getNodes()[x + 1][y].clone();
                        list.get(index).getNodes()[x + 1][y] = list.get(index).getNodes()[x][y].clone();
                        list.get(index).getNodes()[x][y] = nineClone;
                        list.get(index).getMoves().append(String.format(NodeManager.PATTERNID, x, y));
                        list.get(index).setRoboActualPositionX(x);
                        index++;
                    } else if (list.get(index).getNodes()[x][y].isUp()) {
                        Node nineClone = list.get(index).getNodes()[x][y - 1].clone();
                        list.get(index).getNodes()[x][y - 1] = list.get(index).getNodes()[x][y].clone();
                        list.get(index).getNodes()[x][y] = nineClone;
                        list.get(index).getMoves().append(String.format(NodeManager.PATTERNID, x, y));
                        list.get(index).setRoboActualPositionY(y);
                        index++;
                    } else if (list.get(index).getNodes()[x][y].isDown()) {
                        Node nineClone = list.get(index).getNodes()[x][y + 1].clone();
                        list.get(index).getNodes()[x][y + 1] = list.get(index).getNodes()[x][y].clone();
                        list.get(index).getNodes()[x][y] = nineClone;
                        list.get(index).getMoves().append(String.format(NodeManager.PATTERNID, x, y));
                        list.get(index).setRoboActualPositionY(y);
                        index++;
                    }
                }
            }

        } catch (Exception e) {
        }

        return list;
    }

    public Matrix makeInitialSetupByFront(Dados dados) {
        Matrix initialMatrix = new Matrix();
        Matrix.tamanhoX = dados.getTamanhoX();
        Matrix.tamanhoY = dados.getTamanhoY();
        Matrix.roboFinalPositionX = dados.getPFinalX();
        Matrix.roboFinalPositionY = dados.getPFinalY();
        initNode = generateNodes(dados);
        initialMatrix.setNodes(initNode);
        initialMatrix.setRoboActualPositionX(dados.getPInicialX());
        initialMatrix.setRoboActualPositionY(dados.getPInicialY());
        initialMatrix.getMoves().append(String.format(NodeManager.PATTERNID, dados.getPInicialX(), dados.getPInicialY()));
        return initialMatrix;
    }
}
