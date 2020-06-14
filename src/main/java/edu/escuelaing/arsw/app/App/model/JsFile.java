package edu.escuelaing.arsw.app.App.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class JsFile implements BrowserFile {
    @Override
    public void getFile(String path, Socket clientSocket) throws IOException {
        String result = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String line = null;
        while (( line = bufferedReader.readLine()) != null){
            result = result + line;
        }
        clientSocket.getOutputStream().write(("HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/javascript\r\n\r\n" + result).getBytes());
        bufferedReader.close();
    }
}
