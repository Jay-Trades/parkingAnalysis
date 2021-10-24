package parking.processor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import parking.data.PopulationData;
import parking.data.ViolationsData;
import parking.datamanagement.CSVViolationsReader;
import parking.datamanagement.Reader;
import parking.datamanagement.TextReader;

public class FinesProcessor {
	int totalPopulation;
	protected HashMap<String, ArrayList<ViolationsData>> violationsData;
	protected Map<String, PopulationData> populationData;
	ArrayList<ArrayList<Object>> totalData = new ArrayList<ArrayList<Object>>();
	ArrayList<Object> fineData;
	int totalFine;
	
	
	public FinesProcessor(Reader violationsReader, TextReader textReader) {
		this.populationData = textReader.getAllData();
		this.violationsData = violationsReader.getAllData();

	};
	public ArrayList<ArrayList<Object>> getFinePerCapita() {
		TreeMap<String, PopulationData> sortedData = new TreeMap<>();
		sortedData.putAll(populationData);
		
		for (Entry<String, PopulationData> data : sortedData.entrySet()) {
			fineData = new ArrayList<Object>(); //makeks a clean arraylist each time
			
			totalFine = 0;
			Integer tempPopulation = data.getValue().getPopulation();
			if(tempPopulation == 0) {
				continue;
			}
			String zip = data.getKey();
			
			ArrayList<ViolationsData> dataList = violationsData.get(zip);
			if (!(dataList == null)) {
				for (ViolationsData fineData : dataList) {
					if(fineData.getState().toLowerCase().equals("pa") && fineData.getFine() != null) {
						totalFine += fineData.getFine();
					}
				}
				
				if (totalFine == 0 ) {
					continue;
				}
				double doubleTotalFine = totalFine;
				double doublePopulation = tempPopulation;
				double finePerCapita = doubleTotalFine/doublePopulation;
			    finePerCapita = Math.floor(finePerCapita * 10000) / 10000;
				System.out.println(zip + " " + String.format("%.4f", finePerCapita)); // 1.50	
				
			    fineData.add(zip);
			    fineData.add(finePerCapita);
				totalData.add(fineData);
			}
		}
		
		return totalData;
	}
}

//public void getFinePerCapita() {
//for (Entry<String, PopulationData> data : populationData.entrySet()) {
//	int totalFine = 0;
//	Integer tempPopulation = data.getValue().getPopulation();
//	if(tempPopulation == null || tempPopulation == 0) {
//		continue;
//	}
//	String zip = data.getKey();
//	if(zip == null || zip ==  "0" || zip.length() < 5) {
//		continue;
//	} 
//	ArrayList<ViolationsData> dataList = violationsData.get(zip);
//	if (!(dataList == null)) {
//		for (ViolationsData fineData : dataList) {
//			if(fineData.getState() == "PA") {
//				totalFine += fineData.getFine();
//			}
//		}
//		
//		if (totalFine == 0 ) {
//			continue;
//		}
//		double doubleTotalFine = totalFine;
//		double doublePopulation = tempPopulation;
//		double finePerCapita = doubleTotalFine/doublePopulation;
//	    finePerCapita = Math.floor(finePerCapita * 10000) / 10000;
//		
//		System.out.println(zip + ": " + String.format("%.4f", finePerCapita)); 
//	}		
//}
//}
//}
