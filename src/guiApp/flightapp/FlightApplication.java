package guiApp.flightapp;
import javax.swing.*; // JFrame, JMenuBar, JMenu, JMenuItem 
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*; // ActionListener, ActionEvent
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
public class FlightApplication extends JFrame implements ActionListener {
	  
	//JPanel
	JPanel contentPane ;
	
    JMenuBar menuBar;
    
    //ReadFile
    JMenu readFile ; 
    JMenuItem read ; 
     
    //SaveFile
    JMenu saveFile; 
    JMenuItem save; 
   
    //DisplayData
    JMenu displayData ; 
    JMenuItem display ; 
    
    //SortData
    JMenu sortData ; 
    JMenuItem sort ; 
    
    //SearchData
    JMenu searchData ; 
    JMenuItem search ; 
   
    //Help
    JMenu help ; 
    JMenuItem helpI ;
    JMenuItem exit ;
    
    //TextArea
    JTextArea displayArea;
    JScrollPane scrollPane ;
    
    //Flight class object List
    List<Flight> flights = new ArrayList<Flight>();
    
    
	public FlightApplication(String title) { 
	      super(title); 
	      setBounds(40,40,600,480); 
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	      
	      contentPane=new JPanel();
	      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	      contentPane.setLayout(new BorderLayout(0, 0));
	      setContentPane(contentPane);
	    //Instantiate text area object
	      displayArea = new JTextArea(); 
	      contentPane.add(displayArea, BorderLayout.CENTER);
	      //Add ScrollPane to JFrame
	      scrollPane = new JScrollPane(displayArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      contentPane.add(scrollPane);
	      
	      //menu bars
	      menuBar = new JMenuBar(); 
	      setJMenuBar(menuBar); 
	      //ReadFile menu
	      readFile = new JMenu("ReadFile"); 
	      read = new JMenuItem("Read"); 
	      read.addActionListener(this); 
	      readFile.add(read); 
	      menuBar.add(readFile); 
	      //SaveFile
	      saveFile = new JMenu("SaveFile"); 
	      save = new JMenuItem("Save"); 
	      save.addActionListener(this); 
	      saveFile.add(save); 
	      menuBar.add(saveFile);
	      //DisplayData
	      displayData = new JMenu("DisplayData"); 
	      display = new JMenuItem("Display"); 
	      display.addActionListener(this); 
	      displayData.add(display); 
	      menuBar.add(displayData);
	      //SortData
	      sortData = new JMenu("SortData"); 
	      sort = new JMenuItem("Sort"); 
	      sort.addActionListener(this); 
	      sortData.add(sort); 
	      menuBar.add(sortData);
	      //SearchData
	      searchData = new JMenu("SearchData"); 
	      search = new JMenuItem("Search"); 
	      search.addActionListener(this); 
	      searchData.add(search); 
	      menuBar.add(searchData);
	      //Help
	      help = new JMenu("Help"); 
	      helpI = new JMenuItem("Help"); 
	      helpI.addActionListener(this); 
	      help.add(helpI);
	      exit = new JMenuItem("Exit"); 
	      exit.addActionListener(this); 
	      help.add(exit); 
	      menuBar.add(help);	      
                
	       
	}//constructor ends
	
	
	 public static void main(String[] args) { 
	      FlightApplication myApp = new FlightApplication("Processing of Flight Data"); 
	      myApp.setVisible(true); 
	   } 
	 
	//method to read data from file
		 public static List<Flight> readDataFromFile()
		    {
		            List<Flight> flightsList = new ArrayList<Flight>();
		            FileInputStream fstream = null;
		            try
		            {
		                fstream = new FileInputStream("./src/guiApp/data/FlightData.txt");
		                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		                String strLine = "";
		                String[] tokens = strLine.split(", ");
		                //Read file line by line
		                while ((strLine = br.readLine()) != null)   {
		                  // Copy the content into the array
		                  tokens = strLine.split(", ");
		                  Flight flight = new Flight();
			                 flight.setDeparture_city(tokens[0]);
				             flight.setArrival_city(tokens[1]);
				             flight.setYear_Month(tokens[2]);
				             flight.setPrice(Double.parseDouble(tokens[3]));
	      		         flightsList.add(flight);
		                  
		                }
		            }
		            catch (IOException e) {
		              e.printStackTrace();
		            }
		            finally {
		                try { fstream.close();
		                } catch ( Exception ignore ) {}
		            }
		            return flightsList;
		    }//end method
		 
	
		 
		 //method to write data to file
		    public static void writeDataToFile(List<Flight> flightDataList) {
		       //List<Flight> flightDataList = readDataFromFile();
		       String filename="./src/guiApp/data/NewData.txt";
		       BufferedWriter bufferedWriter = null;
		        try {
		            bufferedWriter = new BufferedWriter(new FileWriter(filename));
		        } catch (IOException ex) {
		            Logger.getLogger(Flight.class.getName()).log(Level.SEVERE, null, ex);
		        }
		       Flight fd;
		       String row;
		       int nof=0;
		       for(int i=0; i<flightDataList.size(); i++) {
		           fd = flightDataList.get(i);
		           row = fd.Departure_city + ", " + fd.Arrival_city + ", " + fd.Price ;
		           nof++;
		            try {
		                bufferedWriter.write(row);
		                bufferedWriter.newLine();
		            } catch (FileNotFoundException ex) {
		                ex.printStackTrace();
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		        //Close the BufferedWriter
		        try {
		        	     bufferedWriter.write("Number of total flights:"+nof);
		              if (bufferedWriter != null) {
		                  bufferedWriter.flush();
		                  bufferedWriter.close();
		              }
		        } catch (IOException ex) {
		              ex.printStackTrace();
		        }
		    }//end method

		  
	 
	 //method for searching data
	 public Flight Search(double price, List<Flight>FlightList)
     {
         Flight myFlight = new Flight();
         for(int i=0 ; i<FlightList.size() ; i++)
         {
             if(FlightList.get(i).getPrice() == price)
             {
                 myFlight.setPrice(FlightList.get(i).getPrice());
                 myFlight.setDeparture_city(FlightList.get(i).getDeparture_city());
                 myFlight.setArrival_city(FlightList.get(i).getArrival_city());
                 myFlight.setYear_Month(FlightList.get(i).getYear_Month());
	             return myFlight;
             }
         }
         return null;
        
  } //end method
	 
	 
	
	 
	 //method to sort the list data
	 public static List<Flight> sortByDepartureCity( List<Flight> flights)
	  {
				
		Flight  temp, min;
		int loc, i, j;
		
	    min=flights.get(0);
		for(i=0; i<flights.size()-1; i++)
		{
			min=flights.get(i);
			loc=i;
			for(j=i+1;j<flights.size()-1;j++)
			{
				if(flights.get(j).getDeparture_city().length()<min.getDeparture_city().length())
				{
					min=flights.get(j);loc=j;
				}
			}//loop ends
		
		   if(loc!=i)
		   {
			   temp=flights.get(i);
			   flights.set(i, flights.get(loc));
			   flights.set(loc, temp);
		   }
		}//loop ends
		
		return flights;
	
	 }//...method ends
	
	 
	 //Method to perform the Action on Event 
	   public void actionPerformed(ActionEvent ae) { 
	      String choice = ae.getActionCommand(); 
	      if (choice.equals("Exit")) { 
	    	 // Allows the user to exit the application
	         System.exit(0); 
	      } 
	      
	      if (choice.equals("Help")) { 
	    	  /*
		       * Provides instructions to user via a dialog box on how to use the application and
               * displays the details (e.g. contact person and email) for further help.
		       */
	    	     System.out.println("help menu working"); 
	    	     
		         JOptionPane.showMessageDialog(null, "Flight Data Processing application used to read/write data from text file to List, display, sort and search the data");
		         JOptionPane.showMessageDialog(null, "contact person: SK");
		        
		      } //end if
	      
	      if (choice.equals("Read")) { 
	    	  
		         System.out.println("read menu working"); 
		      // reading text file "FlightData.txt" into List/ArrayList
		     	        
		         flights = readDataFromFile();
		         JOptionPane.showMessageDialog(null, "File has been read");
		         System.out.print(flights);
		     
		      } //end if
	      
	      if (choice.equals("Save")) { 
		         System.out.println("save menu working"); 
		         /*
		          * saving the List/ArrayList data to a text file  "NewData.txt"
		          *this file contains only departure city, arrival city, price is as follows 
		          * Adelaide, Sydney, 116
		          * Brisbane, Canberra, 158
		          * Number of total flights: 2
		          */
		         writeDataToFile(flights);
		         JOptionPane.showMessageDialog(null, "Data have been saved to file");
		       	     
		      } //end if
	      
	      
	      if (choice.equals("Display")) { 
	    	  
		         System.out.println("display menu working"); 
		         // Displays all the data from ArrayList/LinkedList in the display area
		       
		        // List<Flight> flights = readDataFromFile();
		         
		         displayArea.selectAll();
		         displayArea.replaceSelection("");
		         
	             displayArea.append("\t Departure city \t\t Arrival city \t\t Year Month \t\t Price \n");
	             displayArea.append("\n==========================================================================================================\n");
	             for(int i = 0; i < flights.size(); i++){
	                	  			                   
	                  displayArea.append((String)flights.get(i).toString());
	                  displayArea.append("\n");
	                 } 
	             displayArea.append("\n==========================================================================================================\n");

	         
		      } //end if
	      
	      if (choice.equals("Sort")) { 
		         System.out.println("sort menu working"); 
		         //Sorts data by the flight departure city in ascending order		       
		         flights = sortByDepartureCity(flights);
		         
		         System.out.print(flights);

		         JOptionPane.showMessageDialog(null, "Data have been sorted");		         
		      	       
		         displayArea.selectAll();
		         displayArea.replaceSelection("");
		         
		         displayArea.append("\t Departure city \t\t Arrival city \t\t Year Month \t\t Price \n");
	             displayArea.append("\n==========================================================================================================\n");
	             for(int i = 0; i < flights.size(); i++){
	                	  			                   
	                  displayArea.append((String)flights.get(i).toString());
	                  displayArea.append("\n");
	                 } 
	             displayArea.append("\n==========================================================================================================\n");
     
		         } //end if
	      
	      if (choice.equals("Search")) { 
		         System.out.println("Search menu working"); 
		        /* 
		         * Asks the user to enter a flight price via a dialog box as shown below and uses
		         *the best searching algorithm covered in COIT20256 unit to search for the given flight price
		         */
		         String fprice = JOptionPane.showInputDialog(null, "Enter the flight price for searching" );
		        
		         if(fprice == null || fprice.isEmpty()) {
		        	 JOptionPane.showMessageDialog(null, "Invalid input flight price should not be null or empty ");
		         }//end outer if
		         else {
		        	 double sprice = Double.parseDouble(fprice);
		        	 if(sprice<1) {
		        		 JOptionPane.showMessageDialog(null, "Invalid input flight price should be greater than $1");
		        	 }//end inner if 
		        	 else {
		        		 Flight flight;
		        		 flight = Search(sprice, flights);		        		
		        		 displayArea.selectAll();
				         displayArea.replaceSelection("");
				         displayArea.append("\t Departure city \t\t Arrival city \t\t Year Month \t\t Price \n");
			             displayArea.append("\n==========================================================================================================\n");
		        		 displayArea.append((String)flight.toString());
		        		 displayArea.append("\n==========================================================================================================\n");
		        	 }//end inner else 
		         }//end outer else
		        	 	        
		        
           }//end if
	
	  }//end method
 }//end class
 