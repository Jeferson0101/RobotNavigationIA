/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsc.model;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
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
    private DataOutputStream mDataOutputStream;
    public final static int SOCKET_PORT = 23;
    public final static String SERVER = "192.168.0.112";
    public final static String FILE_TO_SEND = "C:\\Users\\Jeferson\\Documents\\RobotNavigationIA\\src\\main\\java\\br\\edu\\ifsc\\jsons\\resultado.json";

    public void Enviar(String msg) throws IOException {
        try {
            
            System.out.println("Connecting to "+ SERVER +" at port "+ SOCKET_PORT + "...");
            sock = new Socket(SERVER, SOCKET_PORT);
            System.out.println("Mensagem: " + msg);
            mDataOutputStream = new DataOutputStream(this.sock.getOutputStream());
            mDataOutputStream.writeUTF(msg);
            //mDataOutputStream.wait(1000);
            // send msg
            //String mensagem="jogada 1";
            //PrintWriter out = new PrintWriter(sock.getOutputStream(),true);
            
            //out.println(msg);
            //out.close();
            //sock.close();

        } catch (Exception ex) {
            System.out.println("Conexão fechada inesperada");
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
