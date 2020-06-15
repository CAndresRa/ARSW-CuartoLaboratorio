package edu.escuelaing.arsw.app.App.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class CssFile implements BrowserFile {
    @Override
    public void getFile(String path, Socket clientSocket) throws IOException {
        try {
            String result = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line = null;
            while (( line = bufferedReader.readLine()) != null){
                result = result + line;
            }
            clientSocket.getOutputStream().write(("HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/css\r\n\r\n" + result).getBytes());
            bufferedReader.close();

        }
        catch (IOException e){
            clientSocket.getOutputStream().write((
                    "HTTP/1.1 400 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n" +
                            "<html><head><title>Error 404</title></head><body><h1>ERROR 404: Archivo no encontrado en el sistema</h1></body></html>").getBytes()

            );
        }
    }
}
