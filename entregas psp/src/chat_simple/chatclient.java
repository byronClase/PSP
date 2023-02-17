package chat_simple;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class chatclient {

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip, 6666);

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        while (true) {
            System.out.println(in.readUTF());
            String toSend = sc.next();
            out.writeUTF(toSend);
            if(toSend.equalsIgnoreCase("Exit")){
                System.out.println("Cerrando conexion con "+ socket);
                System.out.println("Conexion cerrada");
                break;
            }
            String received = in.readUTF();
            System.out.println(received);
        }
        sc.close();
        in.close();
        out.close();
    }
}
