
package parking.main;
import java.io.File;

import parking.datamanagement.CSVPropertyReader;
import parking.datamanagement.CSVViolationsReader;
import parking.datamanagement.JSONViolationsReader;
import parking.datamanagement.Reader;
import parking.datamanagement.TextReader;
import parking.logging.Logger;
import parking.logging.Logging;
import parking.processor.FinesProcessor;
import parking.processor.FrequentFineProcessor;
import parking.processor.MarketValueProcessor;
import parking.processor.PopulationProcessor;
import parking.processor.PropertyProcessor;
import parking.ui.UI;
import parking.ui.UserInterface;

import java.io.IOException;

/*
 * The runtime arguments in this order:
	-The format of the parking violations input file, either “csv” or “json”
	-The name of the parking violations input file
	-The name of the property values input file
	-The name of the population input file
	-The name of the log file 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		String fileType = args[0];
		String fineName = args[1];
		String propertyName = args[2];
		String populationName = args[3];
		String logName = args[4];

		// read command-line arguments
		if (args.length != 5) {
			System.out.println("Please check the number of arguments.");
			System.exit(0);
		}
		if(!fileType.toLowerCase().equals("json") && !fileType.toLowerCase().equals("csv")) {
			System.out.println("Select your file type: CSV or JSON.");
			System.exit(0);
		}
		try {
			File parking_File = new File(fineName);
			File property_File = new File(propertyName);
			File population_File = new File(populationName);
			File logFile = new File(logName);
			logFile.createNewFile();
			if (!parking_File.exists() || !parking_File.canRead() ||
					!property_File.exists() || !property_File.canRead() ||
					!population_File.exists() || !population_File.canRead()) {
				System.out.println("File Error. Please try again.");
				System.exit(0);	
			}
		} catch (IOException e) {
			System.out.println("File cannot be accessed please check");
			System.exit(0);
		}

//		log file initiated
		Logger logger = Logger.getInstance();
		logger.setFile(logName);
		
		for(String n : args) {
			logger.log(n);
		};
		
		//read input
		TextReader textReader = new TextReader(populationName);
		logger.log(populationName);

		Reader parkReader;
		if(args[0].toLowerCase().equals("csv")) {
			parkReader = new CSVViolationsReader(fineName);
		}
		else {
			System.out.println("runnig json reader");
			parkReader = new JSONViolationsReader(fineName);
		}		
		logger.log(fineName);

		CSVPropertyReader propertyReader = new CSVPropertyReader(propertyName);
		logger.log(propertyName);

		//process info supplied
		FinesProcessor fines = new FinesProcessor(parkReader, textReader);
		PopulationProcessor population = new PopulationProcessor(textReader);

//		System.out.println("b4 reading property file through processor");
		PropertyProcessor property = new PropertyProcessor(propertyReader);
		MarketValueProcessor market = new MarketValueProcessor(propertyReader, textReader);
		FrequentFineProcessor frequent = new FrequentFineProcessor(parkReader, textReader);
		
		UserInterface ui = new UserInterface(population, fines, property, market, frequent, logger);
		
		ui.start();

		//prompt end-user
	}
}
