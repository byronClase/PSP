package gestorProcesos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GestorProceso_Servidor {

	private ServerSocket serverSocket;


	public GestorProceso_Servidor(int puerto) throws IOException {
		serverSocket = new ServerSocket(puerto);
		while (true){
			Socket socket = serverSocket.accept();
			System.out.println("(SERVIDOR) Conexion establecida");
			new GestorProceso(socket).start();
		}
	}

	
	public void stop() throws IOException {
		serverSocket.close();
		System.out.println("(SERVIDOR) Conexiones cerradas.");
	}
	
	public static void main(String[] args) {
		try {
			GestorProceso_Servidor servidor = new GestorProceso_Servidor(1028);
			servidor.stop();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}
	
	

}
