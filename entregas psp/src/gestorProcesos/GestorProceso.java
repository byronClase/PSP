package gestorProcesos;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class GestorProceso extends Thread{

    private Socket socket;
    private OutputStream os;

    public GestorProceso(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void realizarProceso() throws IOException {
        os = socket.getOutputStream();
        Random generadorNumerosAleatorios = new Random();
        int tiemporEspera = generadorNumerosAleatorios.nextInt(60);

        try {
            TimeUnit.SECONDS.sleep(tiemporEspera);
            os.write(tiemporEspera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            os.close();
        }


    }
}
