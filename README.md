# GoogleAPI
The GoogleAPI repository uses java to access the GoogleMAP API.

Address object uses the GoogleMaps API to derive fields such as latitude and longitude.

    
    public Address(String streetAddress, String city, String state, String zipCode){}
    //constructor
   
   
    public Address(String latitude, String longitude){}
    //constructor
    
   
    public String getStreetAddress(){}
    //accessor method returns the street address; Example: "312 Adams Street"

    
    public String getCity(){}
    //accessor method returns the city; Example: "San Antonio"
    
    
    public String getState(){}
    //accessor method returns the state; Example: "Texas" or "TX"
    
    
    public String getZipCode(){}
    //accessor method return the zip code: Example: "78210"
    
    
    public String getLatitude(){}
    //accessor method returns the latitude of a given address: 
    //                            Example 312 Adams St, San Antonio, TX 78210 returns "29.4090725"
    
    
    
    public String getLongitude(){}
    //accessor method returns the longitude of a given address:
     //                            Example 312 Adams St, San Antonio, TX 78210 returns "-98.49091"
     
    
   
    public void showJSONInBrowser(){}
    //showJSONInBrowser calls the GoogleMapsAPI with a given address
    //GoogleMaps displays the resulting JSON Object in the web browser
    //
    //Below is the format of the JSON object that Google returns:
     /**The Google API returns a JSON object in the following format.
    	/**
    	 * {   
              "results" : [  
                   {  
                      "address_components" : [
                      {
                               "long_name" : "6445",
                               "short_name" : "6445",
                               "types" : [ "street_number" ]
                      },
                     {
                               "long_name" : "Walzem Road",
                               "short_name" : "Walzem Rd",
                               "types" : [ "route" ]
                     },
                     {
                               "long_name" : "Northeast Side",
                               "short_name" : "Northeast Side",
                               "types" : [ "neighborhood", "political" ]
                     },
                     {
                               "long_name" : "San Antonio",
                               "short_name" : "San Antonio",
                               "types" : [ "locality", "political" ]
                     },
                     {
                               "long_name" : "Bexar County",
                               "short_name" : "Bexar County",
                               "types" : [ "administrative_area_level_2", "political" ]
                     },
                     {
                               "long_name" : "Texas",
                               "short_name" : "TX",
                               "types" : [ "administrative_area_level_1", "political" ]
                     },
                     {
                               "long_name" : "United States",
                               "short_name" : "US",
                               "types" : [ "country", "political" ]
                     },
                     {
                                "long_name" : "78239",
                                "short_name" : "78239",
                                "types" : [ "postal_code" ]
                     },
                     {
                                "long_name" : "3533",
                                "short_name" : "3533",
                                "types" : [ "postal_code_suffix" ]
                     }
             ], 
             "formatted_address" : "6445 Walzem Rd, San Antonio, TX 78239, USA",
             "geometry" : {
                            "bounds" : {
                                        "northeast" : {
                                               "lat" : 29.5023104,
                                                "lng" : -98.36278969999999
                                        },
                           "southwest" : {
                                           "lat" : 29.5022944,
                                           "lng" : -98.36279379999999
                                         }
                           },
            "location" : {
                             "lat" : 29.5022944,
                             "lng" : -98.36279379999999
                         },
            "location_type" : "RANGE_INTERPOLATED",
            "viewport" : {
                              "northeast" : {
                                              "lat" : 29.5036513802915,
                                              "lng" : -98.36144276970849
                                            },
                              "southwest" : {
                                              "lat" : 29.5009534197085,
                                              "lng" : -98.3641407302915
                                             }
                          }
         },  
         "place_id" : "Eio2NDQ1IFdhbHplbSBSZCwgU2FuIEFudG9uaW8sIFRYIDc4MjM5LCBVU0E",
         "types" : [ "street_address" ]
      } 
      ],  
      "status" : "OK"
      }  
    	 * 
    	 * 
    	 */
    
     public void showLatitudeAndLongitudeMapInBrowser(){}
     //Using send a request using GoogleMapsAPI using the latitude and longitude to display a location in GoogleMaps.
    
      
   
    
         
    
    
   


Messenger objects communicates to a web browser using a GET request.
     
     Messenger uses the default constructor;
     
     public JSONObject getGoogleJSONObject(String webaddress) 
           throws MalformedURLException,IOException{}
           //Using a GET method, submit an http request using the String webaddress.
           //Returns a JSONObject
           
     public JSONObject getGoogleJSONObject(URL url)
           
           //Using a GET method, submit an http request using the URL url.
           //Returns a JSONObject
           
           
Browswer object can communicate to a web browser in the http address line.

           Browser uses the default constructor.
           
           public void openBrowswer(String url){}
                  //Given a string, spawn a web browser and submit the url.
                  //If an API is used the JSON object displays to the screen.
                  
                  
GoogleMapsAPIKey

      public String getGoogleAPIKey(){}
      //accessor method returns a string which can be used as the API Key when using GoogleMaps API
      

