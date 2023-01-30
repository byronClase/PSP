package act3_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ServerSocketBN {
    private ServerSocketBN serverSocket;
    private Socket socket;

    private InputStream is;
    private OutputStream os;

    public ServerSocketBN() {
        serverSocket = new ServerSocketBN();
    }

    public ServerSocketBN(int port, int maximo) {
        serverSocket = new ServerSocketBN(port, maximo);
    }

    public ServerSocketBN(int port, int maximo, InetAddress dir) {
        serverSocket = new ServerSocketBN(port, maximo, dir);
    }

    public Socket accept() throws IOException {
        System.out.println("(SERVIDOR) Esperando conexiones...");
        socket = serverSocket.accept();
        System.out.println("(SERVIDOR) Conexion establecida.");
        return socket;
    }

    public void stop() throws IOException {
        socket.close();
    }

    public int getLocalPort() {
        return socket.getLocalPort();
    }
}
