package parking.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parking.data.PopulationData;

public class TextReader {
	protected String filename;
	String zipCode;
	Integer population;
	HashMap<String, PopulationData> populationData;
	public TextReader(String filename) {
		this.filename = filename;
	}
	

	public HashMap<String, PopulationData> getAllData() {
		populationData= new HashMap<String, PopulationData>();
		BufferedReader br = null;
		try {
	        br = new BufferedReader(new FileReader(new File(filename)));
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(" "); 
				zipCode = data[0];
				
				try{
					population = Integer.parseInt(data[1]);
				} catch (NumberFormatException e){
					population = null; //nullto repersent a invalid population since negative and 0 are valid
				}
				
				if(zipCode == null || zipCode.length() < 5) {
					//don't store anything if zip is invalid
					continue;
				} else {
					zipCode = zipCode.substring(0,5);
				}

				PopulationData tempData = new PopulationData(zipCode, population);
				populationData.put(zipCode, tempData);
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return populationData;
			
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return populationData;

	}
}
