package act_chattcp;

import java.net.Socket;

public class ComunHilos {

    private int conexiones;//se sumara en 1 por cada cliente nuevo, es usado como indice
    private int actuales;//muestra el numero de clientes actuales si se desconecta uno se resta en este atributo
    private int MAXIMO;//numero maximo de clientes que se puede conectar

    Socket tabla[] = new Socket[MAXIMO];

    private String mensajes;


}
