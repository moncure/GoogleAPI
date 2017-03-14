
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URI;
import java.lang.RuntimeException;

public class Browser {
    
    
    
    
    
    //open the default web browswer using a specific URL
    //the JSON object is written to the browser
    public void openBrowswer(String url){
        
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }//end openBrowswer
    
    
    public static void main(String[] args){
        //Browser b = new Browser();
        //b.openBrowswer("http://www.google.com");
        
        
    }
            
}
