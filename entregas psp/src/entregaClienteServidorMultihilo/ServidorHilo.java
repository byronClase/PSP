package entregaClienteServidorMultihilo;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorHilo extends Thread {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String nombreCliente;

    public ServidorHilo(Socket socket, DataInputStream in, DataOutputStream out, String nombreCliente) {
      this.socket = socket;
        this.in = in;
        this.out = out;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public void run() {
        int opcion;
        boolean salir = false;
        String mensaje;
        File f = new File("./archivos/numeros.txt");
        while (!salir) {
            try {
                opcion = in.readInt();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (opcion) {
                case 1: {
                    int numeroAleatorio = 0;
                    try {
                        numeroAleatorio = in.readInt();
                        escribirNumeroAleatorio(f, numeroAleatorio);
                        System.out.println("Se escribio el numero en el cliente " + nombreCliente);
                        out.writeUTF("numero guardado correctamente");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
                case 2:
                    try {
                        int numLineas = numeroLineasFichero(f);
                        out.writeInt(numLineas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    try {
                        ArrayList<Integer> numeros = listaNumeros(f);
                        out.writeInt(numeros.size());
                        for (int n : numeros) {
                            out.writeInt(n);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    try {
                        int numLineasCliente = numeroLineasFicheroCliente(f);
                        out.writeInt(numLineasCliente);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    try {
                        byte[] contenidoFichero = ficheroNumeroCliente(f);
                        // Envio la longitud array
                        out.writeInt(contenidoFichero.length);

                        // Envio los bytes uno a uno
                        for (int i = 0; i < contenidoFichero.length; i++) {
                            out.writeByte(contenidoFichero[i]);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    try {
                        out.writeUTF("Solo numeros del 1 al 6.");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Conexion cerrada con el cliente "+ nombreCliente);
    }

    public int numeroLineasFicheroCliente(File f) throws IOException {
        int numLineas = 0;
        String lineas = "";
        BufferedReader br = new BufferedReader(new FileReader(f));
        while ((lineas = br.readLine()) != null) {
            String[] partes = lineas.split(":");
            if (partes[0].equals(nombreCliente)) {
                numLineas++;
            }
        }
        br.close();
        return numLineas;
    }

    public byte[] ficheroNumeroCliente(File f) throws IOException {

        String lineas = "";
        String contenido = "";
        BufferedReader br = new BufferedReader(new FileReader(f));
        while ((lineas = br.readLine()) != null) {
            String[] partes = lineas.split(":");
            if (partes[0].equals(nombreCliente)) {
                contenido += lineas + "\r\n";
            }
        }
        br.close();
        return contenido.getBytes();
    }

    public ArrayList<Integer> listaNumeros(File f) throws IOException {
        ArrayList<Integer> numeros = new ArrayList<>();
        String linea = "";
        BufferedReader br = new BufferedReader(new FileReader(f));
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(":");
            int numero = Integer.parseInt(partes[1]);
            numeros.add(numero);
        }
        br.close();
        return numeros;
    }

    private int numeroLineasFichero(File f) throws IOException {
        int numLineas = 0;
        String lineas = "";
        BufferedReader br = new BufferedReader(new FileReader(f));
        while ((lineas = br.readLine()) != null) {
            numLineas++;
        }
        br.close();
        return numLineas;
    }

    private void escribirNumeroAleatorio(File file, int numeroAleatorio) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(nombreCliente + ":" + numeroAleatorio + "\r\n");
        fw.close();
    }


}
