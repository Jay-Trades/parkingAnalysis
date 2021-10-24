package parking.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parking.data.PropertyData;
import parking.datamanagement.CSVPropertyReader;

public class PropertyProcessor {
		int totalPopulation;
		ArrayList<Integer> data;
		protected HashMap<String, ArrayList<PropertyData>> propertyData;
		
		
		public PropertyProcessor(CSVPropertyReader propertyReader) {
			this.propertyData = propertyReader.getAllData();
			
		};
		
		public int getAvgMarketValue(String zipCode) {
			AverageValue dataGetter = new GetMarketValueData(propertyData, zipCode);
			return getAvgValue(dataGetter);
		}
		
		public int getAvgAreaValue(String zipCode) {
			AverageValue areaDataGetter = new GetTotalLivingData(propertyData, zipCode);
			return getAvgValue(areaDataGetter);
		}
		
		public int getAvgValue(AverageValue dataValues) {
			long houses = 0;
			long totalValue = 0;
			
			data = dataValues.getData();
			if (data != null) {
				for (Integer value : data) {
					if (value != null) {
						totalValue = totalValue + value;
						houses++;
					}
				}
			} else {
				System.out.println("0");
				return 0; //holder for no zipcode found
			}
			System.out.println(houses);
			System.out.println(totalValue);
			double doubleHouses = houses;
			double doubleValue = totalValue;
			
			double avgVal = doubleValue/doubleHouses;
			System.out.println(avgVal);

			int avgValue = (int) Math.floor(avgVal);


			System.out.println(avgValue);
			return avgValue;
		}

}
