package entregaInetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class testInetAddress3 {

    public static void main(String[] args) {
        InetAddress dir = null;
        System.out.println("===========================================");
        System.out.println("SALIDA PARA LOCALHOST: ");
        try {
            dir = InetAddress.getByName("locahost");
            mostrarInfo(dir);
            System.out.println("=======DIRECCIONES IP PARA: ==========");
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++)
                System.out.println("\t\t"+direcciones[i].toString());

            System.out.println("==========================================");
        } catch (UnknownHostException e) {
            System.err.println("ERROR > "+e);
        }

    }

    public static void mostrarInfo(InetAddress dir){
        System.out.println("\tMetodo getByName(): " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //USAMOS METODOS DE LA CLASE
        System.out.println("\tMetodo getHostName(): "+dir.getHostName());
        System.out.println("\tMetodo getHostAddress(): "+ dir.getHostAddress());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }
}
