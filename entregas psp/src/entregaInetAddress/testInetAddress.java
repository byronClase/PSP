package entregaInetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class testInetAddress {

    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String dir = ip.getHostAddress();
            System.out.println(ip.toString());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
