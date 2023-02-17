package chat_simple;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread {

    DateFormat date = new SimpleDateFormat("yy/MM/dd");
    DateFormat time = new SimpleDateFormat("hh:mm:ss");
    DataInputStream in;
    DataOutputStream out;
    Socket socket;
    public ClientHandler(Socket socket, DataInputStream in, DataOutputStream out) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {
        String receveid;
        String toReturn;
        while(true){
            try {
                out.writeUTF("¿Fecha u hora?[pon exit para salir]");
                receveid = in.readUTF();
                if(receveid.equalsIgnoreCase("exit")){
                    System.out.println("Client "+this.socket+" exit!");
                    System.out.println("Cerrando conexion!");
                    this.socket.close();
                    System.out.println("Conexion cerrada");
                    break;
                }
                Date dateF = new Date();
                switch (receveid){
                    case "Date":
                        toReturn = date.format(dateF);
                        out.writeUTF(toReturn);break;
                    case "Time":
                        toReturn = time.format(dateF);
                        out.writeUTF(toReturn);
                        break;
                    default:
                        out.writeUTF("Dato no posible");
                        break;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            this.in.close();
            this.out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
