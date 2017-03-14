/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Amy
 */

import java.awt.Desktop;
//import java.net.URI;
import java.io.IOException;
//import java.net.URISyntaxException;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.*;
import java.net.*;
import java.util.List; 

public class Address {
    
    private String streetAddress;  //includes both the number and the street
    private String city;
    private String state;
    private String zipCode;
    
    private transient String latitude;   //derived from the JSON object
    private transient String longitude;  //derived from the JSON object
    
    private String googleURL = "https://maps.googleapis.com/maps/api/geocode/json?address=";

    
    
    public Address(String streetAddress, String city, String state, String zipCode){
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    
    /*
    
    */
    public String getGoogleFormatAddress(){
        /**
         * getGoogleFormatAddress prepares a request string for the GoogleAPI
         * returns only the request parameters. 
         * 
         */
        
        
        //Replace spaces with + symbols
        String googleStreet = streetAddress.replace(" ","+" );;
        String googleCity = city.replace(" ","+");
        String googleState = state.replace(" ", "+");
        
        return (googleStreet + ",+" + googleCity +  ",+"  + googleState + "+" + zipCode);
    }
    
    private boolean getGoogleFormatAddressTester(){
        
        //test an address where the street name has two words, such as Bull Winkle
        //address also has a two word city
        //IDEA Walzem address
        Address testStreet = new Address("6445 Walzem Rd","San Antonio",
                            "Texas","78239");
        String testString = testStreet.getGoogleFormatAddress();
        boolean case1= testString.equals("6445+Walzem+Rd,+San+Antonio,+Texas+78239");
        
        //test an address - Target in Fargo, North Dakota
        testStreet = new Address("4202 13th Ave S","Fargo","North Dakota","58109");
        String testString2= testStreet.getGoogleFormatAddress();
        boolean case2 = testString2.equals("4202+13th+Ave+S,+Fargo,+North+Dakota+58109");
        
        return (case1 && case2);
    }//end getGoogleFormatAddressTester()
    
     
    //Send the request to GoogleMaps to a browser
    //The JSON object is displayed on the browser.
    
    public void showInBrowser(){
        Browser b = new Browser();
        b.openBrowswer(googleURL + getGoogleFormatAddress()); // GoogleMaps returns the JSON object to the browser
    }
    
    public JSONObject getGoogleJSONObject(){
        
        JSONObject json = null;
        Messenger m = new Messenger();
        
        try{
            return m.getGoogleJSONObject(googleURL + getGoogleFormatAddress());
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
       
        return json;
    }
    
    
    public boolean AddressTester(){
        
        if (!getGoogleFormatAddressTester())
            return false;
        
        
        return true;
        
    }
    
   
    
    public static void main(String[] args) {
        Address a = new Address("312 Adams","San Antonio","Texas","78210");
        if (a.AddressTester())
            System.out.println("Success");
        else System.out.println("Error");
        
        //a.showInBrowser();
        JSONObject json = a.getGoogleJSONObject();
         System.out.println(json.toString());
        
    }
            
    
}
