/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamapt.transferservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kooldeji
 */
public class Test {
    public static void main(String[] args) {
        try {
            JSONObject json = new JSONObject();
            
            //Add balance
//            json.put("balance", 1000l);
//            System.out.println(json);
//            sendPost("http://localhost:8080/api/balance", json);
            
            //Transfer between two accts            
            json.put("to", 10001);
            json.put("from", 10102);
            json.put("amount", 10000);
            sendPost("http://localhost:8080/api/transfer", json);
        } catch (JSONException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static HttpURLConnection sendPost(String urlString, JSONObject json){
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
//            connection.setRequestProperty("Accept-Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
            connection.setRequestProperty("Accept",
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.connect();
            try (OutputStream os = connection.getOutputStream()) {
                os.write(json.toString().getBytes());
                System.out.println(connection.getHeaderFields());
                os.flush();
            }

            System.out.println("POST RESPONSE FOR URL: " + connection.getURL() + " -> " + connection.getResponseCode() + " " + connection.getResponseMessage());
            System.out.println(connection.getHeaderFields());

            InputStream errorStream;
            String err = "Error: ";
            errorStream = connection.getErrorStream();
            if (errorStream != null) {
                Scanner scanner = new Scanner(errorStream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    err += scanner.next();
                } else {
                    err += null;
                }
            } else {
                err += null;
            }
            System.out.println(err);

            InputStream inputStream = connection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            if (scanner.hasNext()) {
                String out = scanner.next();
            } else {
                return null;
            }
            return connection;
        } catch (MalformedURLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
