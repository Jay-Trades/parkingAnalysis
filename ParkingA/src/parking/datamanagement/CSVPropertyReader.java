package parking.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import parking.data.PropertyData;
import parking.data.ViolationsData;

public class CSVPropertyReader{
	protected String filename;
	protected PropertyData data;
	
	public CSVPropertyReader(String name) {
		filename = name;
	}

	public HashMap<String, ArrayList<PropertyData>> getAllData() {
		Scanner in = null;
		int zipCodeIndex = -1;
		int totalAreaIndex = -1;
		int marketValueIndex = -1;
		String zipCode;
		Integer marketValue;
		Integer livableArea;
		HashMap<String, ArrayList<PropertyData>> propertyDataByZip = new HashMap<String, ArrayList<PropertyData>>();
		int i = 0;
		try {
			in = new Scanner(new File(filename));
			if (in.hasNextLine()) {
				String[] rawData = in.nextLine().split(",");
				for (String label: rawData) {
//					System.out.println(label);
					if (label.equals("zip_code")){
						zipCodeIndex = i;
					} else if(label.equals("market_value")) {
						marketValueIndex = i;
					} else if(label.equals("total_livable_area")) {
						totalAreaIndex =i;
					}
					i++;
				}
				if (zipCodeIndex == -1 || totalAreaIndex == -1 || marketValueIndex == -1) {
					System.out.println("not all properties found");
					return null;
				}
			}else {
				return null;
			}
			
//			System.out.println("zip " + zipCodeIndex + "area " + totalAreaIndex + "value " + marketValueIndex);
			
			while(in.hasNextLine()) {
				String[] tokens = in.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				//hashmap with zip code as key and another map storing /total market value/livable area/house count as keys with values.
				String tempZipCode = tokens[zipCodeIndex];
				if(tempZipCode == null || tempZipCode.length() < 5) {
					//don't store anything if zip is invalid
					continue;
				} else {
					zipCode = tempZipCode.trim().substring(0,5);
				}
				
			
			    try{
			    	marketValue = Integer.parseInt(tokens[marketValueIndex]);
			    	//can be float but i'm assuming it is an int
				} catch (NumberFormatException e){
					marketValue = null;
				}
			    try{
			    	livableArea = Integer.parseInt(tokens[totalAreaIndex]);
			    	//can be float but i'm assuming it is an int
				} catch (NumberFormatException e){
					livableArea = null;
				}
			    
			    data = new PropertyData(zipCode, marketValue, livableArea);

			    if (propertyDataByZip.get(zipCode) == null) {
			        ArrayList<PropertyData> propertyList = new ArrayList<PropertyData>();
			        propertyList.add(data);
			        propertyDataByZip.put(zipCode, propertyList);
			    } else {
			    	propertyDataByZip.get(zipCode).add(data);
			    }
			}
	
		} catch (IOException e) {
			e.printStackTrace();
			return propertyDataByZip;
			//Before i was doing system.exit(0) to exit the program but a ta said it was bad practice
		} finally {
			in.close();
		}
		return propertyDataByZip;
	}
}
