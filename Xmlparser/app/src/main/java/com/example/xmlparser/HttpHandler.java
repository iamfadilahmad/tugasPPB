package com.example.xmlparser;

import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpHandler {

    public HttpHandler(){}

    public String makeServiceCall(String requrl){
        String response = null;

        try{
            URL url = new URL(requrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            InputStream in = new BufferedInputStream(con.getInputStream());
            response = converStreamToString(in);
        }catch (MalformedURLException e){
            Log.e("httpHandler ","Malformed URL Exception "   + e.getMessage());
        }catch (ProtocolException e){
            Log.e("httpHandler", "Protocol Exception :" + e.getMessage());
        }catch (IOException e){
            Log.e("httpHandler", "IO Exception :" + e.getMessage());
        }catch (Exception e){
            Log.e("httpHanlder", "Exception : " + e.getMessage());
        }
        return  response;
    }

    private String converStreamToString(InputStream in){
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        String string;

        try{
            while((string = reader.readLine())!=null){
                stringBuilder.append(string).append("\n");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                in.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return  stringBuilder.toString();
    }
}
