
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amy
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;


public class Messenger {
    
    private static final String USER_AGENT = "Mozilla/5.0";
    
    public JSONObject getGoogleJSONObject(URL url){
        try {
            
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
 
            httpConnection.setRequestProperty("User-Agent", USER_AGENT);
 
            int responseCode = httpConnection.getResponseCode();
        
            /*200 OK
            The request has succeeded. The meaning of a success varies depending on the HTTP method:
            GET: The resource has been fetched and is transmitted in the message body.
            HEAD: The entity headers are in the message body.
            POST: The resource describing the result of the action is transmitted in the message body.
            TRACE: The message body contains the request message as received by the server
        
        
            */
        
        
            if (responseCode == 200) {
 
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(
                                                httpConnection.getInputStream()));
             
                String responseLine;
                StringBuffer response = new StringBuffer();
 
                while ((responseLine = responseReader.readLine()) != null) {
                       response.append(responseLine+"\n");
                }
                responseReader.close();
                // print result
                JSONObject json = new JSONObject(response.toString());
                return json;
            }//end if
       

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public JSONObject getGoogleJSONObject(String webaddress) 
           throws MalformedURLException,IOException{
        
        URL url = new URL(webaddress);
        return this.getGoogleJSONObject(url);   
    }
    
    
    
    public boolean messengerTester(){
        
        
        try{
            JSONObject json = getGoogleJSONObject("https://maps.googleapis.com/maps/api/geocode/json?address=312+Adams,+San+Antonio,+Texas+78210");
            System.out.println(json.toString());
            return true;
        
        }catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }    
    }
    
    public static void main(String[] args){
        Messenger m = new Messenger();
        m.messengerTester();
    }
    
    
}
