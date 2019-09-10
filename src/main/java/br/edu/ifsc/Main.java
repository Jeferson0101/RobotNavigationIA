package br.edu.ifsc;

import br.edu.ifsc.algorithm.AStar;
import br.edu.ifsc.algorithm.Bidirectional;
import br.edu.ifsc.algorithm.UniformCost;
import br.edu.ifsc.input.*;
import br.edu.ifsc.model.Algorithm;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
//import br.edu.ifsc.navegacaoderobos.Leitor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class Main {

    public final static int SOCKET_PORT = 5501;
    public final static int FILE_SIZE = 4096;
    public final static String basePath = new File("").getAbsolutePath();
    public final static String s = File.separator;

    public final static String FILE_TO_RECEIVED = basePath + String.format("%ssrc%smain%sjava%sbr%sedu%sifsc%sjsons%smodeloIntegracao.json", s, s, s, s, s, s, s, s);

    public static void main(String[] args) throws IOException {

        //--------Teste de um Json-----------
        Dados dados = new Dados();
        dados.setTamanhoX(10);
        dados.setTamanhoY(10);
        dados.setPInicialX(1);
        dados.setPInicialY(1);
        dados.setPFinalX(8);
        dados.setPFinalY(2);

        Obstaculo obstaculos = new Obstaculo();
        List<Obstaculo> listaObstaculo = new ArrayList<>();
        obstaculos.setX(2);
        obstaculos.setY(2);
        obstaculos.setLarg(2);
        obstaculos.setAltura(2);

        listaObstaculo.add(obstaculos);

        dados.setObstaculos(listaObstaculo);
        //System.out.println("obstaculos: " + dados.getObstaculos().get(0).getX());
        //ChamadasFront chamada = new ChamadasFront();
        //chamada.buscaUniforme(dados);
        ArrayList<Ponto> pontos = new ArrayList<>();
        AStar aStar = new AStar();
        Bidirectional bi = new Bidirectional();
        UniformCost custoUniforme = new UniformCost();
        pontos = (ArrayList<Ponto>) custoUniforme.Buscar(dados);
        //pontos = aStar.AStar(dados);
        //pontos = bi.bidirectional(dados);
        System.out.println(pontos.toString());
        //----------------------------------------
        br.edu.ifsc.model.Leitor leitor = new br.edu.ifsc.model.Leitor();

        BufferedInputStream bis = null;
        OutputStream os = null;
        ServerSocket servsock = null;
        Socket sock = null;
        int bytesRead;
        int current = 0;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            servsock = new ServerSocket(SOCKET_PORT);
            while (true) {
                System.out.println("Waiting...");
                try {
                    sock = servsock.accept();
                    System.out.println("Accepted connection : " + sock);

                    // receive file
                    byte[] mybytearray = new byte[FILE_SIZE];
                    InputStream is = sock.getInputStream();
                    fos = new FileOutputStream(FILE_TO_RECEIVED);
                    bos = new BufferedOutputStream(fos);
                    bytesRead = is.read(mybytearray, 0, mybytearray.length);
                    current = bytesRead;

                    do {
                        bytesRead
                                = is.read(mybytearray, current, (mybytearray.length - current));
                        if (bytesRead >= 0) {
                            current += bytesRead;
                        }
                    } while (bytesRead > -1);

                    bos.write(mybytearray, 0, current);
                    bos.flush();
                    System.out.println("File " + FILE_TO_RECEIVED
                            + " downloaded (" + current + " bytes read)");
                    System.out.println("Processando...");
                    leitor.Iniciar();

                    System.out.println("Done.");
                } catch (IOException ex) {
                    System.out.println(ex.getMessage() + ": An Inbound Connection Was Not Resolved");

                } finally {
                    if (bis != null) {
                        bis.close();
                    }
                    if (os != null) {
                        os.close();
                    }
                    if (sock != null) {
                        sock.close();
                    }
                }
            }
        } finally {
            if (servsock != null) {
                servsock.close();
            }
        }
    }

}
