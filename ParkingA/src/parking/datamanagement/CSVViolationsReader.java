package parking.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import parking.data.ViolationsData;

public class CSVViolationsReader implements Reader{
	protected String filename;
	
	public CSVViolationsReader(String name) {
		this.filename = name;
	}

	public HashMap<String, ArrayList<ViolationsData>> getAllData() {
		Scanner in = null;
		String zipCode;
		Integer fine;
		String state;
		String reason;
//		HashMap<String, HashMap<Integer, ArrayList<ViolationsData>>> violationsState = new HashMap<String, HashMap<Integer, ArrayList<ViolationsData>>>();
		HashMap<String, ArrayList<ViolationsData>> violationsData = new HashMap<String, ArrayList<ViolationsData>>();

		try {
			in = new Scanner(new File(filename));
			while(in.hasNextLine()) {
				String[] tokens = in.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//				System.out.println(tokens[4]);
				//hashmap with zip code as key and another map storing /total market value/livable area/house count as keys with values.
			    try {
			    	fine = Integer.parseInt(tokens[1]);
			    }catch (NumberFormatException e){
			    	fine = null;
			    }
			    
			    reason = tokens[2];
			    state = tokens[4];
			    
			    String tempZipCode = tokens[6];
				if(tempZipCode == null || tempZipCode.length() < 5) {
					continue;
				} else {
					zipCode = tempZipCode.substring(0,5);
				}
				
				try {
					Integer.parseInt(zipCode);
				}catch (NumberFormatException e){
					continue;
				}

				ViolationsData data = new ViolationsData(zipCode, fine, state, reason); //put it in a data obj
				if (violationsData.get(zipCode) == null) {
					ArrayList<ViolationsData> violationsList = new ArrayList<ViolationsData>();
					violationsList.add(data);
					violationsData.put(zipCode, violationsList);
				} else {
					violationsData.get(zipCode).add(data);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			return violationsData ;
			//Before i was doing system.exit(0) to exit the program but a ta said it was bad practice
		} finally {
			in.close();
		}
		return violationsData;
	}
}
