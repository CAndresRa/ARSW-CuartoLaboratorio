package edu.escuelaing.arsw.app.App;

import edu.escuelaing.arsw.app.App.model.BrowserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClientRequest implements Runnable {
    private Socket clientSocket;

    /**
     * Constructor class clientRequest,
     * @param clientSocket socket connection with new client
     */
    public ClientRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;
        try{
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Integer count = 0;
            String[] header = null;
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (count == 0) {
                    header = inputLine.split(" ");
                }
                count++;
                if (!in.ready()) {
                    break;
                }
            }
            String path = "src/main/resources/";
            path = path + header[1].substring(1);
            BrowserService browserService = new BrowserService(path);
            browserService.getFileBrowser(clientSocket);
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null)
                    in.close();
                clientSocket.close();
            } catch (IOException e) {
                //e.printStackTrace();
            }
        }
    }
}
