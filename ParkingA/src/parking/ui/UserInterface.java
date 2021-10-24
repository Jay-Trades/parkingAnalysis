package parking.ui;

import java.math.RoundingMode;
import java.security.KeyStore.Entry;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
//This class takes input from the end-user and displays information
import java.util.Scanner;
import java.util.TreeMap;

import parking.logging.Logger;
import parking.processor.FinesProcessor;
import parking.processor.FrequentFineProcessor;
import parking.processor.MarketValueProcessor;
import parking.processor.PopulationProcessor;
import parking.processor.PropertyProcessor;

public class UserInterface {
	protected PopulationProcessor populationProcessor;
	protected FinesProcessor fines;
	protected PropertyProcessor property;
	protected MarketValueProcessor market;
	protected FrequentFineProcessor frequent;
	protected Scanner in;
	protected Logger logger;
	protected ArrayList<ArrayList<Object>> finesPerCap = null;
	//constructor
	public UserInterface(PopulationProcessor population, FinesProcessor fines, PropertyProcessor property, MarketValueProcessor market, FrequentFineProcessor frequent, Logger logger) {
		this.populationProcessor = population;
		this.fines = fines;
		this.property = property;
		this.market = market;
		this.frequent = frequent;
		this.logger = logger;
		in = new Scanner(System.in);
	}
	//Display user input
	public void start() {
		System.out.println("Enter a value between 0 and 6\n");
		System.out.println("If you enter 0, the program will exit\n");
		System.out.println("If you enter 1, the program will show the total population for all zipcodes\n");
		System.out.println("If you enter 2, the program will show the total parking finds per capita for all zipcodes\n");
		System.out.println("If you enter 3, the program will show the average market value for residences in a specified zipcode\n");	
		System.out.println("If you enter 4, the program will show the total livable area for residences in a specified zipcode\n");	
		System.out.println("If you enter 5, the program will show the total residential market value per capita for a specified zipcode\n");
		System.out.println("If you enter 6, the program will show the minimum value between the total market value per capita and the total fines per capita\n");
		String input = in.next();

		logger.log(input);

		if (input.equals("0")) {
			logger.log("0");
			System.exit(1);
		} else if (input.equals("1")) {
			logger.log("1");
			getTotalPopulation();
		} else if (input.equals("2")) {
			logger.log("2");
			if (finesPerCap == null) {
				finesPerCap = fines.getFinePerCapita();
			}else {
				for (ArrayList<Object> data : finesPerCap)
					//might have to cast to an int because it is an obj
					System.out.println(data.get(0) + " " + String.format("%.4f", data.get(1)));
			}
			start();


		} else if (input.equals("3")) {
			logger.log("3");
			readUserZip();
		} else if (input.equals("4")) {
			logger.log("4");
			readAvgAreaValue();
		} else if (input.equals("5")) {
			logger.log("5");
			marketPerCap();
		} else if (input.equals("6")) {
			logger.log("6");
			frequentFinePerLivableArea();
		} else {

			System.out.println("Error Message" + "\n" + "Please try again with a number 0-6.");
			in.close();
			System.exit(0);
		}
	}		

	/*
	 * If the user enters a 1 when prompted 
	 * the total population for all of the ZIP Codes
	 */       
	protected void getTotalPopulation() {
		System.out.println(populationProcessor.getTotalPopulation());
		//prompt user
		start();
	}
	/*
	 *If the user enters a 2 when prompted for input in Step #0, your program should display to the console
	 *the total fines per capita for each ZIP Code
	 */
	//		protected void displayFines(TreeMap<String, Double> finesPerCapita) {	
	//			//compute fine
	//			
	//			for (java.util.Map.Entry<String, Double> zip : finesPerCapita.entrySet()) {
	//				System.out.println(zip.getKey() + " " + String.format("%.4f", zip.getValue()));
	//			}
	//			start();
	//		}
	/*
	 * If the user enters a 3 
	 * prompt the user to enter a ZIP Code
	 */
	private void readUserZip() {
		System.out.println("Enter a zipcode here.");
		String zipCode = in.next();
		logger.log(" " + zipCode);
		for(int i = 0; i < zipCode.length(); i++) {
			if(!Character.isDigit(zipCode.charAt(i))) {
				System.out.println("Error Message.Try again with a numeric zip code.");
				start();
			}
			System.out.println(property.getAvgMarketValue(zipCode));
			//prompt user
			start();	
		}
	}			
	/*
	 * If the user enters a 4 when prompted
	 * prompt the user to enter a ZIP Code.
	 */	

	public void readAvgAreaValue() {
		System.out.println("Enter a zipcode here.");	
		String userZipCode = in.next();
		logger.log(userZipCode);
		if (!userZipCode.matches("^[0-9]{5}$")) {
			System.out.println("Error Message.Try again with a numeric zip code.");
			start();
		}
		System.out.println(property.getAvgAreaValue(userZipCode));
		//prompt user
		start();
	}

	/*
	 * If the user enters a 5 when prompted 
	 * prompt the user to enter a ZIP Code.
	 */	
	public void marketPerCap() {
		System.out.println("Enter a zipcode here.");	
		String userInput = in.next();
		logger.log(userInput);
		if (!userInput.matches("^[0-9]{5}$")) {
			System.out.println("Error Message.Try again with a numeric zip code.");

			start();
		}
		System.out.println(market.getMarketPerCap(userInput));
	}    
	/*
	 * If the user enters 6 when prompted 
	 * get the most frequent fines per livable area
	 */


	public void frequentFinePerLivableArea() {
		frequent.getFrequentFine();
		start();
		
	}

}
