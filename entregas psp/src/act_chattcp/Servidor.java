package act_chattcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{


    public static void main(String[] args) {
        ServerSocket servidor = null;
        int puerto = 6000;
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Esperando..."); //Esperando conexión
            //servidor.accept();

            while(true){
                Socket cliente = new Socket();
                servidor.accept();
                Hilo h1 = new Hilo(cliente);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
