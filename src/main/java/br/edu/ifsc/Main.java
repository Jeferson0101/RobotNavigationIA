package br.edu.ifsc;

import br.edu.ifsc.input.Dados;
import br.edu.ifsc.input.Front;
import br.edu.ifsc.input.Front_;
import br.edu.ifsc.input.Obstaculo;
import br.edu.ifsc.model.Algorithm;
import br.edu.ifsc.model.Matrix;
import br.edu.ifsc.model.NodeManager;
import br.edu.ifsc.serviços.ChamadasFront;
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
    
    public final static String FILE_TO_RECEIVED = basePath+String.format("%ssrc%smain%sjava%sbr%sedu%sifsc%sjsons%smodeloIntegracao.json",s,s,s,s,s,s,s,s);

    public static void main(String[] args) throws IOException {
        
        //--------Teste de um Json-----------
        Dados dados = new Dados();
        dados.setTamanhoX(50);
        dados.setTamanhoY(50);
        dados.setPInicialX(1);
        dados.setPInicialY(1);
        dados.setPFinalX(30);
        dados.setPFinalY(30);
        
        Obstaculo obstaculos = new Obstaculo();
        List<Obstaculo> listaObstaculo = new ArrayList<>(); 
        obstaculos.setX(5);
        obstaculos.setY(5);
        obstaculos.setLarg(10);
        obstaculos.setAltura(5);
        
        listaObstaculo.add(obstaculos);
        
        dados.setObstaculos(listaObstaculo);
        
        ChamadasFront chamada = new ChamadasFront();
        chamada.buscaUniforme(dados);
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
