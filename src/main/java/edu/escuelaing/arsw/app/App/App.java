package edu.escuelaing.arsw.app.App;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App {
    static ExecutorService executorService = Executors.newFixedThreadPool(10);
    static int counterConnections = 0;

    public static void main( String[] args ) {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(35000);
            System.out.println("Servidor esperando solicitudes al puerto 35000");
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado");
                ClientRequest clientRequest = new ClientRequest(clientSocket);
                executorService.execute(clientRequest);
                counterConnections++;
                System.out.println(counterConnections);
            }
        } catch (IOException e){
            System.err.println("Could not listen on port: 35000");
            System.exit(1);
        }
    }

}
