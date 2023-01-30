package act3_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketBN {
    private SocketBN socketbn;
    private InputStream is;
    private OutputStream os;
    public SocketBN(){
        socketbn = new SocketBN();
    }
    public SocketBN(InetAddress address, int port) throws IOException {
        socketbn = new SocketBN(address, port);
    }
    public SocketBN(InetAddress address, int port, InetAddress localAddr, int localPort) throws IOException {
        socketbn = new SocketBN(address, port, localAddr, localPort);
    }
    public SocketBN(String host, int port) throws IOException {
        socketbn = new SocketBN(host, port);
    }
    public InputStream getInputStream(){
        System.out.println("(CLIENTE) Estableciendo CONEXION...");
        socketbn = new SocketBN();
        is = socketbn.getInputStream();
        System.out.println("(CLIENTE) Conexion establecida.");
        return is;
    }

}
