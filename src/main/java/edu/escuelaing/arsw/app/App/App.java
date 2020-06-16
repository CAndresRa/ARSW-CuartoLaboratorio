package edu.escuelaing.arsw.app.App;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server Http, open port 35000
 * @author AndresRamirez
 */
public class App {
    static ExecutorService executorService = Executors.newFixedThreadPool(13);
    static int counterConnections = 0;
    static ServerSocket serverSocket = null;

    /**
     * Start Server http: port 35000
     * @param args n/a
     */
    public static void main( String[] args ) {
        int port = getPort();
        try{
            if (serverSocket == null) {
                serverSocket = new ServerSocket(port);
            }
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
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
        //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
