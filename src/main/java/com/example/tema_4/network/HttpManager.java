package com.example.tema_4.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class HttpManager implements Callable<String> {

    private URL url;
    private HttpURLConnection connection;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    private final String urlAdress;

    public HttpManager(String urlAdress) {
        this.urlAdress = urlAdress;
    }

    @Override
    public String call(){
        try {
            url = new URL(urlAdress);
            connection = (HttpURLConnection) url.openConnection();
            inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder result = new StringBuilder();
            String linie;
            while ((linie = bufferedReader.readLine()) != null) {
                result.append(linie);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
        return null;
    }
}
