package chat_simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class chatserver {
    public static void main(String args[]) throws Exception {
        ServerSocket ss = new ServerSocket(6666);

        while (true) {
            Socket socket = null;
            try{
                socket = ss.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Nuevo hilo para un cliente: ");
                Thread nuevoHilo = new ClientHandler(socket, in, out);
                nuevoHilo.start();
            }catch (Exception e ){
                System.err.println("ERROR > "+e);
            }

        }


    }
}

