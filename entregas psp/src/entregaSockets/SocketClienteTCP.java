package entregaSockets;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClienteTCP {

    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;

    public SocketClienteTCP(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public void start() throws IOException {
        System.out.println("(CLIENTE) Estableciendo CONEXION...");
        socket = new Socket(serverIP, serverPort);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(CLIENTE) Conexion establecida.");
    }

    public void stop() throws IOException {
        System.out.println("(CLIENTE) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(CLIENTE) Conexion cerrada .");
    }

    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 1026;
        try {
            SocketClienteTCP servidor = new SocketClienteTCP(serverIP, serverPort);
            servidor.start();
            servidor.os.write(20);
            System.out.println("Mensaje recibido del servidor: " + servidor.is.read());
            servidor.stop();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}