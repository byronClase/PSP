package act_url_conexion;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ej_2 {
    public static void downloadFile(URL url, String fileName) throws IOException {
        try (InputStream in = url.openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(fileName)) {

            byte[] data = new byte[1024];
            int count;
            while ((count = bis.read(data, 0, 1024)) != -1) {
                fos.write(data, 0, count);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // llamar al método `downloadFile()`
        downloadFile(new URL("http://www.insmet.cu/Pronostico/tv06.jpg"),"miimagen.jpg");
    }
}
