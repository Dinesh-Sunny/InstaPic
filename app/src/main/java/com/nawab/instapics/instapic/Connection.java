package com.nawab.instapics.instapic;

import android.net.http.AndroidHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Macbook on 21/05/15.
 */
public class Connection {

    public static String getData(String s){
        BufferedReader br = null;
        try {
          URL url = new URL(s);
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                 br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line;

                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }finally {
                if(br != null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
