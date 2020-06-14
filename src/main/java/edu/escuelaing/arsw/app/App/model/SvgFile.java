package edu.escuelaing.arsw.app.App.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class SvgFile implements  BrowserFile{
    @Override
    public void getFile(String path, Socket clientSocket) throws IOException {
        BufferedImage in = ImageIO.read(new File( path));
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        ImageIO.write(in, "PNG", buffer);
        dataOutputStream.writeBytes( "HTTP/1.1 200 \r\n");
        dataOutputStream.writeBytes("Content-Type: image/svg+xml \r\n");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.write(buffer.toByteArray());
    }
}
