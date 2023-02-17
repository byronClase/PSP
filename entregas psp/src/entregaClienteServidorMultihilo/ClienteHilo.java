package entregaClienteServidorMultihilo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ClienteHilo extends Thread {
    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    public int generarNumerosAleatorios(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;
        String mensaje;
        boolean salir = false;
        while (!salir) {
            System.out.println("1. Almacenar numero en el archivo");
            System.out.println("2. Numero almacenados hasta el momento");
            System.out.println("3. Lista de numeros almacenados");
            System.out.println("4. El numero de numeros almacenados por el cliente");
            System.out.println("5. Archivo con numeros del cliente");
            System.out.println("6. Salir");
            opcion = sc.nextInt();
            try {
                out.writeInt(opcion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (opcion) {
                case 1: {
                    int numeroAleatorio = generarNumerosAleatorios(1, 100);
                    System.out.println("numero generado: " + numeroAleatorio);
                    try {
                        out.writeInt(numeroAleatorio);
                        mensaje = in.readUTF();
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                break;
                case 2:
                    try {
                        int numLineas = in.readInt();
                        System.out.println("hay " + numLineas + " números");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 3:
                    try {
                        int limite = in.readInt();
                        for (int i = 0; i < limite; i++) {
                            System.out.println(in.readInt());
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    int numLineasClientes = 0;
                    try {
                        numLineasClientes = in.readInt();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Hay "+ numLineasClientes+" números de nuestro cliente");
                    break;
                case 5:
                    try {
                        int limiteFichero = in.readInt();
                        // Creo un array de bytes
                        byte[] contenidoFichero= new byte[limiteFichero];

                        // Recibo el contenido array
                        for (int i = 0; i < limiteFichero; i++) {
                            contenidoFichero[i] = in.readByte();
                        }
                        // Creo el string
                        String contenido = new String(contenidoFichero);
                        // lo guardo en un nuevo fichero
                        FileWriter fw1 = new FileWriter("./archivos/numeros_cliente.txt");
                        fw1.write(contenido);
                        fw1.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    try {
                        mensaje = in.readUTF();
                        System.out.println(mensaje);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
    }
}
