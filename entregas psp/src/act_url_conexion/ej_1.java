package act_url_conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ej_1 {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.arteguias.com/alcazar/alcazartoledo.htm");
            URLConnection conexion = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            while (br.readLine()!=null){
                String texto = br.readLine();
                System.out.println(texto);
            }
        } catch (MalformedURLException e) {
            System.err.println("ERROR > "+e);
        } catch (IOException e) {
            System.err.println("ERROR > "+e);
        }catch(Exception e){
            System.err.println("ERROR > "+e);
        }
    }
}
