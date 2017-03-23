

/**
 *
 * @author Amy
 */


import org.json.JSONObject;
import java.net.MalformedURLException;
import java.io.IOException;




public class Address {
    
    private String streetAddress;  //includes both the number and the street
    private String city;
    private String state;
    private String zipCode;
    
    private transient String latitude;   //derived from the JSON object
    private transient String longitude;  //derived from the JSON object
    private transient JSONObject json;
    
    private String googleURL = "https://maps.googleapis.com/maps/api/geocode/json";

    
   //*******************************************************************************
   //*******************************************************************************
    public Address(String streetAddress, String city, String state, String zipCode){
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.json = null;
        getLatitudeAndLongitude();
    }
    
    //******************************************************************************
    //******************************************************************************
    public Address(String latitude, String longitude){
    	
    	this.latitude = latitude;
    	this.longitude = longitude;
    	this.json = null;
    	getGoogleMailingAddress();
    	
    }
    
  //******************************************************************************
  //******************************************************************************
    public String getStreetAddress(){
    	return streetAddress;
    }
    
  //******************************************************************************
  //******************************************************************************
    public String getCity(){
    	return city;
    }
    
    
  //******************************************************************************
  //******************************************************************************
  public String getState(){
	  return state;
  }
  
//******************************************************************************
//******************************************************************************
  
  public String getZipCode(){
	  return zipCode;
  }
    
  //******************************************************************************
  //******************************************************************************
    
    
    public String getLatitude(){
    	return latitude;
    }
    
    //******************************************************************************
    //****************************************************************************** 
    
    public String getLongitude(){
    	return longitude;
    }
    
    //******************************************************************************
    //******************************************************************************
    
    
    public void showJSONInBrowser(){
    	
    	//Send the request to GoogleMaps to a browser
        //The JSON object is displayed on the browser.
    	
        Browser b = new Browser();
        b.openBrowser(googleURL + "?address=" + getGoogleFormatAddress()); // GoogleMaps returns the JSON object to the browser
    }
    //******************************************************************************
    //******************************************************************************   
    
    public void showLatitudeAndLongitudeMapInBrowser(){
    	//Using the latitude and longitude, show a specific location on a Google map in the browser.
    	Browser b = new Browser();
    	String addy = "http://maps.google.com/?q=" +  latitude +  ","  +longitude;
    	b.openBrowser(addy);
    	
    	
    }
  //******************************************************************************
  //******************************************************************************  
  //showStreetViewInBrowser uses the GoogleMaps API to show a location in the Browser using the street view
    
    public void showStreetViewInBrowser(){
    	Browser b = new Browser();
    	GoogleAPIKey k = new GoogleAPIKey();
    	
    	//pitch  (default 0) defines the angle variance "up" or "down" from the camera's initial default pitch, which is often (but not always) flat horizontal. 
    	//heading (default 0) defines the rotation angle around the camera locus in degrees relative from true north. Headings are measured clockwise (90 degrees is true east).
    	
    	String addy = "https://maps.googleapis.com/maps/api/streetview?size=600x300&location="+
    		 latitude + "," + longitude + "&heading=-70&pitch=-0.5&key=" + 
    		 k.getGoogleAPIKey();
    	b.openBrowser(addy);
    }
    
    
    //******************************************************************************
    //******************************************************************************
    /*
     * getGoogleMailingAddress uses the latitude and longitude to make a GoogleMapsAPI call
     *            GoogleMaps returns a JSON object from which the class variables 
     *            streetAddress, city, state, and zip can be changed or initialized.
     */
     private void getGoogleMailingAddress(){
      	GoogleAPIKey k = new GoogleAPIKey();
      	
          this.json = getGoogleJSONObject(googleURL + "?latlng=" + latitude + "," + longitude + "&" +k.getGoogleAPIKey());
      	
      	String jsonString = json.toString();
      	//System.out.println(jsonString);
      	int indexOfFormattedAddress = jsonString.indexOf("\"formatted_address\"");
      	//System.out.println(indexOfFormattedAddress);
      	//System.out.println(jsonString.substring(indexOfFormattedAddress+21));
      	
      	//get the street address
      	char[] jsonArray = jsonString.toCharArray();
      	
      	
      	
      	int indexOfStreet = indexOfFormattedAddress+21;
      	int i = indexOfStreet;
      	
      	while(!(jsonArray[i] == ',') ){
      		i++;
      	}
      	//i is the index of the last character in the street address
      	streetAddress = jsonString.substring(indexOfStreet, i);
      	//System.out.println("The street address is: " + streetAddress);
      	
      	i = i+2;  //move past the comma to begin with the city
      	int indexOfCity = i;
      	while(!(jsonArray[i] == ',') ){
      		i++;
      	}
      	city = jsonString.substring(indexOfCity, i);
      	//System.out.println("The city is: " + city);
      	
      	i = i+2;
      	int indexOfState = i;
      	while(!(jsonArray[i] == ' ') ){
      		i++;
      	}
      	state = jsonString.substring(indexOfState, i);
      	//System.out.println("The state is: " + state);
      	
      	i++; //remove the leading space;
      	int indexOfZip = i;
      	while(!(jsonArray[i] == ',') ){
      		i++;
      	}
      	zipCode = jsonString.substring(indexOfZip, i);
      	//System.out.println("The zipCode is: " + zipCode);
      	
      	
      	
      	
      	
      }
    
    
    
    
    
    
    
    
    private String getGoogleFormatAddress(){
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
    //******************************************************************************
    //******************************************************************************
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
    
    
    //******************************************************************************
    //******************************************************************************    
    
   
   
    private JSONObject getGoogleJSONObject(String url){
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
    	
    	
        JSONObject json = null;
        Messenger m = new Messenger();
        
        try{
            return m.getGoogleJSONObject(url);
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  
       
        return json;
    }
 
 //******************************************************************************
 //******************************************************************************   
    
    private boolean AddressTester(){
        
        if (!getGoogleFormatAddressTester())
            return false;
        
        
        return true;
        
    }
    
 
 //******************************************************************************
 //******************************************************************************
      
    
    private void getLatitudeAndLongitude(){
    	this.json = getGoogleJSONObject(googleURL + "?address=" + getGoogleFormatAddress());
    	
    	String jsonString = json.toString();
    	//System.out.println(jsonString);
    	
    	
    	
    	int indexOfLocation = jsonString.indexOf("\"location\"");
    	String location = jsonString.substring(indexOfLocation);
    	//System.out.println(location);
    	int indexOfLat = location.indexOf("lat");
    	int indexOfLng = location.indexOf("lng");
    	
    	//{"lng":-98.49091,"lat":29.4090725}
    	//the longitude is displaced 5 units from indexOfLong
    	//find the index of the comma
    	
    	char[] locationArray = location.toCharArray();
    	
    	
    	int i = indexOfLng + 5;
    	
    	while(!(locationArray[i] == ',') ){
    		i++;
    	}
    	longitude = location.substring(indexOfLng+5, i);
    	
    	i = indexOfLat + 5;
    	while(!(locationArray[i] == '}')){
    		i++;
    	}
    	latitude = location.substring(indexOfLat+5,i);
     	
    }
    
    //******************************************************************************
    //****************************************************************************** 
    
    
            
    
}
