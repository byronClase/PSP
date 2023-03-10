package ChatVentanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.cert.PolicyQualifierInfo;


public class Cliente {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoCliente mimarco = new MarcoCliente();

        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}


class MarcoCliente extends JFrame {

    public MarcoCliente() {

        setBounds(600, 300, 280, 350);

        LaminaMarcoCliente milamina = new LaminaMarcoCliente();

        add(milamina);

        setVisible(true);
    }

}

class LaminaMarcoCliente extends JPanel implements Runnable{

    public LaminaMarcoCliente() {
        nick = new JTextField(5);
        add(nick);

        JLabel texto = new JLabel("-CHAT-");

        add(texto);
        ip = new JTextField(8);
        add(ip);
        campoChat = new JTextArea(12, 20);
        add(campoChat);
        campo1 = new JTextField(20);

        add(campo1);

        miboton = new JButton("Enviar");

        EnviaTexto miEvento = new EnviaTexto();
        miboton.addActionListener(miEvento);
        add(miboton);
        Thread miHilo = new Thread(this);
        miHilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket servidorCliente = new ServerSocket(9090);
            Socket cliente;
            PaqueteEnvio paqueteRecibido;
            while (true){
                cliente=servidorCliente.accept();
                ObjectInputStream flujoEntrada = new ObjectInputStream(cliente.getInputStream());
                paqueteRecibido= (PaqueteEnvio) flujoEntrada.readObject();
                campoChat.append("\n"+paqueteRecibido.getNick()+": "+paqueteRecibido.getMensaje());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private class EnviaTexto implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            //System.out.println(campo1.getText());
            try {
                Socket misocket = new Socket("192.168.177.127", 9999);

                PaqueteEnvio datos = new PaqueteEnvio();
                datos.setNick(nick.getText());
                datos.setIp(ip.getText());
                datos.setMensaje(campo1.getText());
                ObjectOutputStream paqueteDatos = new ObjectOutputStream(misocket.getOutputStream());
                paqueteDatos.writeObject(datos);




                paqueteDatos.close();
                //DataOutputStream flujoSalida = new DataOutputStream(misocket.getOutputStream());
                //flujoSalida.writeUTF(campo1.getText());
                //flujoSalida.close();
            } catch (UnknownHostException e2) {
                System.err.println("ERROR > " + e2);
            } catch (IOException ex) {
                System.err.println("ERROR > " + ex);
            }
        }
    }

    private JTextArea campoChat;
    private JTextField campo1, nick, ip;
    private JButton miboton;
}

class PaqueteEnvio implements Serializable {
    private String nick, ip, mensaje;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}