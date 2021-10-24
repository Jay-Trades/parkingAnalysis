package parking.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parking.data.PopulationData;
import parking.data.PropertyData;
import parking.datamanagement.CSVPropertyReader;
import parking.datamanagement.TextReader;

public class MarketValueProcessor {
			int totalPopulation;
			protected HashMap<String, ArrayList<PropertyData>> propertyData;
			protected HashMap<String, PopulationData> populationData;
//			Logger logger;
			
			
			public MarketValueProcessor(CSVPropertyReader propertyReader, TextReader textReader) {
				this.propertyData = propertyReader.getAllData();
				this.populationData = textReader.getAllData();

//				this.logger = logger;
			};
			// create a parser
			
			public int getMarketPerCap(String zipCode) {
				long totalMarketValue = 0;
				ArrayList<PropertyData> data = propertyData.get(zipCode);
				if (data != null) {
					for (PropertyData d : data) {
						if (d.getMarketValue() != null) {
							totalMarketValue = totalMarketValue + d.getMarketValue();
						}
					}
				} else {
					System.out.println("0");
					return 0;
				}
				
				PopulationData popData = populationData.get(zipCode);
				if (popData == null || totalMarketValue == 0|| popData.getPopulation() == 0) {
					System.out.println("0");
					return 0;
				}
				
				long population = popData.getPopulation();
				System.out.println("pop " + population);
				System.out.println("value " + totalMarketValue);

				double marketVal = totalMarketValue/population;
				System.out.println(marketVal);

				int marketValue = (int) Math.floor(marketVal);
				//need to truncate to a int but not round up how can i do this.
				
				System.out.println("market value per capita : " + marketValue);
				
				return marketValue;
			}

	}