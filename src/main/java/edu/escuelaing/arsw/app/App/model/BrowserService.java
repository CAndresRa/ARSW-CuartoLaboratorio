package edu.escuelaing.arsw.app.App.model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;

public class BrowserService {
    private String filePath;
    private BrowserFile fileBrowser;

    /**
     * constructor that allows to identify the type of file to be found
     * @param path path of file
     */
    public BrowserService(String path) {
        this.filePath = path;
        try {
            new FileReader(path);
            if(filePath.contains(".html")){
                fileBrowser = new HtmlFile();
            }
            if(filePath.contains(".png")){
                fileBrowser = new PngFile();
            }
            if(filePath.contains(".jpg") || filePath.contains(".jpeg")){
                fileBrowser = new JpgFile();
            }
            if(filePath.contains(".svg")){
                fileBrowser = new SvgFile();
            }
            if (filePath.contains(".css")){
                fileBrowser = new CssFile();
            }
            if (filePath.contains(".js")){
                fileBrowser = new JsFile();
            }
        } catch (FileNotFoundException e) {
            fileBrowser = new NotFound();
        }
    }

    /**
     * @throws IOException because of the libraries used in the implementation of files
     */
    public void getFileBrowser(Socket clientSocket) throws IOException {
        try {
            fileBrowser.getFile(filePath, clientSocket);
        } catch (java.lang.NullPointerException e){
            System.out.println("Algun archivo no se encontro");
        }

    }
}
