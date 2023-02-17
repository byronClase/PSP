package entregaSockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCPBasico {
    public static void main(String[] args) throws IOException {
        int puerto = 60000;

        ServerSocket servidor = new ServerSocket(puerto);

        System.out.println("Escuchando el servicio: " + servidor.getLocalPort());
        Socket cliente = servidor.accept();
        Socket cliente2 = servidor.accept();

        servidor.close();

    }

}
