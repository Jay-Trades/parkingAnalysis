package parking.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import parking.data.PopulationData;
import parking.data.PropertyData;
import parking.data.ViolationsData;
import parking.datamanagement.Reader;
import parking.datamanagement.TextReader;

public class FrequentFineProcessor {
	int totalPopulation;
	protected HashMap<String, ArrayList<ViolationsData>> violationsData;
	protected Map<String, PopulationData> populationData;
	protected HashMap<String, Integer> reasonCount = new HashMap<String, Integer>();

	public FrequentFineProcessor(Reader violationsReader, TextReader textReader) {
		this.populationData = textReader.getAllData();
		this.violationsData = violationsReader.getAllData();
	}
	public void getFrequentFine() {		
		for (Entry<String, PopulationData> data : populationData.entrySet()) {
			Integer tempPopulation = data.getValue().getPopulation();
			if(tempPopulation == 0) {
				continue;
			}
			String zip = data.getKey();
			if(zip == null || zip ==  "0" || zip.length() < 5) {
				continue;
			} 
			
			ArrayList<ViolationsData> dataList = violationsData.get(zip);
			if (!(dataList == null)) {
				for (ViolationsData fineData : dataList) {
					String fine = fineData.getReason();
				    if (reasonCount.get(fine) == null) {
				        reasonCount.put(fine, 1);
				    } else {
				    	int count = reasonCount.get(fine);
				    	reasonCount.put(fine, count+1);
				    }
					
				
//				double doublePopulation = tempPopulation;
//				double finePerCapita = doubleTotalFine/doublePopulation;
//			    finePerCapita = Math.floor(finePerCapita * 10000) / 10000;
//				
//				System.out.println(zip + ": " + String.format("%.4f", finePerCapita)); // 1.50	
//		
				}
				Integer count = 0;
				String maxFine = null;
				//prints highest count fine name and number of times it appearas
				for (Entry<String, Integer> entry : reasonCount.entrySet()){
				    if (entry.getValue() > count) {
				        maxFine = entry.getKey();
				        count = entry.getValue();
				    }
				}
				double countD = count;
				
				
				System.out.println("Most common fine for " + zip + " " + maxFine + " happened " + count + " number of times");
//				System.out.println("most common fine divided by population " + countD/totallivablearea/tempPopulation);
				System.out.println("most common fine divided by total livable area per zipcode " );
			}
		}
	}
}
