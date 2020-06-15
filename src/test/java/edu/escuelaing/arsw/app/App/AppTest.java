package edu.escuelaing.arsw.app.App;

import org.junit.Before;
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    @Test
    public void shouldFindTheExistingFileHtml(){
        try {
            URL url = new URL("http://127.0.0.1:35000/index.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            assertTrue(true);
        } catch (IOException e){
            fail();
        }
    }

    @Test
    public void shouldFindTheExistingFilePng(){
        try {
            URL url = new URL("http://127.0.0.1:35000/pngfile.png");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            assertTrue(true);
        } catch (IOException e){
            fail();
        }
    }

    @Test
    public void shouldFindTheExistingFileJpg() {
        try {
            URL url = new URL("http://127.0.0.1:35000/jpgfile.jpg");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            assertTrue(true);
        } catch (IOException e){
            fail();
        }
    }

    @Test
    public void shouldNotFindFile() {
        try {
            URL url = new URL("http://127.0.0.1:35000/inndex.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            fail();
        } catch (IOException e){
            assertTrue(true);
        }
    }

    @Test
    public void shouldFindTheExistingFiles() {
        for(int x = 0; x < 100; x++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://127.0.0.1:35000/index.html");
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                    } catch (MalformedURLException e) {
                        fail();
                    } catch (IOException e) {
                        fail();
                    }
                }
            }).start();
        }
    }
}
