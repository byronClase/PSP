package act_url;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Ej02 {

    public static void mostrarInfo(URL url){
        //String ruta, String protocolo, String host, int puerto, int puertoPorDefecto, File file
        System.out.println("URL: "+ url.toString());
        System.out.println("Protocolo: "+ url.getProtocol());
        System.out.println("Host: "+ url.getHost());
        System.out.println("Puerto por defecto: "+ url.getDefaultPort());
        System.out.println("Puerto: "+ url.getPort());
        System.out.println("Fichero: "+ url.getFile());
    }
    public static void main(String[] args){
        try {
            URL url = new URL("http://www.google.es");
            mostrarInfo(url);
        }catch(Exception e3){
            System.err.println("ERORR 3 > "+ e3);
        }
    }
}
