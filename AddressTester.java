

public class AddressTester {
	
	
	public static void main(String[] args){
		
	 	
		
        
	  Address a = new Address("312 Adams","San Antonio","Texas","78210");
        //test that the latitude has been correctly set:
       boolean case1 =  "29.4090725".equals(a.getLatitude());
        
        //test that the longitude has been correctly set;
       boolean case2 = "-98.49091".equals(a.getLongitude());
        
       
       //test an address where the street name has two words, such as Bull Winkle
       //address also has a two word city
       //IDEA Walzem address
       Address b = new Address("6445 Walzem Rd","San Antonio",
               "Texas","78239");
       boolean case3 = "29.5022944".equals(b.getLatitude());
       boolean case4 = "-98.36279379999999".equals(b.getLongitude());
       b.showStreetViewInBrowser();
       
       
       
     //test an address with one word city - Target in Fargo, North Dakota
       Address testStreet = new Address("4202 13th Ave S","Fargo","North Dakota","58109");
       boolean case5 = "46.8600424".equals(testStreet.getLatitude());
       boolean case6 = "-96.8523177".equals(testStreet.getLongitude());
       
       
     
      //test instantiating with only the latitude and longitude
       //277 Bedford Ave, Brooklyn, NY 11211, USA
       Address c = new Address("40.714224","-73.961452");
       String street = c.getStreetAddress();
       String city = c.getCity();
       String state = c.getState();
       String zipCode = c.getZipCode();
       boolean case7 = street.equals("277 Bedford Ave");
       boolean case8 = city.equals("Brooklyn");
       boolean case9 = state.equals("NY");
       boolean case10 = zipCode.equals("11211");
      
       c.showJSONInBrowser();
       c.showLatitudeAndLongitudeMapInBrowser();
       
       
  	   
       
       
       
       if (case1 && case2 && case3 && case4 && case5 && case6 && case7 && case8 && case9 && case10)
           System.out.println("Success");
       else System.out.println("Error");
       
       
     a.showJSONInBrowser();
     a.showLatitudeAndLongitudeMapInBrowser();
       
       
       
       
    	  
	}
	
	
}