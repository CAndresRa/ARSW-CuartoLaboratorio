package edu.escuelaing.arsw.app.App.model;

import java.io.IOException;
import java.net.Socket;

public class NotFound implements BrowserFile {
    @Override
    public void getFile(String path, Socket clientSocket) throws IOException {
        clientSocket.getOutputStream().write((
                "HTTP/1.1 400 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n" +
                        "<html><head><title>Error 404</title></head><body><h1>ERROR 404: Archivo no encontrado en el sistema</h1></body></html>").getBytes()

        );
    }
}
