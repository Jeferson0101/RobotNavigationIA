/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author cesar
 */
public class Robot {

    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    Socket sock = null;
    public final static int SOCKET_PORT = 6666;
    public final static String SERVER = "127.0.0.1";
    public final static String FILE_TO_SEND = "C:\\Users\\cesar\\Documents\\Projetos\\resultado.json";

    public void Enviar(String msg) throws IOException {
        try {
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Connecting...");

            // send msg
            //String mensagem="jogada 1";
            PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
            out.println(msg);
            out.close();
            sock.close();

        } catch (Exception ex) {
            System.out.println("Conexão fechada");
        } finally {
            if (bis != null) {
                //bis.close();
            }
            if (os != null) {
                //os.close();
            }
            if (sock != null) {
                sock.close();
            }
        }
    }
}
