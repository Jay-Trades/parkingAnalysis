package parking.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import parking.data.PopulationData;
import parking.datamanagement.TextReader;

public class PopulationProcessor {
	int totalPopulation;
	protected Map<String, PopulationData> populationData;


	public PopulationProcessor(TextReader textReader) {
		this.populationData = textReader.getAllData();
	};

	public int getTotalPopulation() {		 	
		for (Entry<String, PopulationData> data : populationData.entrySet()) {
			int tempPopulation = data.getValue().getPopulation();
			totalPopulation += tempPopulation;
			//can do a try except to see if zipcode can be converted to int to filter out invalid zipcodes
		}

		return totalPopulation;
	}
}
