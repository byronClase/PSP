package entregaSockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServidorTCP {
    private ServerSocket serverSocket;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public SocketServidorTCP(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
    }

    public void start() throws IOException {
        System.out.println("(SERVIDOR) Esperando conexiones...");
        socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(SERVIDOR) Conexion establecida.");
    }

    public void stop() throws IOException {
        System.out.println("(SERVIDOR) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        serverSocket.close();
        System.out.println("(SERVIDOR) Conexion cerrada .");
    }

    public static void main(String[] args) {
        try {
            SocketServidorTCP servidor = new SocketServidorTCP(1026);
            servidor.start();
            System.out.println("Mensaje recibido del cliente: " + servidor.is.read());
            servidor.os.write(90);
            servidor.stop();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
