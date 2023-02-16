package ChatVentanas;

import act_chattcp.Hilo;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoServidor mimarco = new MarcoServidor();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

class MarcoServidor extends JFrame implements Runnable {

    public MarcoServidor() {

        setBounds(1200, 300, 280, 350);

        JPanel milamina = new JPanel();

        milamina.setLayout(new BorderLayout());

        areatexto = new JTextArea();

        milamina.add(areatexto, BorderLayout.CENTER);

        add(milamina);

        setVisible(true);
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    private JTextArea areatexto;

    @Override
    public void run() {
        //System.out.println("Estoy a la escucha");
        try {
            ServerSocket servidor = new ServerSocket(9999);
            String nick, ip, mensaje;

            PaqueteEnvio paqueteRecibido;

            while (true) {
                Socket misocket = servidor.accept();
                ObjectInputStream paqueteEntrada = new ObjectInputStream(misocket.getInputStream());

                paqueteRecibido = (PaqueteEnvio) paqueteEntrada.readObject();
                nick = paqueteRecibido.getNick();
                ip = paqueteRecibido.getIp();
                mensaje = paqueteRecibido.getMensaje();

                areatexto.append("\n" + nick + ": " + mensaje + " para " + ip);
                Socket enviaDestinatario = new Socket(ip,9090);
                ObjectOutputStream paqueteReenvio = new ObjectOutputStream(enviaDestinatario.getOutputStream());
                paqueteReenvio.writeObject(paqueteRecibido);
                enviaDestinatario.close();
                misocket.close();

                /*DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
                String mensajeTexto = flujo_entrada.readUTF();
                areatexto.append("\n" + mensajeTexto);*/
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
