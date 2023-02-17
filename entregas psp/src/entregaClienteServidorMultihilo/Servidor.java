package entregaClienteServidorMultihilo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(6000);
            Socket socket;
            System.out.println("Servidor iniciado: ");
            while(true){
                socket = servidor.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                out.writeUTF("Indica tu nombre: ");
                String nombreCliente = in.readUTF();
                ServidorHilo hilo = new ServidorHilo(socket, in, out, nombreCliente);
                hilo.start();

                System.out.println("Creada la conexion con el cliente "+nombreCliente);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
