package edu.escuelaing.arsw.app.App.model;

import java.io.IOException;
import java.net.Socket;

public interface BrowserFile {
    /**
     * @param path path of file
     * @return data understandable by the server for display in browser
     * @throws IOException because of the libraries used in the implementation of files
     */
    public void getFile(String path, Socket clientSocket) throws IOException;
}
