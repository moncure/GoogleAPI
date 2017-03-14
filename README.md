# GoogleAPI
The GoogleAPI repository uses java to access the GoogleMAP API.

Address object uses the GoogleMaps API to derive fields such as latitude and longitude.

    public Address(String streetAddress, String city, String state, String zipCode){}

    public String getGoogleFormatAddress(){
        /**
         * getGoogleFormatAddress prepares a request string for the GoogleAPI
         * returns only the request parameters. 
         * 
         */
         
   
    public void showInBrowser(){}
         //Send the request to GoogleMaps to a browser
         //The JSON object is displayed on the browser.
    
    
    public JSONObject getGoogleJSONObject(){}
         //Use GoogleMapsAPI to get a JSON object


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
