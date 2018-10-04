package guiApp.flightapp;

import java.util.Comparator;

public class Flight implements Comparable<Flight>{
	//fields or properties
	 public String Departure_city;
	 public String Arrival_city;
	 public String Year_Month;
	 public double Price ;
	 
//setter and getter method to set and get the fields
	public String getDeparture_city() {
		return Departure_city;
	}
	public void setDeparture_city(String Departure_city) {
		this.Departure_city = Departure_city;
	}
	public String getArrival_city() {
		return Arrival_city;
	}
	public void setArrival_city(String Arrival_city) {
		this.Arrival_city = Arrival_city;
	}
	public String getYear_Month() {
		return Year_Month;
	}
	public void setYear_Month(String Year_Month) {
		this.Year_Month = Year_Month;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double Price) {
		this.Price = Price;
	}
	
	// Constructor 
	 public Flight(String Departure_city, String Arrival_city, String Year_Month, double Price) {
         super();
         this.Departure_city=Departure_city;
    	 this.Arrival_city=Arrival_city;
    	 this.Year_Month=Year_Month;
    	 this.Price=Price ;
     }
	 
	//default constructor 
	public Flight() {}
	
	//toString method 
	 @Override
     public String toString() {
         return "\t " + Departure_city + "\t\t " + Arrival_city + "\t\t " + Year_Month + "\t\t " + Price ;
     }
	 
	 public static Comparator<Flight> DepartureCityComparator = new Comparator<Flight>() 
		{

			public int compare(Flight flight1, Flight flight2) 
			   {

				String departure_city1 = flight1.getDeparture_city().toUpperCase();
				String departure_city2 = flight2.getDeparture_city().toUpperCase();

				//ascending order
				return departure_city1.compareTo(departure_city2);

				//descending order
				//return departure_city2.compareTo(departure_city1);
		
			  }
			
		};
		
		public int compareTo(Flight compareFlight)
		 {
			
			double comparePrice = ((Flight) compareFlight).getPrice(); 
			
			//ascending order
			return (int) (this.Price - comparePrice);
			
			//descending order
			//return comparePrice - this.Price;
		 }

}//class end
