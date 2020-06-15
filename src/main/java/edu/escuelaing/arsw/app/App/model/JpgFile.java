package edu.escuelaing.arsw.app.App.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class JpgFile implements BrowserFile {
    @Override
    public void getFile(String path, Socket clientSocket) throws IOException {
        try {
            BufferedImage in = ImageIO.read(new File( path));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            ImageIO.write(in, "PNG", buffer);
            dataOutputStream.writeBytes( "HTTP/1.1 200 \r\n");
            dataOutputStream.writeBytes("Content-Type: image/jpeg \r\n");
            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.write(buffer.toByteArray());
        }
        catch (IOException e){
            clientSocket.getOutputStream().write((
                    "HTTP/1.1 400 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/html\r\n\r\n" +
                            "<html><head><title>Error 404</title></head><body><h1>ERROR 404: Archivo no encontrado en el sistema</h1></body></html>").getBytes()

            );
        }
    }
}
